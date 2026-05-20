package com.alphacore.controller;

import com.alphacore.model.User;
import com.alphacore.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserManagementController {

    @Autowired
    private UserRepository userRepository;

    // =========================
    // GET ALL USERS
    // =========================

    @GetMapping
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    // =========================
    // GET USER BY ID
    // =========================

    @GetMapping("/{id}")
    public User getUserById(
            @PathVariable UUID id) {

        return userRepository.findById(id)
                .orElse(null);
    }

    // =========================
    // CREATE USER
    // =========================

    @PostMapping
    public User createUser(
            @RequestBody User user) {

        return userRepository.save(user);
    }

    // =========================
    // UPDATE USER
    // =========================

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable UUID id,
            @RequestBody User updatedUser) {

        return userRepository.findById(id)
                .map(user -> {

                    user.setFirstName(
                            updatedUser.getFirstName());

                    user.setLastName(
                            updatedUser.getLastName());

                    user.setEmail(
                            updatedUser.getEmail());

                    user.setPhone(
                            updatedUser.getPhone());

                    user.setAuthority(
                            updatedUser.getAuthority());

                    user.setAdditionalInfo(
                            updatedUser.getAdditionalInfo());

                    return userRepository.save(user);

                }).orElse(null);
    }

    // =========================
    // DELETE USER
    // =========================

    @DeleteMapping("/{id}")
    public String deleteUser(
            @PathVariable UUID id) {

        userRepository.deleteById(id);

        return "User deleted successfully";
    }
}