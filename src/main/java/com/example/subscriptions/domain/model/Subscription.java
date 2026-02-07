package com.example.subscriptions.domain.model;

import java.time.LocalDateTime;

public class Subscription {

    private Long id;
    private User user;
    private SubscriptionType subscriptionType;
    private SubscriptionStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Subscription() {
    }

    public Subscription(Long id, User user, SubscriptionType subscriptionType,
                        SubscriptionStatus status, LocalDateTime startDate, LocalDateTime endDate,
                        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.subscriptionType = subscriptionType;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Subscription create(User user, SubscriptionType subscriptionType,
                                       LocalDateTime startDate, LocalDateTime endDate) {
        Subscription subscription = new Subscription();
        subscription.user = user;
        subscription.subscriptionType = subscriptionType;
        subscription.status = SubscriptionStatus.ACTIVE;
        subscription.startDate = startDate != null ? startDate : LocalDateTime.now();
        subscription.endDate = endDate;
        subscription.createdAt = LocalDateTime.now();
        return subscription;
    }

    public void cancel() {
        this.status = SubscriptionStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }

    public void pause() {
        this.status = SubscriptionStatus.PAUSED;
        this.updatedAt = LocalDateTime.now();
    }

    public void activate() {
        this.status = SubscriptionStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    public void update(SubscriptionType subscriptionType, SubscriptionStatus status,
                       LocalDateTime startDate, LocalDateTime endDate) {
        this.subscriptionType = subscriptionType;
        if (status != null) {
            this.status = status;
        }
        if (startDate != null) {
            this.startDate = startDate;
        }
        this.endDate = endDate;
        this.updatedAt = LocalDateTime.now();
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

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
