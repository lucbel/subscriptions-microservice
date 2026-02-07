package com.example.subscriptions.domain.port.in;

import com.example.subscriptions.domain.model.Subscription;
import com.example.subscriptions.domain.model.SubscriptionStatus;

import java.time.LocalDateTime;

public interface CreateSubscriptionUseCase {

    Subscription createSubscription(CreateSubscriptionCommand command);

    record CreateSubscriptionCommand(
            Long userId,
            Long subscriptionTypeId,
            SubscriptionStatus status,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {}
}
