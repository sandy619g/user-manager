# User Management System

This project is a simple User Management API built with Spring Boot, Spring Security, JWT authentication, and Spring Data JPA. 
It allows users to be created, retrieve, update, and delete from the database. 

## Features

- **User Management**: Create, read, update, and delete users.
- **JWT Authentication**: Secure endpoints using JWT tokens.
- **Password Encryption**: Uses BCrypt for encrypting user passwords.
- **Role-based Authorization**: Certain endpoints can be secured based on roles (not fully implemented here but can be extended).

## Technologies Used

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- JPA (Java Persistence API)
- H2 Database (In-memory database, can be switched to MySQL/PostgreSQL)
- Lombok

## Project Structure

1. **`com.user.controller`**:
    - `UserController`: Contains REST endpoints for user CRUD operations.
    - `AuthController`: Handles user authentication by generating a JWT token upon login.

2. **`com.user.model`**:
    - `User`: Represents a user entity.

3. **`com.user.exception`**:
    - `UserNotFoundException`: Custom exception for handling user not found errors.

4. **`com.user.service`**:
    - `UserService`: Business logic for handling user operations such as creating, updating, deleting, and retrieving users.

5. **`com.user.config`**:
    - `SecurityConfig`: Configures Spring Security, including JWT authentication filter and BCrypt password encoder.

6. **`com.user.filter`**:
    - `JwtAuthenticationFilter`: Custom filter to intercept incoming requests and validate the JWT token.

7. **`com.user.util`**:
    - `JwtUtil`: Utility class for generating and parsing JWT tokens.

## Setup

### Prerequisites

- JDK 17 or higher
- Maven or Gradle
- IDE (IntelliJ IDEA)

### Clone the Repository

```bash

https://github.com/sandy619g/user-manager.git
cd user-manager
```
### Run Using Maven
```bash

mvn clean install
mvn spring-boot:run
```
### Run Test
```bash

mvn test
```

## API Endpoints

### Authentication
```bash

curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=A4EF1D8D18269AB93DFDACA6DD653F60' \
--data '{
  "username": "admin",
  "password": "admin123"
}'
```

### POST USER
```bash

curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczNjQxNjcwMSwiZXhwIjoxNzM2NDIwMzAxfQ.6-OarIyBVwlvYZrQb3OUlD-QcgF2SIitpUuU-Bow_cM' \
--header 'Cookie: JSESSIONID=A4EF1D8D18269AB93DFDACA6DD653F60' \
--data-raw '{
  "username": "john doe",
  "email": "john@test.com",
  "password": "password"
}'
```