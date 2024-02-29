package com.leew.hello.controller;

import com.leew.hello.dto.FilterDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/string")
    public String hello() {
        return "hello server";
    }

    // 기존에 Name, Age만 필요한 FilterDTO 객체 재사용
    @GetMapping("/json")
    // 에러 : RequestParam 파라미터 담으면, 에러 발생
    public FilterDTO getJson(@RequestParam("name") String name, @RequestParam("age") int age) {
        FilterDTO user = new FilterDTO();
        user.setName(name);
        // user.setName(name);
        user.setAge(age);
        // user.setAge(20);

        return user;
    }
}
