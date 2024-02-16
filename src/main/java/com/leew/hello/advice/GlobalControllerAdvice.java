package com.leew.hello.advice;

import com.leew.hello.controller.ValidationController;
import com.leew.hello.dto.Error;
import com.leew.hello.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

// 특정 Controller에만 적용되도록 basePackageClasses 설정
@RestControllerAdvice(basePackageClasses = ValidationController.class) // 전체 Exception 다 잡을 예정
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
    // POST API의 값 없음 에러
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(
            MethodArgumentNotValidException e,
            HttpServletRequest httpServletRequest // HTTP URI 요청 정보 갖고 있는 객체
            ) {
        System.out.println("Exception Handler in Advice");

        List<Error> errorList = new ArrayList<>();

        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error -> {
            FieldError field = (FieldError) error;

            String fieldName = field.getField();
            String message = field.getDefaultMessage();
            String value = field.getRejectedValue().toString(); // 어떤 값이 잘못 들어갔는 지 확인

            Error errorMessage = new Error();
            errorMessage.setField(fieldName);
            errorMessage.setMessage(message);
            errorMessage.setInvalidValue(value);

            errorList.add(errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // GET API에서 값은 있는데, Valiation 조건에 맞지 않아 에러가 발생
    @ExceptionHandler(value= ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(
            ConstraintViolationException e,
            HttpServletRequest httpServletRequest) { // HTTP URI 요청 정보 갖고 있는 객체
        // 이 Exception : 어떠한 필드가 잘못되었는 지 정보를 담고 있다.
        System.out.println("ConstraintViolationError");

        List<Error> errorList = new ArrayList<>();

        // 밑 문장에서 checkpoint 설정해 놓고 디버깅하면, 어떤 객체들이 있는지 알 수 있다.
        e.getConstraintViolations().forEach(error -> {

            // System.out.println(error.getPropertyPath()); // get.name, //get.age
            // System.out.println(error.getPropertyPath().spliterator());

            Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(), false);
            List<Path.Node> list = stream.collect(Collectors.toList());

            String field = list.get(list.size() - 1).getName();
            String message = error.getMessage();
            String invalidMessage = error.getInvalidValue().toString();

            Error errorMessage = new Error();
            errorMessage.setField(field);
            errorMessage.setMessage(message);
            errorMessage.setInvalidValue(invalidMessage);

            errorList.add(errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // GET API의 값 없음 에러
    @ExceptionHandler(value=MissingServletRequestParameterException.class)
    public ResponseEntity missingServletRequestParameterException(
            MissingServletRequestParameterException e,
            HttpServletRequest httpServletRequest
            ) {

        List<Error> errorList = new ArrayList<>();

        String fieldName = e.getParameterName();
        String invalidValue = e.getMessage();

        Error errorMessage = new Error();
        errorMessage.setField(fieldName);
        errorMessage.setMessage(e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
