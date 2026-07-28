package com.water.backend.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumerResponse {

    private Long id;
    private String consumerName;
    private String connectionNumber;
    private String address;
    private String phoneNumber;
    private String email;
}