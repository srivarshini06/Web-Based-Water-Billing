package com.water.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintRequest {

    @NotNull
    private Long residentId;

    @NotBlank
    private String subject;

    @NotBlank
    private String description;
}