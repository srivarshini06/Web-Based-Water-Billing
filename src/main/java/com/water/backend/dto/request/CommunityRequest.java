package com.water.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityRequest {

    @NotBlank
    private String communityName;

    @NotBlank
    private String ownerName;

    @Email
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String address;
}