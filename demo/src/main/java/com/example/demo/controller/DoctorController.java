package com.example.demo.controller;

import com.example.demo.dto.DoctorRequestDTO;
import com.example.demo.dto.DoctorResponseDTO;
import com.example.demo.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @PostMapping
    public DoctorResponseDTO addDoctor(@Valid @RequestBody DoctorRequestDTO dto) {
        return service.saveDoctor(dto);
    }

    @GetMapping
    public List<DoctorResponseDTO> getDoctors() {
        return service.getAllDoctors();
    }
}