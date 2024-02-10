package com.leew.hello.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

// 해당 메소드의 실행 시간을 기록
@Aspect
@Component
public class TimerAop {
    @Pointcut("execution (* com.leew.hello.controller.AOPController.*(..))")
    private void cut() {

    }

    @Pointcut("@annotation(com.leew.hello.annotation.Timer)")
    private void enableTimer() {

    }

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(); // 스톱워치 시작

        Object result = joinPoint.proceed(); // proceed는 Throwable 던지게끔 코드 작성해야 함
        // 해당 Pointcut을 일으킨 JoinPoint를 진행시킨다.
        // 해당 함수가 완료되면, 다시 여기로 돌아온다. (리턴값이 result가 된다.)

        stopWatch.stop(); // 스톱워치 종료
        System.out.println("total time: " + stopWatch.getTotalTimeSeconds());
    }
}
