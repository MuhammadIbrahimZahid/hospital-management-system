# 🏥 Hospital Management System – Development & Debugging Log

## 📌 Project Overview

This project is a Spring Boot REST API-based Hospital Management System built using:

- Java (Spring Boot)
- Spring Web (REST APIs)
- Spring Data JPA (Hibernate ORM)
- H2 In-memory Database
- Maven
- GitHub Codespaces

The system manages:
- Doctors
- Patients
- Appointments

---

# ⚙️ 1. Development Environment Setup

## 📌 Tools Used

- Java 25 (OpenJDK)
- Maven 3.9.13
- GitHub Codespaces (Linux container)

## 📌 Verification

Both Java and Maven were successfully installed and verified:

- `java -version` → OK
- `mvn -v` → OK

This confirmed the environment was ready for Spring Boot development.

---

# 📦 2. Project Initialization

The project was generated using Spring Initializr.

## Dependencies added:

- Spring Web
- Spring Data JPA
- H2 Database
- Validation
- Lombok

---

# 📌 3. First Issue – Invalid ZIP File

## ❌ Problem

Spring Initializr ZIP extraction failed:

# End-of-central-directory signature not found


## 🔍 Cause

Corrupted or incomplete ZIP download.

## ✔ Fix

- Re-downloaded correct ZIP
- Extracted successfully using unzip
- Verified folder structure

---

# 📌 4. First Application Run Issue

## ❌ Problem

Application started but returned:

# Whitelabel Error Page (404)


## 🔍 Cause

No REST controllers were defined.

## ✔ Fix

Created controllers:

- DoctorController
- PatientController
- AppointmentController

---

# 📌 5. First Working API Test

## Doctor API

```bash
POST /doctors

# Response:

{
  "id": 1,
  "name": "Dr Ali",
  "specialization": "Cardiology",
  "email": "ali@test.com"
}

# Patient API

POST /patients

{
  "id": 1,
  "name": "Ahmed",
  "age": 25,
  "phone": "03001234567"
}

# 📌 6. Appointment API Working

POST /appointments

# Request:

{
  "appointmentTime": "2026-06-25T10:00:00",
  "doctor": { "id": 1 },
  "patient": { "id": 1 }
}

# Response:

{
  "id": 1,
  "appointmentTime": "2026-06-25T10:00:00",
  "status": "BOOKED",
  "doctor": {...},
  "patient": {...}
}

# 📌 7. Major Bug – Entity Relationship Issue

❌ Problem

Appointments were not properly linked with Doctor and Patient.

🔍 Cause

Missing JPA relationships.

✔ Fix

@ManyToOne
@JoinColumn(name = "doctor_id")
private Doctor doctor;

@ManyToOne
@JoinColumn(name = "patient_id")
private Patient patient;

# 📌 8. Major Bug – Invalid Entity Saving

❌ Problem

Using raw JSON objects caused incorrect persistence.

✔ Fix

Always fetch real entities:

Doctor doctor = doctorRepo.findById(id).orElseThrow();
Patient patient = patientRepo.findById(id).orElseThrow();

appointment.setDoctor(doctor);
appointment.setPatient(patient);
appointment.setStatus("BOOKED");

# 📌 9. Major Bug – Infinite JSON Loop

❌ Problem

Response recursion:

Doctor → Appointments → Doctor → Appointments

✔ Fix

@JsonIgnore
private List<Appointment> appointments;

# 📌 10. Codespaces Issue – Port Access

❌ Problem

API not accessible externally.

✔ Fix
Changed port 8080 from PRIVATE → PUBLIC in Codespaces
Used forwarded URL

# 📌 11. Final System Architecture

Controller → Service → Repository → Database

# 📌 12. Key Learnings

Spring Boot REST API structure
JPA relationships (OneToMany, ManyToOne)
Entity lifecycle management
Debugging 404 / 500 errors
JSON serialization issues
Cloud development using Codespaces

# 📌 13. Final Status

✔ Application running
✔ APIs working
✔ Database integration working
✔ Appointment system functional
✔ All major bugs resolved