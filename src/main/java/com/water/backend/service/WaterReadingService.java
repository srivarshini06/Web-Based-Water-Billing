package com.water.backend.service;

import com.water.backend.dto.request.WaterReadingRequest;
import com.water.backend.dto.response.WaterReadingResponse;

import java.util.List;

public interface WaterReadingService {

    WaterReadingResponse addReading(WaterReadingRequest request);

    List<WaterReadingResponse> getAllReadings();

    List<WaterReadingResponse> getResidentReadings(Long residentId);
}