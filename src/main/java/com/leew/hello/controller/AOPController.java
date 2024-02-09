package com.leew.hello.controller;

import com.leew.hello.dto.AOPUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aop")
public class AOPController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") Long id, @RequestParam String name) {
        System.out.println("AOP GET Method");
        System.out.println("id: " + id);
        System.out.println("name: " + name);

        // afterReturning JoinPoint 때문에 리턴값 생성
        return id + ": " + name;

        // http://localhost:9090/aop/get/100?name="leew"
    }

    @PostMapping("/post")
    public AOPUser post(@RequestBody AOPUser user) {
        System.out.println("POST Method: ");
        System.out.println(user);

        // afterReturning JoinPoint 때문에 리턴값 생성
        return user;

        // http://localhost:9090/aop/post
        /* BODY :
            {
                 "id": "leew",
                 "pw": "1234",
                 "email": "leew@gmail.com"
            }
         */
    }
}
