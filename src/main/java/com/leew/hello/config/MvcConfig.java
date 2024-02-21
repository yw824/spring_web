package com.leew.hello.config;

import com.leew.hello.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor // final로 선언된 멤버변수를 샌성자에서 주입받을 수 있다.
public class MvcConfig implements WebMvcConfigurer {
    /**
     * AuthInterceptor가 작동하지 않는다. 작동시키기 위한 설정 사항 세팅
     */
    // 순환 참조가 발생할 수 있기 때문에 AutoWired를 넣지 않고 RequiredArgsConstructor로 해결
            // 생성자에서 주입받도록 한다.
    private final AuthInterceptor authInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
    }
}
