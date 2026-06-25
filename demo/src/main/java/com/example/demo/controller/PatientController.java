package com.example.demo.controller;

import com.example.demo.dto.PatientRequestDTO;
import com.example.demo.dto.PatientResponseDTO;
import com.example.demo.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping
    public PatientResponseDTO add(@Valid @RequestBody PatientRequestDTO dto) {
        return service.save(dto);
    }

    @GetMapping
    public List<PatientResponseDTO> getAll() {
        return service.getAll();
    }
}