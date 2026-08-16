package com.water.backend.mapper;

import com.water.backend.dto.response.WaterMeterResponse;
import com.water.backend.entity.WaterMeter;

public class WaterMeterMapper {

    private WaterMeterMapper() {
        // Utility class
    }

    public static WaterMeterResponse toResponse(
            WaterMeter entity
    ) {

        if (entity == null) {
            return null;
        }

        return WaterMeterResponse.builder()
                .id(entity.getId())
                .residentId(
                        entity.getResident() != null
                                ? entity.getResident().getId()
                                : null
                )
                .meterNumber(entity.getMeterNumber())
                .installationDate(entity.getInstallationDate())
                .initialReading(entity.getInitialReading())
                .active(entity.getActive())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}