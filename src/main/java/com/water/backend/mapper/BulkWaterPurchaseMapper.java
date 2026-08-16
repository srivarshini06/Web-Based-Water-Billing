package com.water.backend.mapper;

import com.water.backend.dto.response.BulkWaterPurchaseResponse;
import com.water.backend.entity.BulkWaterPurchase;
import org.springframework.stereotype.Component;

@Component
public class BulkWaterPurchaseMapper {

    public BulkWaterPurchaseResponse toResponse(BulkWaterPurchase purchase) {
        if (purchase == null) {
            return null;
        }

        return BulkWaterPurchaseResponse.builder()
                .id(purchase.getId())
                .communityId(purchase.getCommunityId())
                .communityName(purchase.getCommunity() != null ? purchase.getCommunity().getCommunityName() : "")
                .billingCycleId(purchase.getBillingCycle() != null ? purchase.getBillingCycle().getId() : null)
                .quantityLitres(purchase.getQuantityLitres())
                .pricePerLitre(purchase.getPricePerLitre())
                .totalCost(purchase.getTotalCost())
                .purchaseDate(purchase.getPurchaseDate())
                .deliveryDate(purchase.getDeliveryDate())
                .source(purchase.getSource())
                .supplierName(purchase.getSupplierName())
                .invoiceNumber(purchase.getInvoiceNumber())
                .remarks(purchase.getRemarks())
                .build();
    }
}