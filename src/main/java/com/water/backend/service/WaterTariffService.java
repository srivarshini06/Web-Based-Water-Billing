package com.water.backend.service;

import com.water.backend.dto.request.WaterTariffRequest;
import com.water.backend.dto.response.WaterTariffResponse;

import java.util.List;

public interface WaterTariffService {

    WaterTariffResponse createTariff(WaterTariffRequest request);

    List<WaterTariffResponse> getAllTariffs();

    List<WaterTariffResponse> getTariffsByCommunity(Long communityId);

    WaterTariffResponse getActiveTariff(Long communityId);

    WaterTariffResponse updateTariff(Long id, WaterTariffRequest request);

    void deleteTariff(Long id);
}