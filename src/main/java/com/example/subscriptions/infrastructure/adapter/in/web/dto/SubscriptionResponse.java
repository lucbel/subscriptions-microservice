package com.example.subscriptions.infrastructure.adapter.in.web.dto;

import com.example.subscriptions.domain.model.Subscription;
import com.example.subscriptions.domain.model.SubscriptionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SubscriptionResponse(
        Long id,
        Long userId,
        Long subscriptionTypeId,
        String subscriptionTypeName,
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
                subscription.getUser().getId(),
                subscription.getSubscriptionType().getId(),
                subscription.getSubscriptionType().getName(),
                subscription.getSubscriptionType().getPrice(),
                subscription.getStatus(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getCreatedAt(),
                subscription.getUpdatedAt()
        );
    }
}
