package com.example.demo.mapper;

import com.example.demo.dto.PatientRequestDTO;
import com.example.demo.dto.PatientResponseDTO;
import com.example.demo.entity.Patient;

public class PatientMapper {

    public static Patient toEntity(PatientRequestDTO dto) {
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setPhone(dto.getPhone());
        return patient;
    }

    public static PatientResponseDTO toDTO(Patient patient) {
        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getPhone()
        );
    }
}