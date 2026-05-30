package com.alphacore.controller;

import com.alphacore.model.TbUser;
import com.alphacore.repository.TbUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final TbUserRepository userRepository;

    public UserController(TbUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // =========================
    // GET ALL USERS (ADMIN)
    // =========================
    @GetMapping("/all")
    public ResponseEntity<List<TbUser>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // =========================
    // GET USER BY EMAIL
    // =========================
    @GetMapping("/by-email")
    public ResponseEntity<TbUser> getByEmail(@RequestParam String email) {
        return ResponseEntity.ok(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("User not found"))
        );
    }
}