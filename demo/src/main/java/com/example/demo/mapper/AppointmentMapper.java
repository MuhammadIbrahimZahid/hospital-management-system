package com.example.demo.mapper;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.dto.AppointmentResponseDTO;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;

public class AppointmentMapper {

    // DTO → Entity (requires Doctor & Patient already resolved)
    public static Appointment toEntity(AppointmentRequestDTO dto, Doctor doctor, Patient patient) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus(dto.getStatus() != null ? dto.getStatus() : "BOOKED");
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        return appointment;
    }

    // Entity → DTO
    public static AppointmentResponseDTO toDTO(Appointment appointment) {
        return new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getAppointmentTime(),
                appointment.getStatus(),
                appointment.getDoctor().getId(),
                appointment.getDoctor().getName(),
                appointment.getPatient().getId(),
                appointment.getPatient().getName()
        );
    }
}