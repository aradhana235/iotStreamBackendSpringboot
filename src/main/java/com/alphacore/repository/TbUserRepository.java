package com.alphacore.repository;

import com.alphacore.model.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TbUserRepository extends JpaRepository<TbUser, UUID> {
    Optional<TbUser> findByEmail(String email);
}