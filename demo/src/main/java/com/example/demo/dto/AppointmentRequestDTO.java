package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AppointmentRequestDTO {

    @NotNull
    private LocalDateTime appointmentTime;

    @NotNull
    private Long doctorId;

    @NotNull
    private Long patientId;

    private String status;

    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }

    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}