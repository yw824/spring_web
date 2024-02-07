package com.leew.hello.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;

import com.leew.hello.dto.carDTO;

// @JsonNaming(value=PropertyNamingStrategy.SnakeCaseStrategy.class) // deprecated
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PutRequestDTO {
    private String name;
    private int age;
    private List<carDTO> carList;

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

    public List<carDTO> getCarList() {
        return carList;
    }

    public void setCarList(List<carDTO> carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        return "PutRequestDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", carList=" + carList +
                '}';
    }
}
