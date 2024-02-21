package com.leew.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/access")
public class PublicController {
    // public Controller : 아무런 권한이 없는 사용자 모두가 들어올 수 있도록 만들 예정
    @GetMapping("/public")
    public String get() {
        return "public Hello";
    }
}

