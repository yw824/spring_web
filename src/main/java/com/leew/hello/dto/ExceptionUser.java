package com.leew.hello.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ExceptionUser {

    @NotEmpty
    @Size(min=1, max=100)
    private String name;
    @Min(value=1)
    @NotNull // Exception 시나리오를 위해 Integer로 변경 + NotNull 설정
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ExceptionUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
