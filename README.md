# Project Description

A Spring Boot REST API-based Hospital Management System that manages doctors, patients, and appointments using a clean layered architecture. The system follows DTO-based design, JPA relationships, validation handling, and global exception management to ensure production-style backend development practices.


# Hospital Management System (Spring Boot REST API)

A backend REST API system built with Spring Boot for managing hospital operations including doctors, patients, and appointments.

This project demonstrates real-world backend development practices using **clean architecture, DTO pattern, and JPA relationships**.

## Features

### Doctor Management
- Add new doctors
- View doctor details
- Validate doctor data

### Patient Management
- Register patients
- Retrieve patient records
- Input validation support

### Appointment System
- Book appointments between doctors and patients
- Fetch all appointments
- Prevent invalid relationships

## Tech Stack

- Java 25
- Spring Boot
- Spring Web (REST APIs)
- Spring Data JPA (Hibernate)
- H2 In-Memory Database
- Maven
- Jakarta Validation
- Lombok

## Architecture

The project follows a clean layered architecture:

Controller → Service → Mapper → Repository → Database

### Design Principles:
- DTO-based request/response structure
- Separation of concerns
- Global exception handling
- Entity isolation from API layer

## Project Structure

demo/
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
├── mapper/
├── exception/
├── resources/
│ └── application.properties
└── DemoApplication.java

## API Endpoints

### Doctor APIs

POST /doctors
GET /doctors

### Patient APIs

POST /patients
GET /patients

### Appointment APIs

POST /appointments
GET /appointments

## Sample API Request

### Create Doctor

json
POST /doctors
{
  "name": "Dr Ali",
  "specialization": "Cardiology",
  "email": "ali@test.com"
}

### Book Appointment

POST /appointments
{
  "appointmentTime": "2026-06-25T14:30:00",
  "doctorId": 1,
  "patientId": 1,
  "status": "BOOKED"
}

## How to Run

### 1. Clone Repository

bash
git clone https://github.com/your-username/hospital-management-system.git

### 2. Move into project

cd demo

### 3. Run application

mvn spring-boot:run

### 4. Access API

http://localhost:8080

Or use Codespaces forwarded URL.

## 🗄 Database

* H2 In-Memory Database
* No installation required
* Data resets on restart

## ⚠️ Key Notes

* Appointment requires valid Doctor ID
* Appointment requires valid Patient ID
* Port 8080 must be public in Codespaces
* Entities are not exposed directly (DTO pattern used)

## 🧠 Key Learnings

* REST API development in Spring Boot
* DTO vs Entity separation
* JPA relationships (OneToMany / ManyToOne)
* Global exception handling
* Layered architecture design
* Debugging real backend issues

## 🚀 Future Improvements

* Spring Security (JWT Authentication)
* Role-based access (Admin / Doctor / Patient)
* Swagger API documentation
* MySQL/PostgreSQL integration
* Pagination & filtering
* Email notifications

## 👨‍💻 Author

Muhammad Ibrahim Zahid
