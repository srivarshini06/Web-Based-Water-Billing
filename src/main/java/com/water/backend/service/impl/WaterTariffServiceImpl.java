package com.water.backend.service.impl;

import com.water.backend.dto.request.WaterTariffRequest;
import com.water.backend.dto.request.TariffTierRequest;
import com.water.backend.dto.response.WaterTariffResponse;
import com.water.backend.entity.WaterTariff;
import com.water.backend.entity.TariffTier;
import com.water.backend.exception.ResourceNotFoundException;
import com.water.backend.mapper.WaterTariffMapper;
import com.water.backend.repository.WaterTariffRepository;
import com.water.backend.repository.TariffTierRepository;
import com.water.backend.service.WaterTariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WaterTariffServiceImpl implements WaterTariffService {

    private final WaterTariffRepository waterTariffRepository;
    private final TariffTierRepository tariffTierRepository;
    private final WaterTariffMapper waterTariffMapper;

    @Override
    @Transactional(readOnly = true)
    public WaterTariffResponse getTariffById(Long id) {
        WaterTariff tariff = waterTariffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Water tariff not found with id: " + id));
        return waterTariffMapper.toResponse(tariff);
    }

    @Override
    @Transactional(readOnly = true)
    public WaterTariffResponse getTariffByCommunityId(Long communityId) {
        WaterTariff tariff = waterTariffRepository.findByCommunityId(communityId)
                .orElseThrow(() -> new ResourceNotFoundException("Tariff not found for community: " + communityId));
        return waterTariffMapper.toResponse(tariff);
    }

    @Override
    @Transactional
    public WaterTariffResponse createTariff(WaterTariffRequest request) {
        WaterTariff tariff = new WaterTariff();
        tariff.setCommunityId(request.getCommunityId());
        tariff.setName(request.getName());
        tariff.setDescription(request.getDescription());
        tariff.setEffectiveFrom(request.getEffectiveFrom());

        WaterTariff saved = waterTariffRepository.save(tariff);
        log.info("Tariff created: {} for community: {}", saved.getId(), request.getCommunityId());
        return waterTariffMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public WaterTariffResponse updateTariff(Long id, WaterTariffRequest request) {
        WaterTariff tariff = waterTariffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tariff not found: " + id));

        tariff.setName(request.getName());
        tariff.setDescription(request.getDescription());
        tariff.setEffectiveFrom(request.getEffectiveFrom());

        WaterTariff updated = waterTariffRepository.save(tariff);
        log.info("Tariff updated: {}", id);
        return waterTariffMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteTariff(Long id) {
        WaterTariff tariff = waterTariffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tariff not found: " + id));

        tariffTierRepository.deleteByTariffId(id);
        waterTariffRepository.delete(tariff);
        log.info("Tariff deleted: {}", id);
    }

    @Override
    @Transactional
    public void addTariffTier(Long tariffId, TariffTierRequest request) {
        WaterTariff tariff = waterTariffRepository.findById(tariffId)
                .orElseThrow(() -> new ResourceNotFoundException("Tariff not found: " + tariffId));

        TariffTier tier = new TariffTier();
        tier.setTariff(tariff);
        tier.setMinVolume(request.getMinVolume());
        tier.setMaxVolume(request.getMaxVolume());
        tier.setRate(request.getRate());

        tariffTierRepository.save(tier);
        log.info("Tier added to tariff: {}", tariffId);
    }

    /**
     * CORE: Calculate household water charge based on tiered tariff
     * Example: 0-10kL @ Rs 20/kL, 10-20kL @ Rs 30/kL, 20+ @ Rs 50/kL
     * If household uses 15kL: (10 * 20) + (5 * 30) = 200 + 150 = Rs 350
     */
    @Override
    @Transactional(readOnly = true)
    public BigDecimal calculateChargeForConsumption(Long tariffId, BigDecimal consumptionKL) {
        List<TariffTier> tiers = tariffTierRepository.findByTariffIdOrderByMinVolumeAsc(tariffId);

        if (tiers.isEmpty()) {
            throw new RuntimeException("No tariff tiers defined for tariff: " + tariffId);
        }

        BigDecimal totalCharge = BigDecimal.ZERO;
        BigDecimal remainingConsumption = consumptionKL;

        for (TariffTier tier : tiers) {
            if (remainingConsumption.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }

            // Calculate volume applicable to this tier
            BigDecimal tierMax = tier.getMaxVolume() != null ? tier.getMaxVolume() : consumptionKL;
            BigDecimal tierMin = tier.getMinVolume() != null ? tier.getMinVolume() : BigDecimal.ZERO;
            BigDecimal tierWidth = tierMax.subtract(tierMin);

            BigDecimal applicableVolume = remainingConsumption.min(tierWidth);
            BigDecimal tierCharge = applicableVolume.multiply(tier.getRate());

            totalCharge = totalCharge.add(tierCharge);
            remainingConsumption = remainingConsumption.subtract(applicableVolume);
        }

        log.debug("Calculated charge: Rs {} for consumption: {} kL", totalCharge, consumptionKL);
        return totalCharge;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WaterTariffResponse> getAllTariffs() {
        return waterTariffRepository.findAll().stream()
                .map(waterTariffMapper::toResponse)
                .toList();
    }
}