package com.example.subscriptions.domain.port.in;

import com.example.subscriptions.domain.model.Subscription;
import com.example.subscriptions.domain.model.SubscriptionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface UpdateSubscriptionUseCase {

    Subscription updateSubscription(Long id, UpdateSubscriptionCommand command);

    record UpdateSubscriptionCommand(
            String userEmail,
            String planName,
            BigDecimal price,
            SubscriptionStatus status,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {}
}
