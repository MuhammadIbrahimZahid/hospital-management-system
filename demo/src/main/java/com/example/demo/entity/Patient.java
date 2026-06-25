package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Patient name is required")
    private String name;

    @Min(value = 0, message = "Age cannot be negative")
    private Integer age;

    @NotBlank(message = "Phone number is required")
    private String phone;
}