package com.water.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaterTariffRequest {

    @NotNull(message = "Community ID is required")
    private Long communityId;

    @NotNull(message = "Price per litre is required")
    @Positive(message = "Price per litre must be greater than zero")
    private Double pricePerLitre;

    @NotNull(message = "Effective date is required")
    private LocalDate effectiveFrom;
}