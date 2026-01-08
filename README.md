# Task Management REST API

A simple, enterprise-style REST API for managing users and tasks, built with Spring Boot, JPA/Hibernate, PostgreSQL, Flyway, and JWT authentication.

This project demonstrates clean backend architecture, proper validation, error handling, pagination, filtering, and database migrations.

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Flyway (Database Migrations)
- Spring Security + JWT (Bonus)
- Maven
- Lombok

---

## 🏗️ Architecture Overview

The application follows a layered architecture:

Controller → Service Interface → Service Implementation → Repository → Database

- Controllers are thin and REST-focused
- Business logic resides in the service layer
- DTOs are used for request/response
- Entities are not exposed directly
- Database schema is managed via Flyway

---

## 📦 Features

### User Management
- Create user
- List users with pagination
- Get user by ID
- Email uniqueness enforced

### Task Management
- Create task
- List tasks with pagination and filters
- Get task by ID
- Update full task
- Update task status only
- Delete task

### Additional
- JWT-based authentication (bonus)
- Global exception handling
- Validation with meaningful error responses
- Database migrations via Flyway

---

## 🔐 Authentication (JWT)

### Login Endpoint
POST /api/auth/login

**Request Body**
```json
{
  "username": "admin",
  "password": "password"
}
```
**Response**

```json
{
  "success": true,
  "data": "<JWT_TOKEN>"
}
```
- Use the returned token in all protected APIs:

## 🧪 API Endpoints
### Users
POST /api/users

GET /api/users?page=0&size=5

GET /api/users/{id}

### Tasks
POST /api/tasks

GET /api/tasks?status=&priority=&assignedUserId=&page=&size=

GET /api/tasks/{id}

PUT /api/tasks/{id}

PATCH /api/tasks/{id}/status

DELETE /api/tasks/{id}

## 🛠️ Setup Instructions
### 1️⃣ Prerequisites
- Java 17 or higher

- PostgreSQL

- Maven

### 2️⃣ Database Setup
- Create PostgreSQL database: "CREATE DATABASE task_manager";
- Update database credentials in application.properties:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/task_manager
spring.datasource.username=postgres
spring.datasource.password=postgres
```
### 3️⃣ Run the Application
- mvn clean install
- mvn spring-boot:run
- Application runs on: http://localhost:8080

Flyway migrations run automatically on startup

---
## ❌ Error Handling
````
- 400 Bad Request → Validation errors

- 404 Not Found → Resource not found

- 409 Conflict → Duplicate email

- 401 Unauthorized → Missing/invalid JWT