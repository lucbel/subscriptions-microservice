package com.example.subscriptions.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SubscriptionType {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SubscriptionType() {
    }

    public SubscriptionType(Long id, String name, BigDecimal price, String description,
                            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static SubscriptionType create(String name, BigDecimal price, String description) {
        SubscriptionType subscriptionType = new SubscriptionType();
        subscriptionType.name = name;
        subscriptionType.price = price;
        subscriptionType.description = description;
        subscriptionType.createdAt = LocalDateTime.now();
        return subscriptionType;
    }

    public void update(String name, BigDecimal price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
