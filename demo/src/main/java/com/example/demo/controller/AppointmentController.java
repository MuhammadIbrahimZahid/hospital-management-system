package com.example.demo.controller;

import com.example.demo.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.dto.AppointmentResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping
    public AppointmentResponseDTO book(@Valid @RequestBody AppointmentRequestDTO dto) {
        return service.book(dto);
    }

    @GetMapping
    public List<AppointmentResponseDTO> getAll() {
        return service.getAll();
    }
}