package com.leew.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 해당 클래스는 Rest API 처리하는 Controller
@RequestMapping("/api") // RequestMaping URI를 지정해 주는 Annotation
public class ApiController {
    @GetMapping("/hello") // http://localhost:9090
    public String hello() {
        return "hello Spring Boot";
    }
}
