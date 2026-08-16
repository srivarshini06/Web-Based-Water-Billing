package com.water.backend.mapper;

import com.water.backend.dto.response.BillingCycleResponse;
import com.water.backend.entity.BillingCycle;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class BillingCycleMapper {

    public BillingCycleResponse toResponse(BillingCycle cycle) {
        if (cycle == null) {
            return null;
        }

        return BillingCycleResponse.builder()
                .id(cycle.getId())
                .communityId(cycle.getCommunityId())
                .startDate(cycle.getStartDate())
                .endDate(cycle.getEndDate())
                .status(cycle.getStatus())
                .totalAmount(cycle.getTotalAmount())
                .totalInvoicesGenerated(cycle.getTotalInvoicesGenerated())
                .createdAt(convertLocalDateTimeToDate(cycle.getCreatedAt()))
                .finalizedAt(convertLocalDateTimeToDate(cycle.getFinalizedAt()))
                .archivedAt(convertLocalDateTimeToDate(cycle.getArchivedAt()))
                .build();
    }

    private Date convertLocalDateTimeToDate(java.time.LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return java.sql.Timestamp.valueOf(localDateTime);
    }
}