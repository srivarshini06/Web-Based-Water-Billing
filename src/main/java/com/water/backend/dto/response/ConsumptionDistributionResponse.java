package com.water.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsumptionDistributionResponse {

    private Long residentId;

    private String residentName;

    private Double consumptionLitres;

    private Double consumptionPercentage;

    private Double allocatedCost;
}