package com.alphacore.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "alarm")
public class Alarm {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "originator_id")
    private String originator;

    @Column(name = "type")
    private String type;

    @Column(name = "severity")
    private String severity;

    @Column(name = "created_time")
    private String createdTime;

    // =========================
    // GETTERS SETTERS
    // =========================

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}