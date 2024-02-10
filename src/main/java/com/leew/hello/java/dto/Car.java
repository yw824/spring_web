package com.leew.hello.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String name;
    @JsonProperty("car_number")
    private String carNumber;
    @JsonProperty("type")
    private String type;

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", carName='" + carNumber + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
