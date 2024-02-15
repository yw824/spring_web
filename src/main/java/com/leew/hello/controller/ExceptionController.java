package com.leew.hello.controller;

import com.leew.hello.dto.ExceptionUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        // Exception -> Server에서 500 반환

        return user;
    }

    @PostMapping("/post")
    public ExceptionUser post(@Valid @RequestBody ExceptionUser user) {
        System.out.println("Exception Post");
        System.out.println(user);

        // 값을 잘못 보내면 400 에러
        return user;
    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println("Exception Handler in Controller: ");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
