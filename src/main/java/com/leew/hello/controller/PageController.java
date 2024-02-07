package com.leew.hello.controller;

import com.leew.hello.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // html 파일을 리턴하는 컨트롤러
public class PageController {

    @RequestMapping("/main")
    public String main() {
        return "main.html"; // resources.static 폴더 내의 main.html
        // Here is Main Page in static
    }

    @GetMapping("/main1")
    public String main1() {
        return "main"; // resources.templates 폴더 내의 main.html
        // Here is Main Page in templates
    }

    @ResponseBody
    @GetMapping("/user")
    public User user() {
        // http://localhost:9090/user

        var user = new User();

        user.setName("scott");
        // user.setAddress("LA");
        user.setAge(20);
        user.setPhoneNumber("010-1234-5678");

        return user;
    }
}
