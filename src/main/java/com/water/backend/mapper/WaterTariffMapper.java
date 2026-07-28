package com.water.backend.mapper;

import com.water.backend.dto.request.WaterTariffRequest;
import com.water.backend.dto.response.WaterTariffResponse;
import com.water.backend.entity.Community;
import com.water.backend.entity.WaterTariff;

public class WaterTariffMapper {

    public static WaterTariff toEntity(
            WaterTariffRequest request,
            Community community) {

        return WaterTariff.builder()
                .community(community)
                .pricePerLitre(request.getPricePerLitre())
                .effectiveFrom(request.getEffectiveFrom())
                .active(true)
                .build();
    }

    public static WaterTariffResponse toResponse(WaterTariff tariff) {

        return WaterTariffResponse.builder()
                .id(tariff.getId())
                .communityId(tariff.getCommunity().getId())
                .pricePerLitre(tariff.getPricePerLitre())
                .effectiveFrom(tariff.getEffectiveFrom())
                .active(tariff.getActive())
                .build();
    }
}