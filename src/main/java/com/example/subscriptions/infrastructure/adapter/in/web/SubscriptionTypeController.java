package com.example.subscriptions.infrastructure.adapter.in.web;

import com.example.subscriptions.domain.port.in.SubscriptionTypeUseCase;
import com.example.subscriptions.infrastructure.adapter.in.web.dto.SubscriptionTypeRequest;
import com.example.subscriptions.infrastructure.adapter.in.web.dto.SubscriptionTypeResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription-types")
public class SubscriptionTypeController {

    private final SubscriptionTypeUseCase subscriptionTypeUseCase;

    public SubscriptionTypeController(SubscriptionTypeUseCase subscriptionTypeUseCase) {
        this.subscriptionTypeUseCase = subscriptionTypeUseCase;
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionTypeResponse>> getAllSubscriptionTypes() {
        var types = subscriptionTypeUseCase.getAllSubscriptionTypes().stream()
                .map(SubscriptionTypeResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionTypeResponse> getSubscriptionTypeById(@PathVariable Long id) {
        var type = subscriptionTypeUseCase.getSubscriptionTypeById(id);
        return ResponseEntity.ok(SubscriptionTypeResponse.fromDomain(type));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SubscriptionTypeResponse> getSubscriptionTypeByName(@PathVariable String name) {
        var type = subscriptionTypeUseCase.getSubscriptionTypeByName(name);
        return ResponseEntity.ok(SubscriptionTypeResponse.fromDomain(type));
    }

    @PostMapping
    public ResponseEntity<SubscriptionTypeResponse> createSubscriptionType(
            @Valid @RequestBody SubscriptionTypeRequest request) {
        var command = new SubscriptionTypeUseCase.CreateSubscriptionTypeCommand(
                request.name(),
                request.price(),
                request.description()
        );
        var type = subscriptionTypeUseCase.createSubscriptionType(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(SubscriptionTypeResponse.fromDomain(type));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionTypeResponse> updateSubscriptionType(
            @PathVariable Long id,
            @Valid @RequestBody SubscriptionTypeRequest request) {
        var command = new SubscriptionTypeUseCase.UpdateSubscriptionTypeCommand(
                request.name(),
                request.price(),
                request.description()
        );
        var type = subscriptionTypeUseCase.updateSubscriptionType(id, command);
        return ResponseEntity.ok(SubscriptionTypeResponse.fromDomain(type));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscriptionType(@PathVariable Long id) {
        subscriptionTypeUseCase.deleteSubscriptionType(id);
        return ResponseEntity.noContent().build();
    }
}
