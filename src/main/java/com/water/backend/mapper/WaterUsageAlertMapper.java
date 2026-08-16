package com.water.backend.mapper;

import com.water.backend.dto.response.WaterUsageAlertResponse;
import com.water.backend.entity.WaterUsageAlert;

public final class WaterUsageAlertMapper {
    private WaterUsageAlertMapper() {}
    public static WaterUsageAlertResponse toResponse(WaterUsageAlert e) {
        if (e == null) return null;
        return WaterUsageAlertResponse.builder()
                .id(e.getId()).residentId(e.getResident() == null ? null : e.getResident().getId())
                .residentName(e.getResident() == null ? null : e.getResident().getFullName())
                .alertType(e.getAlertType()).message(e.getMessage()).detectedValue(e.getDetectedValue())
                .threshold(e.getThreshold()).acknowledged(e.getAcknowledged()).createdAt(e.getCreatedAt()).build();
    }
}
