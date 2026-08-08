package com.water.backend.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TariffTierResponse {

    private Long id;

    private Long tariffId;

    private Double minLitres;

    private Double maxLitres;

    private Double pricePerLitre;
}