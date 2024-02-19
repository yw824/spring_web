package com.leew.hello.controller;

import com.leew.hello.dto.FilterDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// FilterDTO dto객체 사용

@Slf4j
@RestController
@RequestMapping("/filter")
public class FilterController {

    @PostMapping("")
    public FilterDTO user(@RequestBody FilterDTO user) {
        log.info("User: {}", user);
        return user; // echo return
    }
}
