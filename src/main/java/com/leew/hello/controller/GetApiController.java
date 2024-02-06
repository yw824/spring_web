package com.leew.hello.controller;

import com.leew.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get") // http://localhost:9090/api/get/hello
public class GetApiController {
    @GetMapping(path="/hello")
    // RequestMapping Annotation : Get/Post/Put/Delete 모든 메소드에 동작
    // GetMapping 이라는 특정 메소드에만 적용될 수 있는 Annotation 사용
    public String getHello() {
        return "get Hello";
    }

    /* 옛날 방식
    @RequestMapping(path="/hi", method=RequestMethod.GET) // 옛날 방식
    public String hi() {
        return "hi";
    }
     */

    // http://localhost/api/get/pathvariable/{name} - 세부 페이지 작성 시 사용
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable("name") String pathName) {
        System.out.println("PathVariable: " + pathName);
        return pathName;
    }

    @GetMapping(path="query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {

        StringBuilder sb = new StringBuilder();
        queryParam.forEach((key, value) -> { // lambda 함수
            System.out.println(key);
            System.out.println(value);
            System.out.println("\n");

            sb.append(key).append(" = ").append(value).append("\n");
        });

        return sb.toString();
    }

    // QueryParam에 명확히 user, email, age만 넣을 수 있다고 지정을 한 경우
    @GetMapping(path="query-param1")
    public String queryParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age) {

        // age가 int형인 만큼, Talend API tester에서 age에 문자열 넣으면 에러 발생
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name + ", " + email + ", " + age + "\n";
    }

    @GetMapping(path="query-param2")
    public String queryParam2(UserRequest userRequest) {

        // age가 int형인 만큼, Talend API tester에서 age에 문자열 넣으면 에러 발생
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.getName() + ", " + userRequest.getEmail() + ", " + userRequest.getAge() + "\n";
    }
}
