package com.water.backend.controller;

import com.water.backend.dto.request.ConsumerRequest;
import com.water.backend.dto.response.ConsumerResponse;
import com.water.backend.service.ConsumerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumers")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @Operation(summary = "Create consumer")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ConsumerResponse> createConsumer(
            @Valid @RequestBody ConsumerRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(consumerService.createConsumer(request));
    }

    @Operation(summary = "Get all consumers")
    @GetMapping
    public ResponseEntity<List<ConsumerResponse>> getAllConsumers() {

        return ResponseEntity.ok(
                consumerService.getAllConsumers());
    }

    @Operation(summary = "Get consumer by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ConsumerResponse> getConsumerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                consumerService.getConsumerById(id));
    }

    @Operation(summary = "Update consumer")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ConsumerResponse> updateConsumer(
            @PathVariable Long id,
            @Valid @RequestBody ConsumerRequest request) {

        return ResponseEntity.ok(
                consumerService.updateConsumer(id, request));
    }

    @Operation(summary = "Delete consumer")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConsumer(
            @PathVariable Long id) {

        consumerService.deleteConsumer(id);

        return ResponseEntity.ok(
                "Consumer deleted successfully.");
    }
}