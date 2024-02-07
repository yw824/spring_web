package com.leew.hello.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    @DeleteMapping("/delete")
    public void delete() {

    }
}
