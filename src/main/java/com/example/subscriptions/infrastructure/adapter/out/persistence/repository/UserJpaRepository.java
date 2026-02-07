package com.example.subscriptions.infrastructure.adapter.out.persistence.repository;

import com.example.subscriptions.infrastructure.adapter.out.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

    Optional<UserJpaEntity> findByLogin(String login);

    boolean existsByLogin(String login);
}
