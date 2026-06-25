package com.example.demo.service;

import com.example.demo.dto.PatientRequestDTO;
import com.example.demo.dto.PatientResponseDTO;

import java.util.List;

public interface PatientService {

    PatientResponseDTO save(PatientRequestDTO patient);

    List<PatientResponseDTO> getAll();
}