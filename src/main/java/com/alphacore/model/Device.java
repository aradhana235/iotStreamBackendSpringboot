package com.alphacore.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // ======================
    // BASIC FIELDS
    // ======================

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "label")
    private String label;

    // ======================
    // THINGSBOARD REQUIRED
    // ======================

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "device_profile_id", nullable = false)
    private UUID deviceProfileId;

    // ======================
    // CREATED TIME
    // ======================

    @Column(name = "created_time", nullable = false, updatable = false)
    private Long createdTime;

    public Device() {
    }

    // ======================
    // AUTO TIME
    // ======================

    @PrePersist
    public void onCreate() {

        if (this.createdTime == null) {

            this.createdTime = System.currentTimeMillis();

        }
    }

    // ======================
    // GETTERS & SETTERS
    // ======================

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    // ======================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ======================

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // ======================

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    // ======================
    // TENANT
    // ======================

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    // ======================
    // CUSTOMER
    // ======================

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    // ======================
    // DEVICE PROFILE
    // ======================

    public UUID getDeviceProfileId() {
        return deviceProfileId;
    }

    public void setDeviceProfileId(UUID deviceProfileId) {
        this.deviceProfileId = deviceProfileId;
    }

    // ======================
    // CREATED TIME RAW
    // ======================

    public Long getCreatedTimeRaw() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    // ======================
    // CREATED TIME FORMAT
    // ======================

    @Transient
    public LocalDateTime getCreatedTime() {

        if (createdTime == null) {
            return null;
        }

        return Instant.ofEpochMilli(createdTime)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}