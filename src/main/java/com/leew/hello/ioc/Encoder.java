package com.leew.hello.ioc;

import org.springframework.stereotype.Component;

@Component // 스프링 컨테이너에서 IoC를 자동으로 수행하게끔 하는 Annotation
// 클래스명 왼쪽에 Spring 콩 모양이 붙으면, Bean으로 등록된다.
public class Encoder {

    private IEncoder iEncoder;

    public Encoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }
    public String encode(String message) {
        return iEncoder.encode(message);
    }
}
