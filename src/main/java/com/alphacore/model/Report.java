package com.alphacore.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Table(name = "report_template")
public class Report {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "format")
    private String format;

    @Column(name = "description")
    private String description;

    // BIGINT epoch millis
    @Column(name = "created_time", nullable = false, updatable = false)
    private Long createdTime;

    public Report() {}

    // AUTO SET BEFORE INSERT
    @PrePersist
    public void onCreate() {
        if (this.createdTime == null) {
            this.createdTime = System.currentTimeMillis();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // raw value
    public Long getCreatedTimeRaw() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    // frontend readable conversion
    @Transient
    public LocalDateTime getCreatedTime() {
        if (createdTime == null) return null;

        return Instant.ofEpochMilli(createdTime)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}