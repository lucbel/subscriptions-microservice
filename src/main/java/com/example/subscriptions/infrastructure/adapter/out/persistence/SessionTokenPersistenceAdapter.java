package com.example.subscriptions.infrastructure.adapter.out.persistence;

import com.example.subscriptions.domain.model.SessionToken;
import com.example.subscriptions.domain.port.out.SessionTokenRepositoryPort;
import com.example.subscriptions.infrastructure.adapter.out.persistence.mapper.SessionTokenPersistenceMapper;
import com.example.subscriptions.infrastructure.adapter.out.persistence.repository.SessionTokenJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class SessionTokenPersistenceAdapter implements SessionTokenRepositoryPort {

    private final SessionTokenJpaRepository jpaRepository;
    private final SessionTokenPersistenceMapper mapper;

    public SessionTokenPersistenceAdapter(SessionTokenJpaRepository jpaRepository,
                                          SessionTokenPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SessionToken save(SessionToken sessionToken) {
        var entity = mapper.toEntity(sessionToken);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<SessionToken> findByToken(String token) {
        return jpaRepository.findByToken(token)
                .map(mapper::toDomain);
    }

    @Override
    public void deleteByToken(String token) {
        jpaRepository.deleteByToken(token);
    }

    @Override
    public void deleteByUserId(Long userId) {
        jpaRepository.deleteByUserId(userId);
    }

    @Override
    public void deleteExpiredTokens() {
        jpaRepository.deleteExpiredTokens(LocalDateTime.now());
    }
}
