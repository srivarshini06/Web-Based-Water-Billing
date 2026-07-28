package com.water.backend.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaterTariffResponse {

    private Long id;

    private Long communityId;

    private Double pricePerLitre;

    private LocalDate effectiveFrom;

    private Boolean active;
}