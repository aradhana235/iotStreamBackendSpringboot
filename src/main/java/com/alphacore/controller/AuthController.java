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

    public AuthController(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {

        // FIND USER BY EMAIL
        User user =
                userRepository.findByEmail(
                        request.getEmail()
                );

        // USER NOT FOUND
        if (user == null) {

            return ResponseEntity
                    .status(401)
                    .body("Invalid Email");
        }

        // PASSWORD CHECK
        if (!user.getAdditionalInfo()
                .equals(request.getPassword())) {

            return ResponseEntity
                    .status(401)
                    .body("Invalid Password");
        }

        // JWT TOKEN
        String token =
                JwtUtil.generateToken(
                        user.getEmail(),
                        user.getAuthority()
                );

        return ResponseEntity.ok(

                Map.of(
                        "token", token,
                        "email", user.getEmail(),
                        "authority",
                        user.getAuthority(),
                        "firstName",
                        user.getFirstName(),
                        "lastName",
                        user.getLastName()
                )
        );
    }
}