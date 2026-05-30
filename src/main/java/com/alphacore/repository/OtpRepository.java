package com.alphacore.repository;

import com.alphacore.model.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OtpRepository extends JpaRepository<OtpVerification, UUID> {

    OtpVerification findTopByUserIdOrderByCreatedAtDesc(UUID userId);

}