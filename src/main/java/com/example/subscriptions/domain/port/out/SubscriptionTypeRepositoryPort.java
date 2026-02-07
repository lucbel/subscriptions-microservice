package com.example.subscriptions.domain.port.out;

import com.example.subscriptions.domain.model.SubscriptionType;

import java.util.List;
import java.util.Optional;

public interface SubscriptionTypeRepositoryPort {

    SubscriptionType save(SubscriptionType subscriptionType);

    Optional<SubscriptionType> findById(Long id);

    Optional<SubscriptionType> findByName(String name);

    List<SubscriptionType> findAll();

    void deleteById(Long id);

    boolean existsByName(String name);
}
