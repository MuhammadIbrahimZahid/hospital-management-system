package com.example.demo.dto;

public class PatientResponseDTO {

    private Long id;
    private String name;
    private Integer age;
    private String phone;

    public PatientResponseDTO() {}

    public PatientResponseDTO(Long id, String name, Integer age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }
}