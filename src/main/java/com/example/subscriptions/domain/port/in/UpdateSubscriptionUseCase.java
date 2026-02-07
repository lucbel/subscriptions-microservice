package com.example.subscriptions.domain.port.in;

import com.example.subscriptions.domain.model.Subscription;
import com.example.subscriptions.domain.model.SubscriptionStatus;

import java.time.LocalDateTime;

public interface UpdateSubscriptionUseCase {

    Subscription updateSubscription(Long id, UpdateSubscriptionCommand command);

    record UpdateSubscriptionCommand(
            Long subscriptionTypeId,
            SubscriptionStatus status,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {}
}
