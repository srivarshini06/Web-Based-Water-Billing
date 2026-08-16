package com.water.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {
    private Long billingCycleId;
    private Long consumerId;
    private BigDecimal amountDue;
    private String remarks;
}