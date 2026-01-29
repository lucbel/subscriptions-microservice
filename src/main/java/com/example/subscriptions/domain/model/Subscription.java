package com.example.subscriptions.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Subscription {

    private Long id;
    private String userEmail;
    private String planName;
    private BigDecimal price;
    private SubscriptionStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Subscription() {
    }

    public Subscription(Long id, String userEmail, String planName, BigDecimal price,
                        SubscriptionStatus status, LocalDateTime startDate, LocalDateTime endDate,
                        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userEmail = userEmail;
        this.planName = planName;
        this.price = price;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Subscription create(String userEmail, String planName, BigDecimal price,
                                       LocalDateTime startDate, LocalDateTime endDate) {
        Subscription subscription = new Subscription();
        subscription.userEmail = userEmail;
        subscription.planName = planName;
        subscription.price = price;
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

    public void update(String userEmail, String planName, BigDecimal price,
                       SubscriptionStatus status, LocalDateTime startDate, LocalDateTime endDate) {
        this.userEmail = userEmail;
        this.planName = planName;
        this.price = price;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
