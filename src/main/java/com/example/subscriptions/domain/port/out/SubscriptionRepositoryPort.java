package com.example.subscriptions.domain.port.out;

import com.example.subscriptions.domain.model.Subscription;
import com.example.subscriptions.domain.model.SubscriptionStatus;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepositoryPort {

    Subscription save(Subscription subscription);

    Optional<Subscription> findById(Long id);

    List<Subscription> findAll();

    List<Subscription> findByUserEmail(String userEmail);

    List<Subscription> findByStatus(SubscriptionStatus status);

    void deleteById(Long id);
}
