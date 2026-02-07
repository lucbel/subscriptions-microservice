package com.example.subscriptions.infrastructure.adapter.out.persistence.mapper;

import com.example.subscriptions.domain.model.SubscriptionType;
import com.example.subscriptions.infrastructure.adapter.out.persistence.entity.SubscriptionTypeJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubscriptionTypePersistenceMapper {

    SubscriptionType toDomain(SubscriptionTypeJpaEntity entity);

    SubscriptionTypeJpaEntity toEntity(SubscriptionType domain);

    void updateEntity(@MappingTarget SubscriptionTypeJpaEntity entity, SubscriptionType domain);
}
