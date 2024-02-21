package com.leew.hello.controller;

import com.leew.hello.annotation.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/access")
@Auth // Auth 어노테이션이 있으면, 세션이 있을 경우에만 통과
@Slf4j
public class PrivateController {
    // 세션, 인증 등 권한 인증이 된 경우에만 요청 응답
    @GetMapping("/private")
    public String get() {
        log.info("In Private Controller");
        return "Private Hello";
    }
}
