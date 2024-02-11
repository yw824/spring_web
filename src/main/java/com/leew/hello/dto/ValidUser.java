package com.leew.hello.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

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

    @Override
    public String toString() {
        return "ValidUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
