package com.example.subscriptions.infrastructure.adapter.out.persistence.mapper;

import com.example.subscriptions.domain.model.Subscription;
import com.example.subscriptions.domain.model.SubscriptionStatus;
import com.example.subscriptions.infrastructure.adapter.out.persistence.entity.SubscriptionJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionPersistenceMapper {

    public Subscription toDomain(SubscriptionJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Subscription(
                entity.getId(),
                entity.getUserEmail(),
                entity.getPlanName(),
                entity.getPrice(),
                SubscriptionStatus.valueOf(entity.getStatus()),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public SubscriptionJpaEntity toEntity(Subscription domain) {
        if (domain == null) {
            return null;
        }

        SubscriptionJpaEntity entity = new SubscriptionJpaEntity();
        entity.setId(domain.getId());
        entity.setUserEmail(domain.getUserEmail());
        entity.setPlanName(domain.getPlanName());
        entity.setPrice(domain.getPrice());
        entity.setStatus(domain.getStatus().name());
        entity.setStartDate(domain.getStartDate());
        entity.setEndDate(domain.getEndDate());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());

        return entity;
    }
}
