package com.water.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceResponse {
    private Long id;
    private Long billingCycleId;
    private Long consumerId;
    private String consumerName;
    private String cyclePeriod;
    private BigDecimal amountDue;
    private Date issueDate;
    private Date dueDate;
    private Date paidDate;
    private String status;
    private String remarks;
    private List<InvoiceItemResponse> lineItems;
}