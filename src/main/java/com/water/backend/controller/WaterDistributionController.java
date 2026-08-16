package com.water.backend.controller;

import com.water.backend.dto.response.ConsumptionDistributionResponse;
import com.water.backend.service.WaterDistributionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/water-distribution")
@RequiredArgsConstructor
public class WaterDistributionController {

    private final WaterDistributionService
            waterDistributionService;

    /*
     * ==================================================
     * CONSUMPTION-BASED DISTRIBUTION
     * ==================================================
     *
     * Example:
     *
     * GET
     * /api/water-distribution/community/1/purchase/5
     *
     * ==================================================
     */

    @GetMapping(
            "/community/{communityId}/purchase/{bulkPurchaseId}"
    )
    public ResponseEntity<
            List<ConsumptionDistributionResponse>
            > calculateDistribution(

            @PathVariable Long communityId,

            @PathVariable Long bulkPurchaseId) {

        return ResponseEntity.ok(
                waterDistributionService
                        .calculateConsumptionDistribution(
                                communityId,
                                bulkPurchaseId
                        )
        );
    }
}