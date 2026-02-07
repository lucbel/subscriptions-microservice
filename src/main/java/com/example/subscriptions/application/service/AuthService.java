package com.example.subscriptions.application.service;

import com.example.subscriptions.domain.exception.InvalidCredentialsException;
import com.example.subscriptions.domain.model.SessionToken;
import com.example.subscriptions.domain.model.User;
import com.example.subscriptions.domain.port.in.AuthUseCase;
import com.example.subscriptions.domain.port.out.SessionTokenRepositoryPort;
import com.example.subscriptions.domain.port.out.UserRepositoryPort;
import com.example.subscriptions.infrastructure.config.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService implements AuthUseCase {

    private final UserRepositoryPort userRepository;
    private final SessionTokenRepositoryPort sessionTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepositoryPort userRepository,
                       SessionTokenRepositoryPort sessionTokenRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.sessionTokenRepository = sessionTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public SessionToken login(LoginCommand command) {
        User user = userRepository.findByLogin(command.login())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(command.password(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(user);
        SessionToken sessionToken = SessionToken.create(
                user,
                token,
                jwtService.getExpirationTime()
        );

        return sessionTokenRepository.save(sessionToken);
    }

    @Override
    public void logout(String token) {
        sessionTokenRepository.deleteByToken(token);
    }
}
