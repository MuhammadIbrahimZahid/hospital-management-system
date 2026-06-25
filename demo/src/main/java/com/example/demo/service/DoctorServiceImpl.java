package com.example.demo.service;

import com.example.demo.dto.DoctorRequestDTO;
import com.example.demo.dto.DoctorResponseDTO;
import com.example.demo.entity.Doctor;
import com.example.demo.mapper.DoctorMapper;
import com.example.demo.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repository;

    public DoctorServiceImpl(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public DoctorResponseDTO saveDoctor(DoctorRequestDTO dto) {
        Doctor doctor = DoctorMapper.toEntity(dto);
        Doctor saved = repository.save(doctor);
        return DoctorMapper.toDTO(saved);
    }

    @Override
    public List<DoctorResponseDTO> getAllDoctors() {
        return repository.findAll()
                .stream()
                .map(DoctorMapper::toDTO)
                .collect(Collectors.toList());
    }
}