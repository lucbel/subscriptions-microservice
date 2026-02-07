package com.example.subscriptions.infrastructure.adapter.out.persistence.mapper;

import com.example.subscriptions.domain.model.SessionToken;
import com.example.subscriptions.infrastructure.adapter.out.persistence.entity.SessionTokenJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserPersistenceMapper.class})
public interface SessionTokenPersistenceMapper {

    SessionToken toDomain(SessionTokenJpaEntity entity);

    SessionTokenJpaEntity toEntity(SessionToken domain);
}
