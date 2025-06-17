package com.example.studentmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    // Temporary storage for registered users
    private final Map<String, String> registeredUsers = new ConcurrentHashMap<>();

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
        if (registeredUsers.containsKey(username)) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        registeredUsers.put(username, password);
        return ResponseEntity.ok("Registered user: " + username);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        String storedPassword = registeredUsers.get(username);
        if (storedPassword != null && storedPassword.equals(password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}