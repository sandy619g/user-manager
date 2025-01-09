package com.user.controller;

import com.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(String name) {
        return "Hello";
    }

    @PostMapping("/hello")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        return ResponseEntity.ok(null);
    }
}
