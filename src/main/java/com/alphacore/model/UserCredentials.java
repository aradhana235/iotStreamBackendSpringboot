package com.alphacore.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_credentials")
public class UserCredentials {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private TbUser user;

    @Column(nullable = false)
    private String password;

    //private Integer failedAttempts = 0;

   // private LocalDateTime accountLockedUntil;

    //private LocalDateTime passwordUpdatedAt;

    //private Boolean active = true;

    // =========================
    // LIFECYCLE METHODS
    // =========================

//    @PrePersist
//    public void prePersist() {
//        if (failedAttempts == null) {
//            failedAttempts = 0;
//        }
//        active = true;
//        passwordUpdatedAt = LocalDateTime.now();
//    }

    // =========================
    // GETTERS & SETTERS
    // =========================

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public TbUser getUser() {
        return user;
    }

    public void setUser(TbUser user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Integer getFailedAttempts() {
//        return failedAttempts;
//    }
//
//    public void setFailedAttempts(Integer failedAttempts) {
//        this.failedAttempts = failedAttempts;
//    }

//    public LocalDateTime getAccountLockedUntil() {
//        return accountLockedUntil;
//    }
//
//    public void setAccountLockedUntil(LocalDateTime accountLockedUntil) {
//        this.accountLockedUntil = accountLockedUntil;
//    }

//    public LocalDateTime getPasswordUpdatedAt() {
//        return passwordUpdatedAt;
//    }
//
//    public void setPasswordUpdatedAt(LocalDateTime passwordUpdatedAt) {
//        this.passwordUpdatedAt = passwordUpdatedAt;
//    }

//    public Boolean getActive() {
//        return active;
//    }
//
//    public void setActive(Boolean active) {
//        this.active = active;
//    }
}