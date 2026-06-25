package com.example.demo.mapper;

import com.example.demo.dto.DoctorRequestDTO;
import com.example.demo.dto.DoctorResponseDTO;
import com.example.demo.entity.Doctor;

public class DoctorMapper {

    // DTO → Entity
    public static Doctor toEntity(DoctorRequestDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setEmail(dto.getEmail());
        return doctor;
    }

    // Entity → DTO
    public static DoctorResponseDTO toDTO(Doctor doctor) {
        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getEmail()
        );
    }
}