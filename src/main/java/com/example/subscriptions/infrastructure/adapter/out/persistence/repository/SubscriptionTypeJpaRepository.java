package com.example.subscriptions.infrastructure.adapter.out.persistence.repository;

import com.example.subscriptions.infrastructure.adapter.out.persistence.entity.SubscriptionTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionTypeJpaRepository extends JpaRepository<SubscriptionTypeJpaEntity, Long> {

    Optional<SubscriptionTypeJpaEntity> findByName(String name);

    boolean existsByName(String name);
}
