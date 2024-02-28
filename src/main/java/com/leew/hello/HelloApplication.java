package com.leew.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Base64;

@SpringBootApplication
@ServletComponentScan
@EnableAsync // 이렇게 어노테이션 부여하여, 내부 코드에서 비동기 로직이 가능하도록 설정하는 경우 있음
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
        // System.out.println(Base64.getEncoder().encodeToString("leew@gmail.com".getBytes()));
        // bGVld0BnbWFpbC5jb20=
    }

}
