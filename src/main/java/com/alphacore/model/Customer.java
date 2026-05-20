package com.alphacore.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "zip")
    private String zip;

    // 🔥 BIGINT epoch millis
    @Column(name = "created_time", nullable = false, updatable = false)
    private Long createdTime;

    public Customer() {}

    // 🔥 AUTO SET BEFORE INSERT (IMPORTANT FIX)
    @PrePersist
    public void onCreate() {
        if (this.createdTime == null) {
            this.createdTime = System.currentTimeMillis();
        }
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    // raw value
    public Long getCreatedTimeRaw() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    // 🔥 frontend readable conversion
    @Transient
    public LocalDateTime getCreatedTime() {
        if (createdTime == null) return null;

        return Instant.ofEpochMilli(createdTime)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}