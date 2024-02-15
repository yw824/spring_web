package com.leew.hello.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice() // 전체 Exception 다 잡을 예정
// @ControllerAdvice // View-Resolver
public class GlobalControllerAdvice {

    // Rest API이기 때문에 ResponseEntity를 반환
    @ExceptionHandler(value=Exception.class) // 모든 예외를 다 잡기 때문에 Exception으로 생성
     public ResponseEntity exception(Exception e) {
        System.out.println("--------Exception--------");
        System.out.println(e.getClass().getName()); // 어디에 잘못된 예외인지 출
        System.out.println("-------------------------");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        // body에는 내용 담지 않는다.
    }

    // 위에서 출력한 에러 내용을 바탕으로 해당 에러를 처리하는 함수 생성
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println("Exception Handler in Advice");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
