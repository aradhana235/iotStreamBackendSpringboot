package com.alphacore.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "created_time")
    private Long createdTime;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "authority")
    private String authority;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "tenant_id")
    private UUID tenantId;

    @Column(name = "version")
    private Long version;

    @Column(name = "custom_menu_id")
    private UUID customMenuId;

    // =========================
    // AUTO CREATE
    // =========================
    @PrePersist
    public void onCreate() {

        if (this.createdTime == null) {
            this.createdTime = System.currentTimeMillis();
        }

        if (this.version == null) {
            this.version = 1L;
        }

        if (this.authority == null) {
            this.authority = "USER";
        }
    }

    // =========================
    // GETTERS & SETTERS
    // =========================

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getCreatedTimeRaw() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public UUID getCustomMenuId() {
        return customMenuId;
    }

    public void setCustomMenuId(UUID customMenuId) {
        this.customMenuId = customMenuId;
    }

    // frontend date
    @Transient
    public LocalDateTime getCreatedTime() {
        if (createdTime == null) return null;

        return Instant.ofEpochMilli(createdTime)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}