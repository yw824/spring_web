package com.leew.filter.controller;

import com.leew.filter.dto.User;
import com.leew.filter.service.RestTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/client")
public class ApiController {

    // @Autowired
    private RestTemplateService restTemplateService;

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    // http://localhost:9091/api/client
    @GetMapping("/string")
    public String getHello() {
        return restTemplateService.hello();
    }

    @GetMapping("/json")
    public User getJson() {
        return restTemplateService.getJson();
    }

    @GetMapping("/post")
    public User post() {
        return restTemplateService.post();
    }
}
