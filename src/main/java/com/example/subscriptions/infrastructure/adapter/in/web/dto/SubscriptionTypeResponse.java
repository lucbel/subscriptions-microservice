package com.example.subscriptions.infrastructure.adapter.in.web.dto;

import com.example.subscriptions.domain.model.SubscriptionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SubscriptionTypeResponse(
        Long id,
        String name,
        BigDecimal price,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static SubscriptionTypeResponse fromDomain(SubscriptionType subscriptionType) {
        return new SubscriptionTypeResponse(
                subscriptionType.getId(),
                subscriptionType.getName(),
                subscriptionType.getPrice(),
                subscriptionType.getDescription(),
                subscriptionType.getCreatedAt(),
                subscriptionType.getUpdatedAt()
        );
    }
}
