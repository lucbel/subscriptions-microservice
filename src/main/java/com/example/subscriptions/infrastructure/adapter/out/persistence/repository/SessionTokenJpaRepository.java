package com.example.subscriptions.infrastructure.adapter.out.persistence.repository;

import com.example.subscriptions.infrastructure.adapter.out.persistence.entity.SessionTokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SessionTokenJpaRepository extends JpaRepository<SessionTokenJpaEntity, Long> {

    Optional<SessionTokenJpaEntity> findByToken(String token);

    void deleteByToken(String token);

    void deleteByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM SessionTokenJpaEntity s WHERE s.expiresAt < :now")
    void deleteExpiredTokens(LocalDateTime now);
}
