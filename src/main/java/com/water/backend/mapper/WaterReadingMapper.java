package com.water.backend.mapper;

import com.water.backend.dto.response.WaterReadingResponse;
import com.water.backend.entity.WaterReading;

public class WaterReadingMapper {

    public static WaterReadingResponse toResponse(WaterReading reading) {

        return WaterReadingResponse.builder()
                .id(reading.getId())
                .residentId(reading.getResident().getId())
                .residentName(reading.getResident().getFullName())
                .readingDate(reading.getReadingDate())
                .previousReading(reading.getPreviousReading())
                .currentReading(reading.getCurrentReading())
                .consumption(reading.getConsumption())
                .tariffPerLitre(reading.getTariffPerLitre())
                .amount(reading.getAmount())
                .build();
    }
}