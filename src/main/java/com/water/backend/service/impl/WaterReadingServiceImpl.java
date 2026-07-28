package com.water.backend.service.impl;

import com.water.backend.dto.request.WaterReadingRequest;
import com.water.backend.dto.response.WaterReadingResponse;
import com.water.backend.mapper.WaterReadingMapper;
import com.water.backend.repository.ResidentRepository;
import com.water.backend.repository.WaterReadingRepository;
import com.water.backend.repository.WaterTariffRepository;
import com.water.backend.service.WaterReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WaterReadingServiceImpl implements WaterReadingService {

    private final WaterReadingRepository waterReadingRepository;
    private final ResidentRepository residentRepository;
    private final WaterTariffRepository waterTariffRepository;

    @Override
    public WaterReadingResponse addReading(WaterReadingRequest request) {

        // Find resident
        var resident = residentRepository.findById(request.getResidentId())
                .orElseThrow(() -> new RuntimeException("Resident not found"));

        // Get previous reading (latest reading if exists)
        Double previousReading = waterReadingRepository
                .findTopByResidentOrderByReadingDateDesc(resident)
                .map(r -> r.getCurrentReading())
                .orElse(0.0);

        // Current reading
        Double currentReading = request.getCurrentReading();

        // Validate
        if (currentReading < previousReading) {
            throw new RuntimeException(
                    "Current reading cannot be less than previous reading.");
        }

        // Calculate consumption
        Double consumption = currentReading - previousReading;

        // Get active tariff for resident's community
        var tariff = waterTariffRepository
                .findByCommunityIdAndActiveTrue(
                        resident.getCommunity().getId())
                .orElseThrow(() ->
                        new RuntimeException("Active tariff not found"));

        Double pricePerLitre = tariff.getPricePerLitre();

        // Calculate amount
        Double amount = consumption * pricePerLitre;

        // Create entity
        var reading = com.water.backend.entity.WaterReading.builder()
                .resident(resident)
                .readingDate(request.getReadingDate())
                .previousReading(previousReading)
                .currentReading(currentReading)
                .consumption(consumption)
                .tariffPerLitre(pricePerLitre)
                .amount(amount)
                .build();

        var saved = waterReadingRepository.save(reading);

        return WaterReadingMapper.toResponse(saved);
    }

    @Override
    public List<WaterReadingResponse> getAllReadings() {

        return waterReadingRepository.findAll()
                .stream()
                .map(WaterReadingMapper::toResponse)
                .toList();
    }

    @Override
    public List<WaterReadingResponse> getResidentReadings(Long residentId) {

        var resident = residentRepository.findById(residentId)
                .orElseThrow(() -> new RuntimeException("Resident not found"));

        return waterReadingRepository.findByResident(resident)
                .stream()
                .map(WaterReadingMapper::toResponse)
                .toList();
    }
}