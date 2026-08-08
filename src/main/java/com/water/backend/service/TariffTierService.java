package com.water.backend.service;

import com.water.backend.dto.request.TariffTierRequest;
import com.water.backend.dto.response.TariffTierResponse;

import java.util.List;

public interface TariffTierService {

    TariffTierResponse createTier(
            Long tariffId,
            TariffTierRequest request
    );

    List<TariffTierResponse> getTiersByTariff(
            Long tariffId
    );

    TariffTierResponse getTierById(
            Long id
    );

    TariffTierResponse updateTier(
            Long id,
            TariffTierRequest request
    );

    void deleteTier(
            Long id
    );
}