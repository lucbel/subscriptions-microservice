package com.example.subscriptions.application.service;

import com.example.subscriptions.domain.exception.SubscriptionTypeNotFoundException;
import com.example.subscriptions.domain.model.SubscriptionType;
import com.example.subscriptions.domain.port.in.SubscriptionTypeUseCase;
import com.example.subscriptions.domain.port.out.SubscriptionTypeRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubscriptionTypeService implements SubscriptionTypeUseCase {

    private final SubscriptionTypeRepositoryPort subscriptionTypeRepository;

    public SubscriptionTypeService(SubscriptionTypeRepositoryPort subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionType> getAllSubscriptionTypes() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionType getSubscriptionTypeById(Long id) {
        return subscriptionTypeRepository.findById(id)
                .orElseThrow(() -> new SubscriptionTypeNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionType getSubscriptionTypeByName(String name) {
        return subscriptionTypeRepository.findByName(name)
                .orElseThrow(() -> new SubscriptionTypeNotFoundException(name));
    }

    @Override
    public SubscriptionType createSubscriptionType(CreateSubscriptionTypeCommand command) {
        SubscriptionType subscriptionType = SubscriptionType.create(
                command.name(),
                command.price(),
                command.description()
        );
        return subscriptionTypeRepository.save(subscriptionType);
    }

    @Override
    public SubscriptionType updateSubscriptionType(Long id, UpdateSubscriptionTypeCommand command) {
        SubscriptionType subscriptionType = getSubscriptionTypeById(id);
        subscriptionType.update(
                command.name(),
                command.price(),
                command.description()
        );
        return subscriptionTypeRepository.save(subscriptionType);
    }

    @Override
    public void deleteSubscriptionType(Long id) {
        getSubscriptionTypeById(id);
        subscriptionTypeRepository.deleteById(id);
    }
}
