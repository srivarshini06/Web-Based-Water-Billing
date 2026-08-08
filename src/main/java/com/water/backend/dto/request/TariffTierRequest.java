package com.water.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TariffTierRequest {

    @NotNull(message = "Minimum litres is required")
    @PositiveOrZero(message = "Minimum litres cannot be negative")
    private Double minLitres;

    @PositiveOrZero(message = "Maximum litres cannot be negative")
    private Double maxLitres;

    @NotNull(message = "Price per litre is required")
    @Positive(message = "Price per litre must be greater than zero")
    private Double pricePerLitre;
}