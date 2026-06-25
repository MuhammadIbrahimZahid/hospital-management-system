package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PatientRequestDTO {

    @NotBlank(message = "Patient name is required")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age cannot be negative")
    private Integer age;

    @NotBlank(message = "Phone number is required")
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}