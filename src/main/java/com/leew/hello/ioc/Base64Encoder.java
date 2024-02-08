package com.leew.hello.ioc;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("base64Encoder") // 붙이면 : 스프링부트에서 관리를 해줘~~ : Bean으로 설정
public class Base64Encoder implements IEncoder {
    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
