package com.alphacore.controller;

import com.alphacore.model.LoginRequest;
import com.alphacore.model.User;
import com.alphacore.repository.UserRepository;
import com.alphacore.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        user.setRole("USER");
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = JwtUtil.generateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(Map.of("token", token, "role", user.getRole()));
    }
}