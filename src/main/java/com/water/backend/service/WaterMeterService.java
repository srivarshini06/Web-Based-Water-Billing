package com.water.backend.service;

import com.water.backend.dto.request.WaterMeterRequest;
import com.water.backend.dto.response.WaterMeterResponse;

import java.util.List;

public interface WaterMeterService {

    WaterMeterResponse createWaterMeter(
            WaterMeterRequest request
    );

    WaterMeterResponse getWaterMeterByResident(
            Long residentId
    );

    List<WaterMeterResponse> getWaterMetersByResident(
            Long residentId
    );

    WaterMeterResponse getWaterMeterById(
            Long meterId
    );

    WaterMeterResponse updateWaterMeter(
            Long meterId,
            WaterMeterRequest request
    );

    void deleteWaterMeter(
            Long meterId
    );
}