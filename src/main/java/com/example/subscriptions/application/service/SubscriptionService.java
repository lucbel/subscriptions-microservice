package com.example.subscriptions.application.service;

import com.example.subscriptions.domain.exception.SubscriptionNotFoundException;
import com.example.subscriptions.domain.model.Subscription;
import com.example.subscriptions.domain.model.SubscriptionStatus;
import com.example.subscriptions.domain.port.in.*;
import com.example.subscriptions.domain.port.out.SubscriptionRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubscriptionService implements
        CreateSubscriptionUseCase,
        GetSubscriptionUseCase,
        UpdateSubscriptionUseCase,
        DeleteSubscriptionUseCase,
        ManageSubscriptionStatusUseCase {

    private final SubscriptionRepositoryPort subscriptionRepository;

    public SubscriptionService(SubscriptionRepositoryPort subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Subscription createSubscription(CreateSubscriptionCommand command) {
        Subscription subscription = Subscription.create(
                command.userEmail(),
                command.planName(),
                command.price(),
                command.startDate(),
                command.endDate()
        );

        if (command.status() != null) {
            subscription.setStatus(command.status());
        }

        return subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subscription> getSubscriptionsByUserEmail(String userEmail) {
        return subscriptionRepository.findByUserEmail(userEmail);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subscription> getSubscriptionsByStatus(SubscriptionStatus status) {
        return subscriptionRepository.findByStatus(status);
    }

    @Override
    public Subscription updateSubscription(Long id, UpdateSubscriptionCommand command) {
        Subscription subscription = getSubscriptionById(id);

        subscription.update(
                command.userEmail(),
                command.planName(),
                command.price(),
                command.status(),
                command.startDate(),
                command.endDate()
        );

        return subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteSubscription(Long id) {
        getSubscriptionById(id);
        subscriptionRepository.deleteById(id);
    }

    @Override
    public Subscription cancelSubscription(Long id) {
        Subscription subscription = getSubscriptionById(id);
        subscription.cancel();
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription pauseSubscription(Long id) {
        Subscription subscription = getSubscriptionById(id);
        subscription.pause();
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription activateSubscription(Long id) {
        Subscription subscription = getSubscriptionById(id);
        subscription.activate();
        return subscriptionRepository.save(subscription);
    }
}
