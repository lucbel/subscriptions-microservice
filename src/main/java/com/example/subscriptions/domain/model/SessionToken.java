package com.example.subscriptions.domain.model;

import java.time.LocalDateTime;

public class SessionToken {

    private Long id;
    private User user;
    private String token;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;

    public SessionToken() {
    }

    public SessionToken(Long id, User user, String token, LocalDateTime expiresAt, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
    }

    public static SessionToken create(User user, String token, LocalDateTime expiresAt) {
        SessionToken sessionToken = new SessionToken();
        sessionToken.user = user;
        sessionToken.token = token;
        sessionToken.expiresAt = expiresAt;
        sessionToken.createdAt = LocalDateTime.now();
        return sessionToken;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
