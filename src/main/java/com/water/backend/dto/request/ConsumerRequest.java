package com.water.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumerRequest {

    @NotBlank
    private String consumerName;

    @NotBlank
    private String connectionNumber;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    @Email
    private String email;
}