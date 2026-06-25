```md
# 🏥 Hospital Management System — Spring Boot (DTO + Clean Architecture)

## 📌 Overview

This is a Spring Boot backend project for managing:

- Doctors
- Patients
- Appointments

It demonstrates **real-world backend design principles**, including:

- DTO-based API design
- Validation handling
- Global exception handling
- Clean architecture layering
- JPA relationships
- Proper request/response separation

---

# 🧠 Architecture

We follow a layered architecture:

```

Controller → Service → Repository → Database
↑
DTO + Mapper Layer

````

### Why this architecture matters

- Controllers never expose entities
- Services handle business logic
- Repositories handle DB operations
- DTOs protect internal database structure
- Mappers convert between DTO ↔ Entity

---

# 🚀 TECH STACK

- Spring Boot 4.x
- Spring Web MVC
- Spring Data JPA
- Hibernate
- H2 Database
- Jakarta Validation
- Lombok
- Maven

---

# 🚀 PHASE 1 — DOCTOR MODULE

## 📦 Doctor Entity (Initial version)

```java
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;
    private String email;
}
````

---

## 📦 Validation Added (Before DTO)

```java
@NotBlank(message = "Doctor name is required")
private String name;

@NotBlank(message = "Specialization is required")
private String specialization;

@Email(message = "Invalid email format")
@NotBlank(message = "Email is required")
private String email;
```

---

## 🎮 Doctor Controller (Before DTO)

```java
@PostMapping
public Doctor addDoctor(@RequestBody Doctor doctor)
```

---

## 🚨 Problem Identified

* Entities were exposed directly
* API tightly coupled with database structure
* No response control

---

## ✅ Fix: DTO Layer Introduced

### DoctorRequestDTO

```java
public class DoctorRequestDTO {

    @NotBlank(message = "Doctor name is required")
    private String name;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
}
```

---

### DoctorResponseDTO

```java
public class DoctorResponseDTO {

    private Long id;
    private String name;
    private String specialization;
    private String email;
}
```

---

## 🔄 Controller (After DTO)

```java
@PostMapping
public DoctorResponseDTO addDoctor(@Valid @RequestBody DoctorRequestDTO dto)
```

---

## 🧪 Validation Response (Handled via Global Exception Handler)

```json
{
  "message": "Validation failed",
  "errors": {
    "name": "Doctor name is required",
    "email": "Invalid email format"
  }
}
```

---

# 🚀 PHASE 2 — PATIENT MODULE

## 📦 Patient Entity

```java
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Patient name is required")
    private String name;

    @Min(value = 0, message = "Age cannot be negative")
    private Integer age;

    @NotBlank(message = "Phone number is required")
    private String phone;
}
```

---

## 📦 Patient DTOs

### PatientRequestDTO

```java
public class PatientRequestDTO {

    @NotBlank(message = "Patient name is required")
    private String name;

    @Min(value = 0, message = "Age cannot be negative")
    private Integer age;

    @NotBlank(message = "Phone number is required")
    private String phone;
}
```

---

### PatientResponseDTO

```java
public class PatientResponseDTO {

    private Long id;
    private String name;
    private Integer age;
    private String phone;
}
```

---

## 🎮 Controller (After DTO)

```java
@PostMapping
public PatientResponseDTO add(@Valid @RequestBody PatientRequestDTO dto)
```

---

# 🚀 PHASE 3 — APPOINTMENT MODULE (MOST IMPORTANT)

## 📦 Appointment Entity

```java
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Appointment time is required")
    private LocalDateTime appointmentTime;

    private String status;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
```

---

## 📦 Appointment DTOs

### AppointmentRequestDTO

```java
public class AppointmentRequestDTO {

    @NotNull
    private LocalDateTime appointmentTime;

    @NotNull
    private Long doctorId;

    @NotNull
    private Long patientId;

    private String status;
}
```

---

### AppointmentResponseDTO

```java
public class AppointmentResponseDTO {

    private Long id;
    private LocalDateTime appointmentTime;
    private String status;

    private Long doctorId;
    private String doctorName;

    private Long patientId;
    private String patientName;
}
```

---

# 🔄 MAPPER LAYER

## AppointmentMapper

```java
public class AppointmentMapper {

    public static Appointment toEntity(AppointmentRequestDTO dto, Doctor doctor, Patient patient) {

        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus(dto.getStatus() != null ? dto.getStatus() : "BOOKED");
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        return appointment;
    }

    public static AppointmentResponseDTO toDTO(Appointment appointment) {

        return new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getAppointmentTime(),
                appointment.getStatus(),
                appointment.getDoctor().getId(),
                appointment.getDoctor().getName(),
                appointment.getPatient().getId(),
                appointment.getPatient().getName()
        );
    }
}
```

---

# ⚙️ SERVICE LAYER

## Appointment Flow Logic

1. Receive DTO
2. Fetch Doctor from DB
3. Fetch Patient from DB
4. Create Appointment
5. Save to DB
6. Return ResponseDTO

---

### Core Logic

```java
Doctor doctor = doctorRepo.findById(dto.getDoctorId())
        .orElseThrow(() -> new RuntimeException("Doctor not found"));

Patient patient = patientRepo.findById(dto.getPatientId())
        .orElseThrow(() -> new RuntimeException("Patient not found"));

Appointment appointment = AppointmentMapper.toEntity(dto, doctor, patient);

Appointment saved = repository.save(appointment);

return AppointmentMapper.toDTO(saved);
```

---

# 🎮 CONTROLLERS

## AppointmentController

```java
@PostMapping
public AppointmentResponseDTO book(@Valid @RequestBody AppointmentRequestDTO dto)

@GetMapping
public List<AppointmentResponseDTO> getAll()
```

---

# 🌐 GLOBAL EXCEPTION HANDLING

## GlobalExceptionHandler

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Validation failed");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
```

---

# 🧪 API TESTING (cURL)

## Doctor

```bash
curl -X POST http://localhost:8080/doctors \
-H "Content-Type: application/json" \
-d '{"name":"Dr Ali","specialization":"Cardiology","email":"ali@test.com"}'
```

---

## Patient

```bash
curl -X POST http://localhost:8080/patients \
-H "Content-Type: application/json" \
-d '{"name":"Ahmed","age":30,"phone":"03001234567"}'
```

---

## Appointment

```bash
curl -X POST http://localhost:8080/appointments \
-H "Content-Type: application/json" \
-d '{
  "appointmentTime": "2026-06-25T14:30:00",
  "doctorId": 1,
  "patientId": 1,
  "status": "BOOKED"
}'
```

---

# ⚠️ REAL ISSUES ENCOUNTERED

## 1. Compilation Errors after DTO migration

Cause:

* Service layer still used entity-based logic

Fix:

* Updated services to use DTO + Mapper

---

## 2. Empty JSON (`{}`) responses

Cause:

* Missing getters in DTOs

Fix:

* Added proper getters or Lombok annotations

---

## 3. Maven build failure

Cause:

* Wrong directory execution

Fix:

```bash
cd demo
mvn clean install
```

---

## 4. Broken service layer after refactor

Cause:

* Mixing entity-based and DTO-based architecture

Fix:

* Fully standardized DTO flow across all layers

---

# 🧠 KEY LEARNINGS

* DTOs are mandatory for production APIs
* Entities must NEVER be exposed directly
* Jackson requires getters for JSON serialization
* Mapper layer isolates transformation logic
* Service layer = business logic only
* Validation must be centralized using `@RestControllerAdvice`

---

# 🚀 FINAL ARCHITECTURE

```
Controller (DTO)
      ↓
Service (Business Logic)
      ↓
Mapper (Conversion Layer)
      ↓
Entity
      ↓
Repository
      ↓
Database
```

---

# 🎯 FINAL STATUS

✔ Doctor Module — COMPLETE
✔ Patient Module — COMPLETE
✔ Appointment Module — COMPLETE
✔ DTO Architecture — IMPLEMENTED
✔ Validation — ACTIVE
✔ Global Exception Handling — ACTIVE
✔ Clean Architecture — ACHIEVED

---

# 🚀 NEXT POSSIBLE IMPROVEMENTS

* Authentication (Spring Security + JWT)
* Role-based access (Admin / Doctor / Patient)
* Appointment conflict detection
* Pagination + filtering
* Swagger API documentation
* Email notifications system

---

```

---

If you want next upgrade, I can help you build:

👉 Swagger UI (API documentation like real companies use)  
👉 JWT Authentication (login system)  
👉 Admin dashboard APIs  
👉 Or convert this into GitHub-ready README + badges