package com.example.subscriptions.infrastructure.adapter.out.persistence;

import com.example.subscriptions.domain.model.Subscription;
import com.example.subscriptions.domain.model.SubscriptionStatus;
import com.example.subscriptions.domain.port.out.SubscriptionRepositoryPort;
import com.example.subscriptions.infrastructure.adapter.out.persistence.mapper.SubscriptionPersistenceMapper;
import com.example.subscriptions.infrastructure.adapter.out.persistence.repository.SubscriptionJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SubscriptionPersistenceAdapter implements SubscriptionRepositoryPort {

    private final SubscriptionJpaRepository jpaRepository;
    private final SubscriptionPersistenceMapper mapper;

    public SubscriptionPersistenceAdapter(SubscriptionJpaRepository jpaRepository,
                                          SubscriptionPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Subscription save(Subscription subscription) {
        var entity = mapper.toEntity(subscription);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Subscription> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Subscription> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Subscription> findByUserEmail(String userEmail) {
        return jpaRepository.findByUserEmail(userEmail).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Subscription> findByStatus(SubscriptionStatus status) {
        return jpaRepository.findByStatus(status.name()).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
