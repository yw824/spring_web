package com.leew.hello.controller;

import com.leew.hello.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {

    private final AsyncService asyncService;

    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/")
    public String hello() {
        asyncService.hello();
        log.info("Method END");
        // asyncService hello 함수가 동기/비동기일 때 Method END 메세지 실행 시점 비교
        return "hello";
    }
}
