package com.water.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulkWaterPurchaseRequest {
    private Long communityId;
    private Long billingCycleId;
    private Long quantityLitres;
    private BigDecimal pricePerLitre;
    private LocalDateTime purchaseDate;
    private LocalDateTime deliveryDate;
    private String source;
    private String supplierName;
    private String invoiceNumber;
    private String remarks;
}