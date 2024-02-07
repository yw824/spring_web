package com.leew.hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leew.hello.dto.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("------Object Mapper------");

        /*
        Object Mapper :
        Text JSON -> Object
        Object -> Text JSON

        JSON(text) -req-> Controller -> Map to Object
        Object -> Controller -res-> Map to JSON(text)

        직접 객체로 생성해서 활용할 수 있는 방법이 있다.
         */

        var objectMapper = new ObjectMapper();
        // object -> text
        var user = new UserRequest("scott", "scott@gmail.com", 20);

        // JsonProcessingException 발생 가능 -> method에 throw Exception 처리해야 함
        var text = objectMapper.writeValueAsString(user);
        System.out.println(text); // 여기까지만 작성하면 에러 발생 -> DTO 가서 Getter/Setter 설정해야 함
        // 특징 1 : Object Mapper는 Getter/Setter 사용한다.

        // object -> text
        var objectUser = objectMapper.readValue(text, UserRequest.class);
        System.out.println(objectUser); // 에러 발생
        /* 에러 로그 : 생성자가 없어 생성할 수 없다 : 근데 DTO 가면 생성자 있다.
            Cannot construct instance of `com.leew.hello.dto.UserRequest`
            (no Creators, like default constructor, exist):
            cannot deserialize from Object value (no delegate- or property-based Creator)
         */
        // Default 생성자도 생성해야 한다. 생성 후에는 성공
        // 특징 2 : Object Mapper는 default 생성자를 필요로 한다.

        // 에러 발생하는 경우 3
        UserRequest defaultUser = UserRequest.getDefaultUser();
        // UserRequest defaultUser = UserRequest.defaultUser(); // get을 어두에서 없앤다.

    }

}
