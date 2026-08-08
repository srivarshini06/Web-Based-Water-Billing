package com.water.backend.mapper;

import com.water.backend.dto.request.TariffTierRequest;
import com.water.backend.dto.response.TariffTierResponse;
import com.water.backend.entity.TariffTier;
import com.water.backend.entity.WaterTariff;

public class TariffTierMapper {

    private TariffTierMapper() {
        // Utility class
    }

    public static TariffTier toEntity(
            TariffTierRequest request,
            WaterTariff tariff) {

        return TariffTier.builder()
                .tariff(tariff)
                .minLitres(request.getMinLitres())
                .maxLitres(request.getMaxLitres())
                .pricePerLitre(request.getPricePerLitre())
                .build();
    }

    public static TariffTierResponse toResponse(TariffTier tier) {

        return TariffTierResponse.builder()
                .id(tier.getId())
                .tariffId(tier.getTariff().getId())
                .minLitres(tier.getMinLitres())
                .maxLitres(tier.getMaxLitres())
                .pricePerLitre(tier.getPricePerLitre())
                .build();
    }
}