package com.water.backend.service.impl;

import com.water.backend.dto.request.ConsumerRequest;
import com.water.backend.dto.response.ConsumerResponse;
import com.water.backend.entity.Consumer;
import com.water.backend.exception.ResourceAlreadyExistsException;
import com.water.backend.mapper.ConsumerMapper;
import com.water.backend.repository.ConsumerRepository;
import com.water.backend.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository consumerRepository;

    @Override
    public ConsumerResponse createConsumer(ConsumerRequest request) {

        if (consumerRepository.existsByConnectionNumber(request.getConnectionNumber())) {
            throw new ResourceAlreadyExistsException("Connection number already exists.");
        }

        if (consumerRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists.");
        }

        Consumer consumer = ConsumerMapper.toEntity(request);

        Consumer saved = consumerRepository.save(consumer);

        return ConsumerMapper.toResponse(saved);
    }

    @Override
    public List<ConsumerResponse> getAllConsumers() {

        return consumerRepository.findAll()
                .stream()
                .map(ConsumerMapper::toResponse)
                .toList();
    }

    @Override
    public ConsumerResponse getConsumerById(Long id) {

        Consumer consumer = consumerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumer not found."));

        return ConsumerMapper.toResponse(consumer);
    }

    @Override
       public ConsumerResponse updateConsumer(Long id, ConsumerRequest request) {

        Consumer consumer = consumerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumer not found."));

        if (!consumer.getConnectionNumber().equals(request.getConnectionNumber())
                && consumerRepository.existsByConnectionNumber(request.getConnectionNumber())) {

            throw new ResourceAlreadyExistsException("Connection number already exists.");
        }

        if (!consumer.getEmail().equals(request.getEmail())
                && consumerRepository.existsByEmail(request.getEmail())) {

            throw new ResourceAlreadyExistsException("Email already exists.");
        }

        consumer.setConsumerName(request.getConsumerName());
        consumer.setConnectionNumber(request.getConnectionNumber());
        consumer.setAddress(request.getAddress());
        consumer.setPhoneNumber(request.getPhoneNumber());
        consumer.setEmail(request.getEmail());

        Consumer updated = consumerRepository.save(consumer);

        return ConsumerMapper.toResponse(updated);
    }

    @Override
    public void deleteConsumer(Long id) {

        consumerRepository.deleteById(id);
    }
}