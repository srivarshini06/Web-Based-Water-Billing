package com.water.backend.service.impl;

import com.water.backend.dto.request.WaterTariffRequest;
import com.water.backend.dto.response.WaterTariffResponse;
import com.water.backend.entity.Community;
import com.water.backend.entity.WaterTariff;
import com.water.backend.mapper.WaterTariffMapper;
import com.water.backend.repository.CommunityRepository;
import com.water.backend.repository.WaterTariffRepository;
import com.water.backend.service.WaterTariffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WaterTariffServiceImpl implements WaterTariffService {

    private final WaterTariffRepository waterTariffRepository;
    private final CommunityRepository communityRepository;

    @Override
    public WaterTariffResponse createTariff(WaterTariffRequest request) {

        Community community = communityRepository.findById(request.getCommunityId())
                .orElseThrow(() -> new RuntimeException("Community not found."));

        waterTariffRepository.findByCommunityIdAndActiveTrue(request.getCommunityId())
                .ifPresent(existing -> {
                    existing.setActive(false);
                    waterTariffRepository.save(existing);
                });

        WaterTariff tariff = WaterTariffMapper.toEntity(request, community);

        WaterTariff saved = waterTariffRepository.save(tariff);

        return WaterTariffMapper.toResponse(saved);
    }

    @Override
    public List<WaterTariffResponse> getAllTariffs() {

        return waterTariffRepository.findAll()
                .stream()
                .map(WaterTariffMapper::toResponse)
                .toList();
    }

    @Override
    public List<WaterTariffResponse> getTariffsByCommunity(Long communityId) {

        return waterTariffRepository.findByCommunityId(communityId)
                .stream()
                .map(WaterTariffMapper::toResponse)
                .toList();
    }

    @Override
    public WaterTariffResponse getActiveTariff(Long communityId) {

        WaterTariff tariff = waterTariffRepository
                .findByCommunityIdAndActiveTrue(communityId)
                .orElseThrow(() -> new RuntimeException("No active tariff found."));

        return WaterTariffMapper.toResponse(tariff);
    }

    @Override
    public WaterTariffResponse updateTariff(Long id, WaterTariffRequest request) {

        WaterTariff tariff = waterTariffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tariff not found."));

        Community community = communityRepository.findById(request.getCommunityId())
                .orElseThrow(() -> new RuntimeException("Community not found."));

        tariff.setCommunity(community);
        tariff.setPricePerLitre(request.getPricePerLitre());
        tariff.setEffectiveFrom(request.getEffectiveFrom());

        WaterTariff updated = waterTariffRepository.save(tariff);

        return WaterTariffMapper.toResponse(updated);
    }

    @Override
    public void deleteTariff(Long id) {

        WaterTariff tariff = waterTariffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tariff not found."));

        waterTariffRepository.delete(tariff);
    }
}