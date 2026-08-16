package com.water.backend.service;

import com.water.backend.dto.response.ConsumptionDistributionResponse;

import java.util.List;

public interface WaterDistributionService {

    List<ConsumptionDistributionResponse>
    calculateConsumptionDistribution(
            Long communityId,
            Long bulkPurchaseId
    );
}