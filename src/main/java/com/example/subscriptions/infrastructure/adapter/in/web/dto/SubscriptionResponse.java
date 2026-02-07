package com.example.subscriptions.infrastructure.adapter.in.web.dto;

import com.example.subscriptions.domain.model.Subscription;
import com.example.subscriptions.domain.model.SubscriptionStatus;
import com.example.subscriptions.domain.model.SubscriptionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SubscriptionResponse(
        Long id,
        UserResponse user,
        SubscriptionType subscriptionType,
        BigDecimal price,
        SubscriptionStatus status,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static SubscriptionResponse fromDomain(Subscription subscription) {
        return new SubscriptionResponse(
                subscription.getId(),
                UserResponse.fromDomain(subscription.getUser()),
                subscription.getSubscriptionType(),
                subscription.getPrice(),
                subscription.getStatus(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getCreatedAt(),
                subscription.getUpdatedAt()
        );
    }
}
