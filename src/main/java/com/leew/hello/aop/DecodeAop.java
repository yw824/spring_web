package com.leew.hello.aop;

import com.leew.hello.dto.AOPUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    @Pointcut("execution (* com.leew.hello.controller.AOPController.*(..))")
    private void cut() {

    }

    @Pointcut("@annotation(com.leew.hello.annotation.Decode)")
    private void enableDecode() {

    }

    // 값을 찾아야 한다.
    // 전 : Decoding -> 후 : Encoding
    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();

        for(Object arg: args) {
            if(arg instanceof AOPUser) {
                // 값을 바꿔준다.
                AOPUser user = AOPUser.class.cast(arg);
                String base64Email = user.getEmail();
                // 이메일 정보를 받아서, decode 해준다.
                String email = new String(Base64.getDecoder().decode(base64Email), "UTF-8");

                System.out.println("Decode - Email: " + email);
                // decode 해준 이메일 정보를 디코딩해서 다시 넣어준 후에 본래 함수에게 전달한다.
                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void after(JoinPoint joinPoint, Object returnObj) throws UnsupportedEncodingException {
        if(returnObj instanceof AOPUser) {
            // 값을 바꿔준다.
            AOPUser user = AOPUser.class.cast(returnObj);
            String email = user.getEmail();
            // 이메일 정보를 받아서, decode 해준다.
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());

            System.out.println("Encode Email: " + base64Email);
            // decode 해준 이메일 정보를 디코딩해서 다시 넣어준 후에 본래 함수에게 전달한다.
            user.setEmail(base64Email);
        }
    }
}
