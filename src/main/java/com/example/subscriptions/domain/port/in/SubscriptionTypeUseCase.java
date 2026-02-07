package com.example.subscriptions.domain.port.in;

import com.example.subscriptions.domain.model.SubscriptionType;

import java.math.BigDecimal;
import java.util.List;

public interface SubscriptionTypeUseCase {

    List<SubscriptionType> getAllSubscriptionTypes();

    SubscriptionType getSubscriptionTypeById(Long id);

    SubscriptionType getSubscriptionTypeByName(String name);

    SubscriptionType createSubscriptionType(CreateSubscriptionTypeCommand command);

    SubscriptionType updateSubscriptionType(Long id, UpdateSubscriptionTypeCommand command);

    void deleteSubscriptionType(Long id);

    record CreateSubscriptionTypeCommand(
            String name,
            BigDecimal price,
            String description
    ) {}

    record UpdateSubscriptionTypeCommand(
            String name,
            BigDecimal price,
            String description
    ) {}
}
