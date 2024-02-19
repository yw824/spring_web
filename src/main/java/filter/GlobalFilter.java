package filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@Component
public class GlobalFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("Filter Task Started");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // ServletRequest, ServletResponse로 req, res 값을 그대로 받을 수 있다.
        // 전처리 구간

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String url = httpServletRequest.getRequestURI();


        BufferedReader br = httpServletRequest.getReader();
        br.lines().forEach(line -> {
            log.info("url : {}, Line : {}", url, line);
        });

        // 이후 일련의 비즈니스(서비스) 로직 수행
        chain.doFilter(request, response);

        // 후처리 공간 - 에러 날 것이기 때문에 조금 있다가 실습 수행
    }

    @Override
    public void destroy() {
        log.info("Filter Task Done");
        Filter.super.destroy();
    }
}
