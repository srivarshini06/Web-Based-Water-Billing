package com.water.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulkWaterPurchaseResponse {
    private Long id;
    private Long communityId;
    private String communityName;
    private Long billingCycleId;
    private Long quantityLitres;
    private BigDecimal pricePerLitre;
    private BigDecimal totalCost;
    private LocalDateTime purchaseDate;
    private LocalDateTime deliveryDate;
    private String source;
    private String supplierName;
    private String invoiceNumber;
    private String remarks;
}