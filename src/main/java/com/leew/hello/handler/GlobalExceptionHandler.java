package com.leew.hello.handler;

import com.leew.hello.exception.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class) // 해당 Exception이 터지면 다 여기로 와서 처리한다.
    public ResponseEntity<String> authException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        // 401이 나와야 하는데, 500 뜬다. 여기를 거치지 않고 그냥 서버 에러로 다뤄짐
    }
}
