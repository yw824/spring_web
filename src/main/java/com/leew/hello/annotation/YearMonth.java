package com.leew.hello.annotation;

// Validation으로 사용했던 Annotation 아무거나(@Email, @Pattern, @Size 등,, 에서 클래스 모양 베껴온다.)


import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.AssertTrue.List;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { })
public @interface YearMonth {

    String message() default "yyyyMM";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // 패턴 유효성 체크 위함 - pattern annotation에서 꺼내옴
    String pattern() default "yyyyMM";
    // 해당 Annotation에 패턴을 명시하지 않는다면
    // 기본 패턴 명시자로 yyyyMM 사용
}
