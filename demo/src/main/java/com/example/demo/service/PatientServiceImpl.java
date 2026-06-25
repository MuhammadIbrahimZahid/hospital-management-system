package com.example.demo.service;

import com.example.demo.dto.PatientRequestDTO;
import com.example.demo.dto.PatientResponseDTO;
import com.example.demo.entity.Patient;
import com.example.demo.mapper.PatientMapper;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;

    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public PatientResponseDTO save(PatientRequestDTO dto) {
        Patient patient = PatientMapper.toEntity(dto);
        Patient saved = repository.save(patient);
        return PatientMapper.toDTO(saved);
    }

    @Override
    public List<PatientResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList());
    }
}