package com.leew.filter.service;

import com.leew.filter.dto.User;
import com.leew.filter.dto.UserRequest;
import com.leew.filter.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {
    // http://localhost/api/server -> request 요청
    // response
    public String hello() { // String 형태의 응답을 받을 것
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090") // 9090 서버에 요청
                .path("/api/server/string")
                .encode().build().toUri();

        System.out.println(uri.toString());

        String result = "";

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        // 궁극적인 목적 : exchange 사용하는 것
        // result = restTemplate.getForObject(uri, String.class); // uri에 요청 후 문자열로 받는다.
        ResponseEntity<String> res = restTemplate.getForEntity(uri, String.class);
        result = res.getBody();

        System.out.println(res.getStatusCode());
        System.out.println(res.getBody());

        return result;
    }

    /**
     * String 형태는 받았는데, JSON 형태는 어떻게 받을 것인가??
     * {
     *     "name": "scott",
     *     "age": 10
     * } -> User DTO 사용
     */

    public User getJson() {
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090") // 9090 서버에 요청
                .path("/api/server/json")
                .queryParam("name", "aaaa")
                .queryParam("age", 88)
                .encode().build().toUri();

        System.out.println(uri.toString());

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        // 궁극적인 목적 : exchange 사용하는 것
        // result = restTemplate.getForObject(uri, String.class); // uri에 요청 후 문자열로 받는다.
        ResponseEntity<User> res = restTemplate.getForEntity(uri, User.class);
        User result = res.getBody();

        return result;
    }

    public User post() {
        // http://localhost:9090/api/server/user/{userId}/name/{userName}
        /*
            {
                "name": "aaaa",
                "age": 88
            }
         */
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/name/{userName}/age/{userAge}")
                .encode().build()
                .expand("scott", 88) // 각각의 requestBody 이름과 연결된다.
                .toUri();

        System.out.println(uri);

        // http body -> object -> object mapper -> json -> restTemplate -> http body(json)

        UserRequest req = new UserRequest();
        req.setName("Doe");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class);
        // expand로 설정한 것은 PathVariable로 들어가고, Req 객체는 RequestBody로 들어간다.
        // 어떤 자료형으로 반환할 지 모른다면, 일단 String 자료형으로 받는 전략
        // 그러나 대부분의 REST API는 잘 설정되어 있다. 작게 맞춰야 하는 사항들만 맞추면 됨
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }
}

