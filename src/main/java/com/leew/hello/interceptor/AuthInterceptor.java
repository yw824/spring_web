package com.leew.hello.interceptor;

import com.leew.hello.annotation.Auth;
import com.leew.hello.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        /**
        Filter 단에서 ContentCachingRequestWrapper로 Requset/Response 형변환 안하고 넘겼을 때
         여기서 형변환 안하면 문제 발생 (이전 Commit)
         ServletHttpRequest를 그대로 넘기면, Controller / Handler에서는 형변환 불가
         (Filter에서만 형변환이 가능하기 때문)
         */
        String url = request.getRequestURI();
        log.info("Request url: {}", url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has Annotation: {}", hasAnnotation);

        // 나의 서버는 모두 public으로 동작하는데,
        // Auth 권한을 가진 요청에 대해서는 세션, 쿠키, 등 요청이 필요함

        URI uri = UriComponentsBuilder.fromUriString(
                request.getRequestURI()
        ).query(request.getQueryString()).build().toUri();
        // URI와 Query를 모두 가져오는 코드
        // URI : 요청 주소 // Query : 요청 내용

        if(hasAnnotation) { // 권한 체크 필요성이 있는 경우에
            // 권한 체크 : query 내용이 동일하면 통과시키도록 하자. (임의의 하나의 기준 설정)
            String query = uri.getQuery(); // name=scott : query
            if(query.equals("name=scott")) {
                return true;
            } // http://localhost:9090/access/private?name=scott

            // return false; // 다른 코드이면, 200이지만, BODY가 없음 : 예외 던지기 및 처리 필수

            // false를 반환하는 것이 아니라 이번에는 Exception을 throw
            throw new AuthException();
        }

        // 권한 처리를 통과하였거나 권한 처리가 필요없는 경우에는 바로 통과, true 리턴
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
