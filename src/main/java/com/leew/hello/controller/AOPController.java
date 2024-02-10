package com.leew.hello.controller;

import com.leew.hello.annotation.Decode;
import com.leew.hello.annotation.Timer;
import com.leew.hello.dto.AOPUser;
import org.springframework.util.StopWatch;
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

    @Timer // Timer라는, 우리가 직접 만든 Annotation을 붙인다.
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {
        // db logic : 2초 걸린다-라고 가정. - 임의로 sleep 2초 걸기

        // StopWatch stopWatch = new StopWatch();
        // stopWatch.start();

        Thread.sleep(1000 * 2);

        // stopWatch.stop();
        // System.out.println("In DeleteAPI - Total Time: " + stopWatch.getTotalTimeSeconds());
    }

    @Decode
    @PutMapping("/put") // Decode 위한 Put API 새로 생성
    public AOPUser put(@RequestBody AOPUser user) {
        System.out.println("PUT Method: ");
        System.out.println(user);

        // afterReturning JoinPoint 때문에 리턴값 생성
        return user;

        // http://localhost:9090/aop/put
        /* BODY :
            {
                 "id": "leew",
                 "pw": "1234",
                 "email": "bGVld0BnbWFpbC5jb20="
            }
         */
    }
}
