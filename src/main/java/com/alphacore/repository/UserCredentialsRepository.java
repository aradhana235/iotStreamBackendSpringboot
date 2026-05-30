package com.alphacore.repository;

import com.alphacore.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, UUID> {
    UserCredentials findByUserId(UUID userId);
}