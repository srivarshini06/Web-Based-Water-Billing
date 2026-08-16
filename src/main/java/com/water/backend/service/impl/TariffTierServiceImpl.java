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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TariffTierServiceImpl
        implements TariffTierService {

    private final TariffTierRepository tariffTierRepository;
    private final WaterTariffRepository waterTariffRepository;

    @Override
    @Transactional
    public TariffTierResponse createTier(
            Long tariffId,
            TariffTierRequest request) {

        WaterTariff tariff =
                waterTariffRepository.findById(tariffId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Water tariff not found."
                                ));

        validateTier(
                request,
                tariffId,
                null
        );

        TariffTier tier =
                TariffTierMapper.toEntity(
                        request,
                        tariff
                );

        TariffTier savedTier =
                tariffTierRepository.save(tier);

        return TariffTierMapper.toResponse(
                savedTier
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<TariffTierResponse> getTiersByTariff(
            Long tariffId) {

        if (!waterTariffRepository.existsById(tariffId)) {

            throw new RuntimeException(
                    "Water tariff not found."
            );
        }

        return tariffTierRepository
                .findByTariffIdOrderByMinLitresAsc(tariffId)
                .stream()
                .map(TariffTierMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TariffTierResponse getTierById(Long id) {

        TariffTier tier =
                tariffTierRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Tariff tier not found."
                                ));

        return TariffTierMapper.toResponse(tier);
    }

    @Override
    @Transactional
    public TariffTierResponse updateTier(
            Long id,
            TariffTierRequest request) {

        TariffTier tier =
                tariffTierRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Tariff tier not found."
                                ));

        validateTier(
                request,
                tier.getTariff().getId(),
                id
        );

        tier.setMinLitres(
                request.getMinLitres()
        );

        tier.setMaxLitres(
                request.getMaxLitres()
        );

        tier.setPricePerLitre(
                request.getPricePerLitre()
        );

        return TariffTierMapper.toResponse(
                tariffTierRepository.save(tier)
        );
    }

    @Override
    @Transactional
    public void deleteTier(Long id) {

        if (!tariffTierRepository.existsById(id)) {

            throw new RuntimeException(
                    "Tariff tier not found."
            );
        }

        tariffTierRepository.deleteById(id);
    }

    private void validateTier(
            TariffTierRequest request,
            Long tariffId,
            Long currentTierId) {

        Double min = request.getMinLitres();
        Double max = request.getMaxLitres();
        Double price = request.getPricePerLitre();

        if (min == null) {
            throw new IllegalArgumentException(
                    "Minimum litres cannot be null."
            );
        }

        if (min < 0) {
            throw new IllegalArgumentException(
                    "Minimum litres cannot be negative."
            );
        }

        if (price == null || price <= 0) {
            throw new IllegalArgumentException(
                    "Price per litre must be greater than zero."
            );
        }

        if (max != null && max <= min) {
            throw new IllegalArgumentException(
                    "Maximum litres must be greater than minimum litres."
            );
        }

        List<TariffTier> existingTiers =
                tariffTierRepository
                        .findByTariffIdOrderByMinLitresAsc(
                                tariffId
                        );

        for (TariffTier existing : existingTiers) {

            if (currentTierId != null
                    && existing.getId().equals(currentTierId)) {

                continue;
            }

            if (tiersOverlap(
                    min,
                    max,
                    existing.getMinLitres(),
                    existing.getMaxLitres()
            )) {

                throw new IllegalArgumentException(
                        "Tariff tier overlaps with an existing tier."
                );
            }
        }

        /*
         * Check whether inserting this tier creates a gap
         * with an existing bounded tier.
         *
         * We allow the first tier to start at any value because
         * the database may already contain legacy configuration.
         */
        List<TariffTier> remainingTiers =
                existingTiers.stream()
                        .filter(tier ->
                                currentTierId == null
                                        || !tier.getId()
                                        .equals(currentTierId))
                        .toList();

        List<TierRange> ranges =
                new java.util.ArrayList<>();

        for (TariffTier tier : remainingTiers) {

            ranges.add(
                    new TierRange(
                            tier.getMinLitres(),
                            tier.getMaxLitres()
                    )
            );
        }

        ranges.add(
                new TierRange(min, max)
        );

        ranges.sort(
                java.util.Comparator.comparing(
                        TierRange::min
                )
        );

        /*
         * Once a tier starts, the next tier must begin exactly
         * where the previous tier ends.
         *
         * Unlimited tier must be the last tier.
         */
        for (int i = 0; i < ranges.size() - 1; i++) {

            TierRange current = ranges.get(i);
            TierRange next = ranges.get(i + 1);

            if (current.max() == null) {

                throw new IllegalArgumentException(
                        "An unlimited tariff tier must be the final tier."
                );
            }

            if (Double.compare(
                    current.max(),
                    next.min()
            ) != 0) {

                throw new IllegalArgumentException(
                        "Tariff tiers must be continuous without gaps."
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
                max1 == null
                        ? Double.MAX_VALUE
                        : max1;

        double upper2 =
                max2 == null
                        ? Double.MAX_VALUE
                        : max2;

        return min1 < upper2
                && min2 < upper1;
    }

    private record TierRange(
            Double min,
            Double max
    ) {
    }
}