package com.example.demo.service;

import com.example.demo.dto.DoctorRequestDTO;
import com.example.demo.dto.DoctorResponseDTO;

import java.util.List;

public interface DoctorService {

    DoctorResponseDTO saveDoctor(DoctorRequestDTO doctor);

    List<DoctorResponseDTO> getAllDoctors();
}