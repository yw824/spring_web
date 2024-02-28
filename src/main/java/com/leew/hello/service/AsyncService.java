package com.leew.hello.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncService {
    @Async // 비동기로 동작하도록
    public CompletableFuture hello() {
        for(int i = 0; i < 10; ++i) {
            try {
                Thread.sleep(2000); // 2초 동안 sleep
                log.info("thread sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         }
    }
}
