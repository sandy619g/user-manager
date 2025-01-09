package com.user.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email id")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false)
    private String password;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}

