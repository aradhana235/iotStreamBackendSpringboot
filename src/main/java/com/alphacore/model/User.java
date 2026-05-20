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

    // =========================
    // ID
    // =========================

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id",
            nullable = false,
            updatable = false)
    private UUID id;

    // =========================
    // CREATED TIME
    // =========================

    @Column(name = "created_time")
    private Long createdTime;

    // =========================
    // ADDITIONAL INFO
    // =========================

    @Column(name = "additional_info")
    private String additionalInfo;

    // =========================
    // AUTHORITY
    // =========================

    @Column(name = "authority")
    private String authority;

    // =========================
    // CUSTOMER ID
    // =========================

    @Column(name = "customer_id")
    private UUID customerId;

    // =========================
    // EMAIL
    // =========================

    @Column(name = "email")
    private String email;

    // =========================
    // FIRST NAME
    // =========================

    @Column(name = "first_name")
    private String firstName;

    // =========================
    // LAST NAME
    // =========================

    @Column(name = "last_name")
    private String lastName;

    // =========================
    // PHONE
    // =========================

    @Column(name = "phone")
    private String phone;

    // =========================
    // TENANT ID
    // =========================

    @Column(name = "tenant_id")
    private UUID tenantId;

    // =========================
    // VERSION
    // =========================

    @Column(name = "version")
    private Long version;

    // =========================
    // CUSTOMER MANU ID
    // =========================

    @Column(name = "custom_menu_id")
    private UUID customMenuId;

    // =========================
    // AUTO CREATED TIME
    // =========================

    @PrePersist
    public void onCreate() {

        if (this.createdTime == null) {

            this.createdTime =
                    System.currentTimeMillis();
        }

        if (this.version == null) {

            this.version = 1L;
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

    public UUID getcustomMenuId() {
        return customMenuId;
    }

    public void setcustomMenuId(UUID customMenuId) {
        this.customMenuId = customMenuId;
    }

    // =========================
    // FRONTEND DATE FORMAT
    // =========================

    @Transient
    public LocalDateTime getCreatedTime() {

        if (createdTime == null)
            return null;

        return Instant.ofEpochMilli(createdTime)
                .atZone(
                        ZoneId.systemDefault()
                )
                .toLocalDateTime();
    }
}