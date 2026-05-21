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

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {

        // SAFE DEFAULTS
        if (user.getAuthority() == null) {
            user.setAuthority("USER");
        }

        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable UUID id,
            @RequestBody User updatedUser) {

        return userRepository.findById(id)
                .map(user -> {

                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhone(updatedUser.getPhone());
                    user.setAuthority(updatedUser.getAuthority());

                    return userRepository.save(user);

                }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable UUID id) {

        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}