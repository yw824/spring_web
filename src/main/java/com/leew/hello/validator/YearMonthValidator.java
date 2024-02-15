package com.leew.hello.validator;

import com.leew.hello.annotation.YearMonth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// YearMonth 값 유효성을 처리해 주는 클래스
// 해당하는 Annotation과 확인해야 하는 값을 설정
public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

    private String pattern; // 받는 값

    // Override만 설정해줬더니, 그, Constraint 자료형이였는데, YearMonth Constraint로 설정하니 에러 안남
    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    // 맨 처음에 Override 메서드만 불러왔더니 value가 Object였고, 에러 남
    // value를 String으로 바꿔줬더니, 에러 안남 -> 아마도 Constraintvalidator의 value 값이 Stringd
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("YearMonthValidator: isValid Running...");

        // yyyyMM 패턴이 잘 적용되었는지 확인 , ValidUser의 AssertTrue의 코드 가져온다.
        try { // 날짜까지 들어가야 한다. 그래서 내부적으로 사전 작업
            LocalDate localDate = LocalDate.parse(
                    value + "01", // 파라미터로 받은 value yyyyMM 파라미터를 받으면 된다.
                    DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
