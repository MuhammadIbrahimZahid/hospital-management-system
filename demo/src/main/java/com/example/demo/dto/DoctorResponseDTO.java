package com.example.demo.dto;

public class DoctorResponseDTO {

    private Long id;
    private String name;
    private String specialization;
    private String email;

    public DoctorResponseDTO() {}

    public DoctorResponseDTO(Long id, String name, String specialization, String email) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getEmail() {
        return email;
    }
}