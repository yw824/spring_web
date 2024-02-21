package com.leew.hello.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object Handler
    ) throws Exception {
        /**
        Filter 단에서 ContentCachingRequestWrapper로 Requset/Response 형변환 안하고 넘겼을 때
         여기서 형변환 안하면 문제 발생 (이전 Commit)
         ServletHttpRequest를 그대로 넘기면, Controller / Handler에서는 형변환 불가
         (Filter에서만 형변환이 가능하기 때문)
         */
        String url = request.getRequestURI();
        log.info("Request url: {}", url);

        // Filter 단위에서 캐시로 넘겨준다.

        return true; // 여기가 false이면 Private Controller에서 아무 값도 리턴되지 않는다.
    }

    private boolean checkAnnotation(Object handler, Class clazz) {
        // resource javascript, html 등을 요청할 때는 무조건 통과
        if(handler instanceof ResourceHttpRequestHandler) return true;

        // annotation이 달려 있는가 check
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(handlerMethod.getMethodAnnotation(clazz) != null ||
                null != handlerMethod.getBeanType().getAnnotation(clazz)
        ) { // Auth Annotation이 있을 때 true
            return true;
        }
        return false;
    }
}
