package com.water.backend.controller;

import com.water.backend.dto.request.ConsumerRequest;
import com.water.backend.dto.response.ConsumerResponse;
import com.water.backend.service.ConsumerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumers")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumerResponse createConsumer(
            @Valid @RequestBody ConsumerRequest request) {

        return consumerService.createConsumer(request);
    }

    @GetMapping
    public List<ConsumerResponse> getAllConsumers() {

        return consumerService.getAllConsumers();
    }

    @GetMapping("/{id}")
    public ConsumerResponse getConsumerById(@PathVariable Long id) {

        return consumerService.getConsumerById(id);
    }

    @PutMapping("/{id}")
    public ConsumerResponse updateConsumer(
            @PathVariable Long id,
            @Valid @RequestBody ConsumerRequest request) {

        return consumerService.updateConsumer(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteConsumer(@PathVariable Long id) {

        consumerService.deleteConsumer(id);
        return "Consumer deleted successfully.";
    }
}