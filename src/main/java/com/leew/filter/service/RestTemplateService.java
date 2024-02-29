package com.leew.filter.service;

import com.leew.filter.dto.User;
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
}

