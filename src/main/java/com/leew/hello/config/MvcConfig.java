package com.leew.hello.config;

import com.leew.hello.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.Executor;

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
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/access/private"); // 특정 패턴에서만 작동하거나 특정 패턴만 제외하는 코드
            // add이기 때문에, 여러 개 넣으면, 넣은 앞 순서부터 뒤 순서대로 Interceptor 순차적으로 진행
    }

    @Bean("async-thread")
    public Executor asyncThread() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(10);
        threadPoolTaskExecutor.setThreadNamePrefix("Async--");
        return threadPoolTaskExecutor;
    }
}
