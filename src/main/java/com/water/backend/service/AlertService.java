package com.water.backend.service;

import com.water.backend.dto.response.WaterUsageAlertResponse;
import java.util.List;

public interface AlertService {
    void detectThresholdViolations();
    void detectOutliers();
    List<WaterUsageAlertResponse> getAlertsByResident(Long residentId);
    WaterUsageAlertResponse acknowledgeAlert(Long alertId);
}
