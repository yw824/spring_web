package com.leew.hello.controller;

import com.leew.hello.dto.ExceptionUser;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/get")
    public ExceptionUser get(
            @RequestParam(required = false) String name, // 파라미터 없이도 동작 + 값은 null
            @RequestParam(required = false) Integer age) {
        ExceptionUser user = new ExceptionUser();
        user.setName(name);
        user.setAge(age);

        int a = 10 + age; // null 값 들어오면 NullPointerException 발생

        return user;
    }

    @PostMapping("/post")
    public ExceptionUser post(@Valid @RequestBody ExceptionUser user) {
        System.out.println("Exception Post");
        System.out.println(user);
        return user;
    }
}
