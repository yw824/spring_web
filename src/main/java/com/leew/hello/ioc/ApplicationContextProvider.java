package com.leew.hello.ioc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component // 얘도 컴포넌트로 등록해야 한다. 그래야 알아서 관리해줌
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    // 인터페이스 구현체이기 때문에 구현해야 하는 함수, 구현 안하면 에러 발생
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 얘도 외부로부터(스프링이 알아서 주입해줌) 주입을 받는다.(ApplicationContext)
        this.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
