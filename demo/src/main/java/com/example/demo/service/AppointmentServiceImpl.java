package com.example.demo.service;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.dto.AppointmentResponseDTO;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public AppointmentServiceImpl(AppointmentRepository repo,
                                  DoctorRepository doctorRepo,
                                  PatientRepository patientRepo) {
        this.repo = repo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    @Override
    public AppointmentResponseDTO book(AppointmentRequestDTO dto) {

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setStatus(dto.getStatus() != null ? dto.getStatus() : "BOOKED");

        Appointment saved = repo.save(appointment);

        return new AppointmentResponseDTO(
                saved.getId(),
                saved.getAppointmentTime(),
                saved.getStatus(),
                doctor.getId(),
                doctor.getName(),
                patient.getId(),
                patient.getName()
        );
    }

    @Override
    public List<AppointmentResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(a -> new AppointmentResponseDTO(
                        a.getId(),
                        a.getAppointmentTime(),
                        a.getStatus(),
                        a.getDoctor().getId(),
                        a.getDoctor().getName(),
                        a.getPatient().getId(),
                        a.getPatient().getName()
                ))
                .collect(Collectors.toList());
    }
}