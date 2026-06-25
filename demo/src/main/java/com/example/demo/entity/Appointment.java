package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Appointment time is required")
    private LocalDateTime appointmentTime;

    private String status; // BOOKED, CANCELLED, COMPLETED

    // MANY appointments → ONE doctor
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @NotNull(message = "Doctor is required")
    private Doctor doctor;

    // MANY appointments → ONE patient
    @ManyToOne
    @JoinColumn(name = "patient_id")
    @NotNull(message = "Patient is required")
    private Patient patient;
}