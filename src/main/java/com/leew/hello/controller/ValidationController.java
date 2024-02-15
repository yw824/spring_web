package com.leew.hello.controller;

import com.leew.hello.dto.ValidUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation")
public class ValidationController {
    // DTO : dto.ValidUser 사용

    // RequestBody로 들어오는 객체에 대해 Validation Annotation 작동시키려면
    // @Valid 어노테이션 추가 필요
    @PostMapping("/user")
    public Object post(@Valid @RequestBody ValidUser user, BindingResult bindingResult) {
        // Validation 과정에서 에러 발생하면, 검증 성공 못하면
        // -> 그 결과가 BindingResult 객체에 담긴다.
        // 이 객체가 없으면, 그냥 400 Bad Request 발생
        if (bindingResult.hasErrors()) {
            // 메세지 만든다.
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError; // 에러 중 필드 값 가져오기
                String message = objectError.getDefaultMessage();

                System.out.println("field: " + field.getField());
                System.out.println(message);

                sb.append("field: " + field.getField());
                sb.append("message: " + message);

            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }


        // http://localhost:9090/validation/user

        // 그런데form의 내용이 좀 이상하다. 클라이언트에서 서버로 잘못 보냄
        /*
            {
              "name": "홍길동",
              "age": 1300,
              "email": "hgd123",
              "phoneNumber": "01012345678",
              "reqYearMonth": "202402"
            }
         */

        // 그래서 유효성 검사를 이 폼에 넣기로 했다.
        /*
        if(user.getAge() >= 90) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        } // Controller 기능만 해야 하는데, 유효성 검사 기능도 포함
         */
        // 좋지 않은 코드 -> DTO 가서 유효성 보장을 위한 코드 수정

        // 유효성 보장 완료 후 정상 로직 수행
        System.out.println(user);
        return ResponseEntity.ok(user);
    }
}
