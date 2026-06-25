package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Doctor name is required")
    private String name;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private java.util.List<Appointment> appointments;
}