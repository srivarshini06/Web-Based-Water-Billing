package com.water.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.water.backend.enums.BillingCycleStatus;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingCycleResponse {
    private Long id;
    private Long communityId;
    private Date startDate;
    private Date endDate;
    private BillingCycleStatus status;
    private BigDecimal totalAmount;
    private Integer totalInvoicesGenerated;
    private Date createdAt;
    private Date finalizedAt;
    private Date archivedAt;
}