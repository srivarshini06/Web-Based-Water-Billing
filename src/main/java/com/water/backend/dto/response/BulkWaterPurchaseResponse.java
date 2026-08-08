package com.water.backend.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulkWaterPurchaseResponse {

    private Long id;

    private Long communityId;

    private String communityName;

    private Double quantityLitres;

    private Double totalCost;

    private LocalDate purchaseDate;

    private Boolean active;
}