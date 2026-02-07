package com.example.subscriptions.domain.port.in;

import com.example.subscriptions.domain.model.Subscription;
import com.example.subscriptions.domain.model.SubscriptionStatus;
import com.example.subscriptions.domain.model.SubscriptionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface UpdateSubscriptionUseCase {

    Subscription updateSubscription(Long id, UpdateSubscriptionCommand command);

    record UpdateSubscriptionCommand(
            SubscriptionType subscriptionType,
            BigDecimal price,
            SubscriptionStatus status,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {}
}
