package com.example.subscriptions.domain.port.in;

import com.example.subscriptions.domain.model.Subscription;
import com.example.subscriptions.domain.model.SubscriptionStatus;

import java.util.List;

public interface GetSubscriptionUseCase {

    List<Subscription> getAllSubscriptions();

    Subscription getSubscriptionById(Long id);

    List<Subscription> getSubscriptionsByUserEmail(String userEmail);

    List<Subscription> getSubscriptionsByStatus(SubscriptionStatus status);
}
