package com.example.hello.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectMapperTestDto {

    private String name;
    private int age;

    @JsonProperty("phone_number")
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ObjectMapperTestDto() {

    }

    public ObjectMapperTestDto(String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ObjectMapperTestDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
