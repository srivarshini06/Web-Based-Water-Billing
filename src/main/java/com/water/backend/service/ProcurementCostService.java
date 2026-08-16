package com.water.backend.service;

import com.water.backend.dto.response.ProcurementCostResponse;

public interface ProcurementCostService {

    ProcurementCostResponse calculateProcurementCost(
            Long communityId
    );
}