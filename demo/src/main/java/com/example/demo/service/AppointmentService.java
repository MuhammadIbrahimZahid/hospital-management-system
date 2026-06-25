package com.example.demo.service;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.dto.AppointmentResponseDTO;

import java.util.List;

public interface AppointmentService {

    AppointmentResponseDTO book(AppointmentRequestDTO dto);

    List<AppointmentResponseDTO> getAll();
}