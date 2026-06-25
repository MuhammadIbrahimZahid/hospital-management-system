package com.example.demo.dto;

import java.time.LocalDateTime;

public class AppointmentResponseDTO {

    private Long id;
    private LocalDateTime appointmentTime;
    private String status;

    private Long doctorId;
    private String doctorName;

    private Long patientId;
    private String patientName;

    public AppointmentResponseDTO() {}

    public AppointmentResponseDTO(Long id,
                                  LocalDateTime appointmentTime,
                                  String status,
                                  Long doctorId,
                                  String doctorName,
                                  Long patientId,
                                  String patientName) {
        this.id = id;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientId = patientId;
        this.patientName = patientName;
    }

    // getters only

    public Long getId() {
        return id;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }
}