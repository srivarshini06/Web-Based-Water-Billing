package com.water.backend.service.impl;

import com.water.backend.dto.request.WaterMeterRequest;
import com.water.backend.dto.response.WaterMeterResponse;
import com.water.backend.entity.Resident;
import com.water.backend.entity.WaterMeter;
import com.water.backend.mapper.WaterMeterMapper;
import com.water.backend.repository.ResidentRepository;
import com.water.backend.repository.WaterMeterRepository;
import com.water.backend.service.WaterMeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WaterMeterServiceImpl
        implements WaterMeterService {

    private final WaterMeterRepository waterMeterRepository;

    private final ResidentRepository residentRepository;

    @Override
    @Transactional
    public WaterMeterResponse createWaterMeter(
            WaterMeterRequest request
    ) {

        Resident resident =
                residentRepository.findById(
                        request.getResidentId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Resident not found."
                        )
                );

        if (waterMeterRepository
                .findByMeterNumber(request.getMeterNumber())
                .isPresent()) {

            throw new IllegalArgumentException(
                    "Meter number already exists."
            );
        }

        WaterMeter meter =
                WaterMeter.builder()
                        .resident(resident)
                        .meterNumber(
                                request.getMeterNumber()
                        )
                        .installationDate(
                                request.getInstallationDate()
                        )
                        .initialReading(
                                request.getInitialReading()
                        )
                        .active(true)
                        .build();

        WaterMeter saved =
                waterMeterRepository.save(meter);

        return WaterMeterMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public WaterMeterResponse getWaterMeterByResident(
            Long residentId
    ) {

        Resident resident =
                residentRepository.findById(residentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Resident not found."
                                )
                        );

        return waterMeterRepository
                .findByResidentAndActiveTrue(resident)
                .map(WaterMeterMapper::toResponse)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No active water meter found for resident."
                        )
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<WaterMeterResponse> getWaterMetersByResident(
            Long residentId
    ) {

        Resident resident =
                residentRepository.findById(residentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Resident not found."
                                )
                        );

        return waterMeterRepository
                .findByResident(resident)
                .stream()
                .map(WaterMeterMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public WaterMeterResponse getWaterMeterById(
            Long meterId
    ) {

        return waterMeterRepository
                .findById(meterId)
                .map(WaterMeterMapper::toResponse)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Water meter not found."
                        )
                );
    }

    @Override
    @Transactional
    public WaterMeterResponse updateWaterMeter(
            Long meterId,
            WaterMeterRequest request
    ) {

        WaterMeter meter =
                waterMeterRepository.findById(meterId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Water meter not found."
                                )
                        );

        Resident resident =
                residentRepository.findById(
                        request.getResidentId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Resident not found."
                        )
                );

        if (!meter.getMeterNumber()
                .equals(request.getMeterNumber())
                &&
                waterMeterRepository
                        .existsByMeterNumberAndIdNot(
                                request.getMeterNumber(),
                                meterId
                        )) {

            throw new IllegalArgumentException(
                    "Meter number already exists."
            );
        }

        meter.setResident(resident);

        meter.setMeterNumber(
                request.getMeterNumber()
        );

        meter.setInstallationDate(
                request.getInstallationDate()
        );

        meter.setInitialReading(
                request.getInitialReading()
        );

        WaterMeter updated =
                waterMeterRepository.save(meter);

        return WaterMeterMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteWaterMeter(
            Long meterId
    ) {

        WaterMeter meter =
                waterMeterRepository.findById(meterId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Water meter not found."
                                )
                        );

        /*
         * Soft delete.
         * We do not physically remove the meter because
         * historical readings may depend on it.
         */
        meter.setActive(false);

        waterMeterRepository.save(meter);
    }
}