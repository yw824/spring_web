package com.leew.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Base64;

@SpringBootApplication
@ServletComponentScan
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
        // System.out.println(Base64.getEncoder().encodeToString("leew@gmail.com".getBytes()));
        // bGVld0BnbWFpbC5jb20=
    }

}
