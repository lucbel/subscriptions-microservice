package com.example.subscriptions.infrastructure.adapter.out.persistence;

import com.example.subscriptions.domain.model.SubscriptionType;
import com.example.subscriptions.domain.port.out.SubscriptionTypeRepositoryPort;
import com.example.subscriptions.infrastructure.adapter.out.persistence.mapper.SubscriptionTypePersistenceMapper;
import com.example.subscriptions.infrastructure.adapter.out.persistence.repository.SubscriptionTypeJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SubscriptionTypePersistenceAdapter implements SubscriptionTypeRepositoryPort {

    private final SubscriptionTypeJpaRepository jpaRepository;
    private final SubscriptionTypePersistenceMapper mapper;

    public SubscriptionTypePersistenceAdapter(SubscriptionTypeJpaRepository jpaRepository,
                                               SubscriptionTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SubscriptionType save(SubscriptionType subscriptionType) {
        var entity = mapper.toEntity(subscriptionType);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<SubscriptionType> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<SubscriptionType> findByName(String name) {
        return jpaRepository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public List<SubscriptionType> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }
}
