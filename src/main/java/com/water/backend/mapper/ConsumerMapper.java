package com.water.backend.mapper;

import com.water.backend.dto.request.ConsumerRequest;
import com.water.backend.dto.response.ConsumerResponse;
import com.water.backend.entity.Consumer;

public class ConsumerMapper {

    public static Consumer toEntity(ConsumerRequest request) {

        return Consumer.builder()
                .consumerName(request.getConsumerName())
                .connectionNumber(request.getConnectionNumber())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();
    }

    public static ConsumerResponse toResponse(Consumer consumer) {

        return ConsumerResponse.builder()
                .id(consumer.getId())
                .consumerName(consumer.getConsumerName())
                .connectionNumber(consumer.getConnectionNumber())
                .address(consumer.getAddress())
                .phoneNumber(consumer.getPhoneNumber())
                .email(consumer.getEmail())
                .build();
    }
}