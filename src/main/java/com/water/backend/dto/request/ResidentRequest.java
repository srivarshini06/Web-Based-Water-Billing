package com.water.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResidentRequest {

    @NotBlank
    private String fullName;

    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String building;

    @NotBlank
    private String block;

    @NotBlank
    private String flatNumber;

    private Long communityId;
}