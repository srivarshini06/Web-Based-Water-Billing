package com.water.backend.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaterTariffRequest {

    private Long communityId;

    private Double pricePerLitre;

    private LocalDate effectiveFrom;
}