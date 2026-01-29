package com.example.subscriptions.infrastructure.adapter.in.web.dto;

import com.example.subscriptions.domain.model.SubscriptionStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SubscriptionRequest(
        @NotBlank(message = "User email is required")
        @Email(message = "Invalid email format")
        String userEmail,

        @NotBlank(message = "Plan name is required")
        String planName,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        BigDecimal price,

        SubscriptionStatus status,

        LocalDateTime startDate,

        LocalDateTime endDate
) {}
