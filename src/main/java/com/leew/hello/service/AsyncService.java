package com.leew.hello.service;

import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncService {
    // @Async // 비동기로 동작하도록
    // 비동기 방식을 수행하는데 비동식 annotation이 불필요하다,, ????
    public String hello() { // 리턴값은 해당 자료형으로 전달 가능
        for(int i = 0; i < 10; ++i) {
            try {
                Thread.sleep(2000); // 2초 동안 sleep
                log.info("thread sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         }
        return "Service: Hello";
    }

    @Async
    public CompletableFuture run() {
        return new AsyncResult(hello()).completable();
    }
}
