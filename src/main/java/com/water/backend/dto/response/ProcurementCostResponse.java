package com.water.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcurementCostResponse {

    private Long communityId;

    private String communityName;

    private Double totalPurchasedLitres;

    private Double totalProcurementCost;

    private Double averageProcurementCostPerLitre;
}