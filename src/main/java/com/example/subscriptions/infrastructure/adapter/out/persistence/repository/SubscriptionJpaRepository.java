package com.example.subscriptions.infrastructure.adapter.out.persistence.repository;

import com.example.subscriptions.infrastructure.adapter.out.persistence.entity.SubscriptionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionJpaRepository extends JpaRepository<SubscriptionJpaEntity, Long> {

    List<SubscriptionJpaEntity> findByUserId(Long userId);

    List<SubscriptionJpaEntity> findByStatus(String status);

    List<SubscriptionJpaEntity> findBySubscriptionTypeId(Long subscriptionTypeId);
}
