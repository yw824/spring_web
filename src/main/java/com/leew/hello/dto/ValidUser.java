package com.leew.hello.dto;

import com.leew.hello.annotation.YearMonth;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ValidUser {

    private String name;
    @Min(value=0, message="나이는 최소 0살이어야 합니다.") // 최소값 0살이여야 한다.
    @Max(value=90, message="나이는 최대 90살까지 입력 가능합니다.") // 최대값 90살이어야 한다.
    private int age;
    // 이메일의 형식에 맞지 않으면 에러 발생
    @Email
    private String email;
    // 정규식 패턴을 유효성 패턴으로 사용하기 위한 Annotation
    @Pattern(regexp="^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String phoneNumber;

    // @Size(min=6, max=6)
    @YearMonth
    private String reqYearMonth; // 요청 시간(yyyyMM)
    // 그러나, 6글자라고 다 연도-월 일 수는 없다. 상식에 맞는 데이터인지, 검증하는 추가 함수가 필요하다.
    // -> YearMonthValidation.isValid 함수 수행 -> YearMonth의 ConstraintValidator 가졌기 때문
    // implements ConstraintValidator(<YearMonth, String>)

    private List<carDTO> cars;

    public List<carDTO> getCars() {
        return cars;
    }

    public void setCars(List<carDTO> cars) {
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReqYearMonth() {
        return reqYearMonth;
    }

    public void setReqYearMonth(String reqYearMonth) {
        this.reqYearMonth = reqYearMonth;
    }

    @Override
    public String toString() {
        return "ValidUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqYearMonth='" + reqYearMonth + '\'' +
                ", cars=" + cars +
                '}';
    }
}
