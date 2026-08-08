package com.water.backend.service.impl;

import com.water.backend.dto.request.TariffTierRequest;
import com.water.backend.dto.response.TariffTierResponse;
import com.water.backend.entity.TariffTier;
import com.water.backend.entity.WaterTariff;
import com.water.backend.mapper.TariffTierMapper;
import com.water.backend.repository.TariffTierRepository;
import com.water.backend.repository.WaterTariffRepository;
import com.water.backend.service.TariffTierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TariffTierServiceImpl implements TariffTierService {

    private final TariffTierRepository tariffTierRepository;
    private final WaterTariffRepository waterTariffRepository;

    @Override
    public TariffTierResponse createTier(
            Long tariffId,
            TariffTierRequest request) {

        WaterTariff tariff = waterTariffRepository.findById(tariffId)
                .orElseThrow(() ->
                        new RuntimeException("Water tariff not found."));

        validateTier(request, tariffId, null);

        TariffTier tier = TariffTierMapper.toEntity(request, tariff);

        TariffTier savedTier = tariffTierRepository.save(tier);

        return TariffTierMapper.toResponse(savedTier);
    }

    @Override
    public List<TariffTierResponse> getTiersByTariff(
            Long tariffId) {

        if (!waterTariffRepository.existsById(tariffId)) {
            throw new RuntimeException("Water tariff not found.");
        }

        return tariffTierRepository
                .findByTariffIdOrderByMinLitresAsc(tariffId)
                .stream()
                .map(TariffTierMapper::toResponse)
                .toList();
    }

    @Override
    public TariffTierResponse getTierById(Long id) {

        TariffTier tier = tariffTierRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tariff tier not found."));

        return TariffTierMapper.toResponse(tier);
    }

    @Override
    public TariffTierResponse updateTier(
            Long id,
            TariffTierRequest request) {

        TariffTier tier = tariffTierRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tariff tier not found."));

        validateTier(
                request,
                tier.getTariff().getId(),
                id
        );

        tier.setMinLitres(request.getMinLitres());
        tier.setMaxLitres(request.getMaxLitres());
        tier.setPricePerLitre(request.getPricePerLitre());

        TariffTier updatedTier =
                tariffTierRepository.save(tier);

        return TariffTierMapper.toResponse(updatedTier);
    }

    @Override
    public void deleteTier(Long id) {

        if (!tariffTierRepository.existsById(id)) {
            throw new RuntimeException("Tariff tier not found.");
        }

        tariffTierRepository.deleteById(id);
    }

    private void validateTier(
            TariffTierRequest request,
            Long tariffId,
            Long currentTierId) {

        if (request.getMaxLitres() != null
                && request.getMaxLitres()
                < request.getMinLitres()) {

            throw new RuntimeException(
                    "Maximum litres must be greater than or equal to minimum litres."
            );
        }

        List<TariffTier> existingTiers =
                tariffTierRepository
                        .findByTariffIdOrderByMinLitresAsc(tariffId);

        for (TariffTier existing : existingTiers) {

            if (currentTierId != null
                    && existing.getId().equals(currentTierId)) {
                continue;
            }

            boolean overlaps = tiersOverlap(
                    request.getMinLitres(),
                    request.getMaxLitres(),
                    existing.getMinLitres(),
                    existing.getMaxLitres()
            );

            if (overlaps) {
                throw new RuntimeException(
                        "Tariff tier overlaps with an existing tier."
                );
            }
        }
    }

    private boolean tiersOverlap(
            Double min1,
            Double max1,
            Double min2,
            Double max2) {

        double upper1 =
                max1 == null ? Double.MAX_VALUE : max1;

        double upper2 =
                max2 == null ? Double.MAX_VALUE : max2;

        return min1 <= upper2 && min2 <= upper1;
    }
}