package com.renandev.agregadorinvestimentos.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity(name = "User")
@Table(name = "tb_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @CreationTimestamp
    private Instant craationTimestamp;
    @UpdateTimestamp
    private Instant updatedTimestamp;

    public User() {
    }

    public User(UUID userId, String username, String email, String password, Instant craationTimestamp, Instant updatedTimestamp) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.craationTimestamp = craationTimestamp;
        this.updatedTimestamp = updatedTimestamp;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCraationTimestamp() {
        return craationTimestamp;
    }

    public void setCraationTimestamp(Instant craationTimestamp) {
        this.craationTimestamp = craationTimestamp;
    }

    public Instant getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(Instant updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }
}
