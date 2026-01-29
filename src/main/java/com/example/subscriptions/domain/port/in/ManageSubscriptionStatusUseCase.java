package com.example.subscriptions.domain.port.in;

import com.example.subscriptions.domain.model.Subscription;

public interface ManageSubscriptionStatusUseCase {

    Subscription cancelSubscription(Long id);

    Subscription pauseSubscription(Long id);

    Subscription activateSubscription(Long id);
}
