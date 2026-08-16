package com.water.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingCycleRequest {
    @NotNull private Long communityId;
    @NotNull private LocalDate startDate;
    @NotNull private LocalDate endDate;
}
