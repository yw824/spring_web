package com.leew.hello.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// 이번에는 컴포넌트로 두지 않고,
// bean으로 Configuration 클래스(class AppConfig)에서 직접 등록시키려 한다.
public class Encoder {

    private IEncoder iEncoder;

    public Encoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    public void setiEncoder(IEncoder iEncoder) { // setter
        this.iEncoder = iEncoder;
    } // 한 번 생성하면 고정되는 것이 아니라, 다른 Encoder를 받아오면, 그렇게 작동

    public String encode(String message) {
        return iEncoder.encode(message);
    }
}


