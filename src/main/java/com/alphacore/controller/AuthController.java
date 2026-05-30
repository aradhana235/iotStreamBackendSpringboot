package com.alphacore.controller;

import com.alphacore.model.LoginRequest;

import com.alphacore.model.OtpRequest;
import com.alphacore.model.ForgotPasswordRequest;
import com.alphacore.model.ResetPasswordRequest;
import com.alphacore.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(
            authenticationService.login(request.getEmail(), request.getPassword())
        );
    }
    // =========================
    // VERIFY OTP
    // =========================
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest request) {

        String result = authenticationService.verifyOtp(
                request.getEmail(),
                request.getOtp()
        );

        return ResponseEntity.ok(result);
    }
    
    // =========================
    // FORGOT PASSWORD
    // =========================
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
            @RequestBody ForgotPasswordRequest request) {

        authenticationService.forgotPassword(
                request.getEmail()
        );

        return ResponseEntity.ok("Password reset link sent successfully");
    }

    // =========================
    // RESET PASSWORD
    // =========================
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetPasswordRequest request) {

        authenticationService.resetPassword(
                request.getToken(),
                request.getNewPassword()
        );

        return ResponseEntity.ok("Password updated successfully");
    }

}