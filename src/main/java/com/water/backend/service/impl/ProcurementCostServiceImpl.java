package com.water.backend.service.impl;

import com.water.backend.dto.response.ProcurementCostResponse;
import com.water.backend.entity.BulkWaterPurchase;
import com.water.backend.entity.Community;
import com.water.backend.repository.BulkWaterPurchaseRepository;
import com.water.backend.repository.CommunityRepository;
import com.water.backend.service.ProcurementCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcurementCostServiceImpl
        implements ProcurementCostService {

    private final BulkWaterPurchaseRepository
            bulkWaterPurchaseRepository;

    private final CommunityRepository
            communityRepository;

    @Override
    @Transactional(readOnly = true)
    public ProcurementCostResponse calculateProcurementCost(
            Long communityId) {

        /*
         * ------------------------------------------
         * 1. FIND COMMUNITY
         * ------------------------------------------
         */

        Community community =
                communityRepository.findById(communityId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Community not found."
                                )
                        );

        /*
         * ------------------------------------------
         * 2. GET PURCHASES
         * ------------------------------------------
         */

        List<BulkWaterPurchase> purchases =
                bulkWaterPurchaseRepository
                        .findByCommunityIdOrderByPurchaseDateDesc(
                                communityId
                        );

        /*
         * ------------------------------------------
         * 3. MAKE SURE PURCHASES EXIST
         * ------------------------------------------
         */

        if (purchases.isEmpty()) {

            throw new RuntimeException(
                    "No bulk water purchases found for this community."
            );
        }

        /*
         * ------------------------------------------
         * 4. CALCULATE TOTALS
         * ------------------------------------------
         */

        double totalLitres = 0.0;

        double totalCost = 0.0;

        for (BulkWaterPurchase purchase : purchases) {

            if (purchase.getQuantityLitres() == null
                    || purchase.getQuantityLitres() <= 0) {

                continue;
            }

            if (purchase.getTotalCost() == null
                    || purchase.getTotalCost() < 0) {

                continue;
            }

            totalLitres +=
                    purchase.getQuantityLitres();

            totalCost +=
                    purchase.getTotalCost();
        }

        /*
         * ------------------------------------------
         * 5. VALIDATE TOTAL
         * ------------------------------------------
         */

        if (totalLitres <= 0) {

            throw new RuntimeException(
                    "Total purchased water must be greater than zero."
            );
        }

        /*
         * ------------------------------------------
         * 6. EFFECTIVE PROCUREMENT COST
         * ------------------------------------------
         *
         * Total procurement cost
         * -----------------------
         * Total purchased litres
         */

        double averageCostPerLitre =
                totalCost / totalLitres;

        /*
         * ------------------------------------------
         * 7. BUILD RESPONSE
         * ------------------------------------------
         */

        return ProcurementCostResponse.builder()
                .communityId(
                        community.getId()
                )
                .communityName(
                        community.getCommunityName()
                )
                .totalPurchasedLitres(
                        totalLitres
                )
                .totalProcurementCost(
                        totalCost
                )
                .averageProcurementCostPerLitre(
                        averageCostPerLitre
                )
                .build();
    }
}