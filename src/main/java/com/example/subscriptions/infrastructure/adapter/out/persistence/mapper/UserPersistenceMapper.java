package com.example.subscriptions.infrastructure.adapter.out.persistence.mapper;

import com.example.subscriptions.domain.model.User;
import com.example.subscriptions.infrastructure.adapter.out.persistence.entity.UserJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    User toDomain(UserJpaEntity entity);

    UserJpaEntity toEntity(User domain);

    void updateEntity(@MappingTarget UserJpaEntity entity, User domain);
}
