package com.leew.hello.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component // Spring에서 관리하도록 Bean 등록
public class ParameterAop {

    @Pointcut("execution (* com.leew.hello.controller.AOPController.*(..))")
    private void cut() {

    }

    @Before("cut()") // 함수호출까지 적어주나 보다,,
    public void before(JoinPoint joinPoint) {
        // 메소드 실행되기 전 넘어가는 파라미터 확인
        Object[] args = joinPoint.getArgs();

        // method Signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("Method Before: " + method.getName());

        for (Object obj: args) {
            System.out.println("type: " + obj.getClass().getSimpleName());
            System.out.println("value: " + obj);
        }
    }

    @AfterReturning(value="cut()", returning = "returnObj") // 밑의 파라미터와 이름 같아야 함
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        // 메소드 실행된 후 넘어오는 리턴값 확인

        // 이거 쓰려면, GET Controller에서 리턴 값을 만들어줘야 한다.
        System.out.println("After Returning: ");
        System.out.println(returnObj); // null

    }
}
