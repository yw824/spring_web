package com.leew.hello.controller;

import com.leew.hello.dto.FilterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/server")
@Slf4j
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

    @PostMapping("/name/{userName}/age/{userAge}")
    public FilterDTO post(
            @RequestBody FilterDTO user,
            @PathVariable(value = "userName") String userName,
            @PathVariable(value = "userAge") int userAge,
            @RequestHeader("x-authorization") String authorization,
            @RequestHeader("custom-header") String customHeader
    ) { // DTO 객체로 담아서도 올 수 있고, 각자 변수대로 담아서 올 수도 있다.
        log.info("userId: {}, userName: {}", userName, userAge);
        log.info("x-authorization: {}, custom_header: {}", authorization, customHeader);
        log.info("client req: {}", user);
        return user;
    }

}
