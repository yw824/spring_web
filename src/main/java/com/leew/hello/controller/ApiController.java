package com.leew.hello.controller;

import com.leew.hello.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // 해당 클래스는 Rest API 처리하는 Controller
@RequestMapping("/api") // RequestMaping URI를 지정해 주는 Annotation
public class ApiController {
    @GetMapping("/hello") // http://localhost:9090
    public String hello() {
        return "hello Spring Boot";
    }

    /*
    다양한 Response 형태 반환 실습
     */
    @GetMapping("/text") // 문자열(String) 반환
    public String text(@RequestParam String account) {
        // http://localhost:9090/api/text?account=user100
        System.out.println("account: " + account);
        return account;
    }

    @PostMapping("/json")
    // request -> Object Mapper -> Object -> method -> Object -> Object Mapper -> JSON -> response
    public User json(@RequestBody User user) {
        // http://localhost:9090/api/json
        /* BODY :
            {
              "name": "scott",
              "age": 20,
              "phone_number": "010-1234-5678",
              "address": "LA"
            }
         */
        System.out.println(user); // User ECHO
        return user;
    }

    @PutMapping("/resp-put") // PutApiController에 같은 API URI 있음
    // ResponsEntity 사용
    public ResponseEntity<User> put(@RequestBody User user) {
        // 생성 : 201 = HttpStatus.CREATED, 수정 : 200
        // http://localhost:9090/api/resp-put
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
        /* BODY :
            {
              "name": "scott",
              "age": 20,
              "phone_number": "010-1234-5678",
              "address": "LA"
            }
         */
    }
}
