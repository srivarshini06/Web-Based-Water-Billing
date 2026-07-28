package com.water.backend.service;

import com.water.backend.dto.request.ConsumerRequest;
import com.water.backend.dto.response.ConsumerResponse;

import java.util.List;

public interface ConsumerService {

    ConsumerResponse createConsumer(ConsumerRequest request);

    List<ConsumerResponse> getAllConsumers();

    ConsumerResponse getConsumerById(Long id);

    ConsumerResponse updateConsumer(Long id, ConsumerRequest request);

    void deleteConsumer(Long id);
}