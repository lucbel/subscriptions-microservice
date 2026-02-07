package com.example.subscriptions.infrastructure.adapter.in.web.dto;

import com.example.subscriptions.domain.model.SubscriptionStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateSubscriptionRequest(
        @NotNull(message = "Subscription type ID is required")
        Long subscriptionTypeId,

        SubscriptionStatus status,

        LocalDateTime startDate,

        LocalDateTime endDate
) {}
