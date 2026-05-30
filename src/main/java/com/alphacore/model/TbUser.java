package com.alphacore.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import com.alphacore.model.Role;

@Entity
@Table(name = "tb_user")
public class TbUser {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private Role authority;

//    @Column(nullable = false)
//    private Boolean active = true;
//
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;

    // =========================
    // LIFECYCLE METHODS
    // =========================

//    @PrePersist
//    public void onCreate() {
//        if (id == null) {
//            id = UUID.randomUUID();
//        }
//        createdAt = LocalDateTime.now();
//        updatedAt = LocalDateTime.now();
//        active = true;
//    }

//    @PreUpdate
//    public void onUpdate() {
//        updatedAt = LocalDateTime.now();
//    }

    // =========================
    // GETTERS & SETTERS
    // =========================

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Role getAuthority() {
        return authority;
    }

    public void setAuthority(Role authority) {
        this.authority = authority;
    }

//    public Boolean getActive() {
//        return active;
//    }
//
//    public void setActive(Boolean active) {
//        this.active = active;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
}