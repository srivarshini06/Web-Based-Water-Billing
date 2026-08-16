package com.water.backend.controller;

import com.water.backend.dto.response.ProcurementCostResponse;
import com.water.backend.service.ProcurementCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/procurement-cost")
@RequiredArgsConstructor
public class ProcurementCostController {

    private final ProcurementCostService
            procurementCostService;

    /*
     * ==================================================
     * CALCULATE COMMUNITY PROCUREMENT COST
     * ==================================================
     *
     * Example:
     *
     * GET
     * /api/procurement-cost/community/1
     */

    @GetMapping("/community/{communityId}")
    public ResponseEntity<ProcurementCostResponse>
    calculateProcurementCost(
            @PathVariable Long communityId) {

        return ResponseEntity.ok(
                procurementCostService
                        .calculateProcurementCost(
                                communityId
                        )
        );
    }
}