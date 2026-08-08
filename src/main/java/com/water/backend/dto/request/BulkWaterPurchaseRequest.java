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
public class BulkWaterPurchaseRequest {

    @NotNull(message = "Community ID is required")
    private Long communityId;

    @NotNull(message = "Quantity in litres is required")
    @Positive(message = "Quantity must be greater than zero")
    private Double quantityLitres;

    @NotNull(message = "Total cost is required")
    @Positive(message = "Total cost must be greater than zero")
    private Double totalCost;

    @NotNull(message = "Purchase date is required")
    private LocalDate purchaseDate;
}