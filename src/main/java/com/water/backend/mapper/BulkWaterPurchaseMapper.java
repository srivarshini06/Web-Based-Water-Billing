package com.water.backend.mapper;

import com.water.backend.dto.request.BulkWaterPurchaseRequest;
import com.water.backend.dto.response.BulkWaterPurchaseResponse;
import com.water.backend.entity.BulkWaterPurchase;
import com.water.backend.entity.Community;

public class BulkWaterPurchaseMapper {

    private BulkWaterPurchaseMapper() {
        // Utility class
    }

    public static BulkWaterPurchase toEntity(
            BulkWaterPurchaseRequest request,
            Community community) {

        return BulkWaterPurchase.builder()
                .community(community)
                .quantityLitres(request.getQuantityLitres())
                .totalCost(request.getTotalCost())
                .purchaseDate(request.getPurchaseDate())
                .active(true)
                .build();
    }

    public static BulkWaterPurchaseResponse toResponse(
            BulkWaterPurchase purchase) {

        return BulkWaterPurchaseResponse.builder()
                .id(purchase.getId())
                .communityId(purchase.getCommunity().getId())
                .communityName(purchase.getCommunity().getCommunityName())
                .quantityLitres(purchase.getQuantityLitres())
                .totalCost(purchase.getTotalCost())
                .purchaseDate(purchase.getPurchaseDate())
                .active(purchase.getActive())
                .build();
    }
}