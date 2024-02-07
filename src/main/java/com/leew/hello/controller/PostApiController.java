package com.leew.hello.controller;

import com.leew.hello.dto.PostRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {

    @PostMapping("/post")
    public void post(@RequestBody Map<String, Object> requestData) {
        requestData.forEach((key, value) -> {
            System.out.println("key: " + key);
            System.out.println("value: " + value);
        });
    }

    @PostMapping("/post1")
    public void post1(@RequestBody PostRequestDTO requestData) {
        // DTO 클래스 가서 Getter/Setter/toString 만들어 줘야 한다.
        System.out.println(requestData);
    }
}
