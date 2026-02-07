package com.example.subscriptions.infrastructure.adapter.out.persistence.mapper;

import com.example.subscriptions.domain.model.Subscription;
import com.example.subscriptions.domain.model.SubscriptionStatus;
import com.example.subscriptions.domain.model.SubscriptionType;
import com.example.subscriptions.infrastructure.adapter.out.persistence.entity.SubscriptionJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {UserPersistenceMapper.class})
public interface SubscriptionPersistenceMapper {

    @Mapping(target = "subscriptionType", source = "subscriptionType", qualifiedByName = "stringToSubscriptionType")
    @Mapping(target = "status", source = "status", qualifiedByName = "stringToSubscriptionStatus")
    Subscription toDomain(SubscriptionJpaEntity entity);

    @Mapping(target = "subscriptionType", source = "subscriptionType", qualifiedByName = "subscriptionTypeToString")
    @Mapping(target = "status", source = "status", qualifiedByName = "subscriptionStatusToString")
    SubscriptionJpaEntity toEntity(Subscription domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "subscriptionType", source = "subscriptionType", qualifiedByName = "subscriptionTypeToString")
    @Mapping(target = "status", source = "status", qualifiedByName = "subscriptionStatusToString")
    void updateEntity(@MappingTarget SubscriptionJpaEntity entity, Subscription domain);

    @Named("stringToSubscriptionType")
    default SubscriptionType stringToSubscriptionType(String value) {
        return value != null ? SubscriptionType.valueOf(value) : null;
    }

    @Named("subscriptionTypeToString")
    default String subscriptionTypeToString(SubscriptionType value) {
        return value != null ? value.name() : null;
    }

    @Named("stringToSubscriptionStatus")
    default SubscriptionStatus stringToSubscriptionStatus(String value) {
        return value != null ? SubscriptionStatus.valueOf(value) : null;
    }

    @Named("subscriptionStatusToString")
    default String subscriptionStatusToString(SubscriptionStatus value) {
        return value != null ? value.name() : null;
    }
}
