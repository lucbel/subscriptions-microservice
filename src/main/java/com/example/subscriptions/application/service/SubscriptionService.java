package com.example.subscriptions.application.service;

import com.example.subscriptions.domain.exception.SubscriptionNotFoundException;
import com.example.subscriptions.domain.exception.SubscriptionTypeNotFoundException;
import com.example.subscriptions.domain.exception.UserNotFoundException;
import com.example.subscriptions.domain.model.Subscription;
import com.example.subscriptions.domain.model.SubscriptionStatus;
import com.example.subscriptions.domain.model.SubscriptionType;
import com.example.subscriptions.domain.model.User;
import com.example.subscriptions.domain.port.in.*;
import com.example.subscriptions.domain.port.out.SubscriptionRepositoryPort;
import com.example.subscriptions.domain.port.out.SubscriptionTypeRepositoryPort;
import com.example.subscriptions.domain.port.out.UserRepositoryPort;
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
    private final UserRepositoryPort userRepository;
    private final SubscriptionTypeRepositoryPort subscriptionTypeRepository;

    public SubscriptionService(SubscriptionRepositoryPort subscriptionRepository,
                               UserRepositoryPort userRepository,
                               SubscriptionTypeRepositoryPort subscriptionTypeRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    public Subscription createSubscription(CreateSubscriptionCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException(command.userId()));

        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(command.subscriptionTypeId())
                .orElseThrow(() -> new SubscriptionTypeNotFoundException(command.subscriptionTypeId()));

        Subscription subscription = Subscription.create(
                user,
                subscriptionType,
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
    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subscription> getSubscriptionsByStatus(SubscriptionStatus status) {
        return subscriptionRepository.findByStatus(status);
    }

    @Override
    public Subscription updateSubscription(Long id, UpdateSubscriptionCommand command) {
        Subscription subscription = getSubscriptionById(id);

        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(command.subscriptionTypeId())
                .orElseThrow(() -> new SubscriptionTypeNotFoundException(command.subscriptionTypeId()));

        subscription.update(
                subscriptionType,
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
