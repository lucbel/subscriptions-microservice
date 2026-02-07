package com.example.subscriptions.domain.port.out;

import com.example.subscriptions.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByLogin(String login);

    List<User> findAll();

    void deleteById(Long id);

    boolean existsByLogin(String login);
}
