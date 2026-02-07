package com.example.subscriptions.infrastructure.adapter.in.web.dto;

import com.example.subscriptions.domain.model.SubscriptionStatus;
import com.example.subscriptions.domain.model.SubscriptionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SubscriptionRequest(
        @NotNull(message = "User ID is required")
        Long userId,

        @NotNull(message = "Subscription type is required")
        SubscriptionType subscriptionType,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        BigDecimal price,

        SubscriptionStatus status,

        LocalDateTime startDate,

        LocalDateTime endDate
) {}
