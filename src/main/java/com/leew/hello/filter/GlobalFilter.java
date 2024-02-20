package com.leew.hello.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns= "/filter/*")
public class GlobalFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 전처리

        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper(
                (HttpServletRequest) request
        );
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper(
                (HttpServletResponse) response
        );

        // 비즈니스 로직 : chain
        chain.doFilter(httpServletRequest, httpServletResponse);

        // 후처리
        String url = httpServletRequest.getRequestURI();

        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request URL: {}, Request Body: {}", url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse(); // 다시 Response에 복사
        log.info("response status: {}, responseBody: {}", httpStatus, resContent);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
