package com.example.subscriptions.infrastructure.adapter.in.web;

import com.example.subscriptions.domain.port.in.AuthUseCase;
import com.example.subscriptions.infrastructure.adapter.in.web.dto.LoginRequest;
import com.example.subscriptions.infrastructure.adapter.in.web.dto.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        var command = new AuthUseCase.LoginCommand(
                request.login(),
                request.password()
        );
        var sessionToken = authUseCase.login(command);
        return ResponseEntity.ok(LoginResponse.fromDomain(sessionToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        authUseCase.logout(token);
        return ResponseEntity.noContent().build();
    }
}
