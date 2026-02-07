package com.example.subscriptions.domain.exception;

public class SubscriptionTypeNotFoundException extends RuntimeException {

    public SubscriptionTypeNotFoundException(Long id) {
        super("Subscription type not found with id: " + id);
    }

    public SubscriptionTypeNotFoundException(String name) {
        super("Subscription type not found with name: " + name);
    }
}
