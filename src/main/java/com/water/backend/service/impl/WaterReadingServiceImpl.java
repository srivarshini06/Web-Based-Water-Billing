package com.water.backend.service.impl;

import com.water.backend.dto.request.WaterReadingRequest;
import com.water.backend.dto.response.WaterReadingResponse;
import com.water.backend.entity.Resident;
import com.water.backend.entity.TariffTier;
import com.water.backend.entity.WaterReading;
import com.water.backend.entity.WaterTariff;
import com.water.backend.mapper.WaterReadingMapper;
import com.water.backend.repository.ResidentRepository;
import com.water.backend.repository.TariffTierRepository;
import com.water.backend.repository.WaterReadingRepository;
import com.water.backend.repository.WaterTariffRepository;
import com.water.backend.service.WaterReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WaterReadingServiceImpl implements WaterReadingService {

    private final WaterReadingRepository waterReadingRepository;
    private final ResidentRepository residentRepository;
    private final WaterTariffRepository waterTariffRepository;
    private final TariffTierRepository tariffTierRepository;

    @Override
    @Transactional
    public WaterReadingResponse addReading(
            WaterReadingRequest request) {

        /*
         * --------------------------------------------------
         * 1. FIND RESIDENT
         * --------------------------------------------------
         */

        Resident resident =
                residentRepository.findById(
                        request.getResidentId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Resident not found."
                        )
                );

        if (resident.getCommunity() == null) {
            throw new RuntimeException(
                    "Resident is not assigned to a community."
            );
        }

        /*
         * --------------------------------------------------
         * 2. GET PREVIOUS METER READING
         * --------------------------------------------------
         */

        Double previousReading =
                waterReadingRepository
                        .findTopByResidentOrderByReadingDateDesc(
                                resident
                        )
                        .map(WaterReading::getCurrentReading)
                        .orElse(0.0);

        /*
         * --------------------------------------------------
         * 3. GET CURRENT READING
         * --------------------------------------------------
         */

        Double currentReading =
                request.getCurrentReading();

        if (currentReading == null) {
            throw new RuntimeException(
                    "Current reading is required."
            );
        }

        if (currentReading < 0) {
            throw new RuntimeException(
                    "Current reading cannot be negative."
            );
        }

        /*
         * Meter reading cannot decrease.
         */

        if (currentReading < previousReading) {
            throw new RuntimeException(
                    "Current reading cannot be less than previous reading."
            );
        }

        /*
         * --------------------------------------------------
         * 4. CALCULATE CONSUMPTION
         * --------------------------------------------------
         */

        double consumption =
                currentReading - previousReading;

        /*
         * --------------------------------------------------
         * 5. FIND ACTIVE TARIFF
         * --------------------------------------------------
         */

        WaterTariff tariff =
                waterTariffRepository
                        .findByCommunityIdAndActiveTrue(
                                resident.getCommunity().getId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Active tariff not found."
                                )
                        );

        /*
         * --------------------------------------------------
         * 6. GET TARIFF TIERS
         * --------------------------------------------------
         */

        List<TariffTier> tiers =
                tariffTierRepository
                        .findByTariffIdOrderByMinLitresAsc(
                                tariff.getId()
                        );

        if (tiers.isEmpty()) {
            throw new RuntimeException(
                    "No tariff tiers configured."
            );
        }

        /*
         * --------------------------------------------------
         * 7. VALIDATE TIER STRUCTURE
         * --------------------------------------------------
         */

        validateTierCoverage(tiers);

        /*
         * --------------------------------------------------
         * 8. CALCULATE TIERED BILL
         * --------------------------------------------------
         *
         * Example:
         *
         * Tier 1: 0 - 10000     -> ₹0.50/L
         * Tier 2: 10000 - 20000  -> ₹0.75/L
         * Tier 3: 20000+         -> ₹1.00/L
         *
         * Consumption = 15000 L
         *
         * First 10000 = 10000 × 0.50 = ₹5000
         * Next 5000   = 5000 × 0.75  = ₹3750
         *
         * Total = ₹8750
         */

        double amount =
                calculateTieredAmount(
                        consumption,
                        tiers
                );

        /*
         * --------------------------------------------------
         * 9. EFFECTIVE TARIFF PRICE
         * --------------------------------------------------
         *
         * WaterReading currently has only one
         * tariffPerLitre field.
         *
         * Since billing can contain multiple rates,
         * we store the first tier's rate as the
         * effective/base rate for backward compatibility.
         */

        double effectivePrice =
                tiers.get(0).getPricePerLitre();

        /*
         * --------------------------------------------------
         * 10. CREATE WATER READING
         * --------------------------------------------------
         */

        WaterReading reading =
                WaterReading.builder()
                        .resident(resident)
                        .readingDate(
                                request.getReadingDate()
                        )
                        .previousReading(
                                previousReading
                        )
                        .currentReading(
                                currentReading
                        )
                        .consumption(
                                consumption
                        )
                        .tariffPerLitre(
                                effectivePrice
                        )
                        .amount(
                                amount
                        )
                        .build();

        /*
         * --------------------------------------------------
         * 11. SAVE
         * --------------------------------------------------
         */

        WaterReading saved =
                waterReadingRepository.save(
                        reading
                );

        /*
         * --------------------------------------------------
         * 12. RETURN RESPONSE
         * --------------------------------------------------
         */

        return WaterReadingMapper.toResponse(
                saved
        );
    }

    /*
     * ==================================================
     * TIERED BILLING ENGINE
     * ==================================================
     */

    private double calculateTieredAmount(
            double consumption,
            List<TariffTier> tiers) {

        if (consumption <= 0) {
            return 0.0;
        }

        double totalAmount = 0.0;

        for (TariffTier tier : tiers) {

            double min =
                    tier.getMinLitres();

            double max =
                    tier.getMaxLitres() == null
                            ? Double.MAX_VALUE
                            : tier.getMaxLitres();

            /*
             * Consumption has not reached this tier.
             */

            if (consumption <= min) {
                continue;
            }

            /*
             * Calculate litres falling inside
             * this particular tier.
             */

            double litresInTier =
                    Math.min(
                            consumption,
                            max
                    ) - min;

            if (litresInTier > 0) {

                totalAmount +=
                        litresInTier
                                * tier.getPricePerLitre();
            }

            /*
             * Consumption has been completely
             * processed.
             */

            if (consumption <= max) {
                break;
            }
        }

        /*
         * Make sure every litre is covered
         * by a configured tariff tier.
         */

        double coveredLitres =
                calculateCoveredLitres(
                        consumption,
                        tiers
                );

        if (coveredLitres < consumption) {

            throw new RuntimeException(
                    "Tariff tiers do not cover the complete consumption."
            );
        }

        return totalAmount;
    }

    /*
     * ==================================================
     * CALCULATE COVERED LITRES
     * ==================================================
     */

    private double calculateCoveredLitres(
            double consumption,
            List<TariffTier> tiers) {

        double covered = 0.0;

        for (TariffTier tier : tiers) {

            double min =
                    tier.getMinLitres();

            double max =
                    tier.getMaxLitres() == null
                            ? Double.MAX_VALUE
                            : tier.getMaxLitres();

            if (consumption <= min) {
                continue;
            }

            double upper =
                    Math.min(
                            consumption,
                            max
                    );

            if (upper > min) {
                covered += upper - min;
            }

            if (consumption <= max) {
                break;
            }
        }

        return covered;
    }

    /*
     * ==================================================
     * VALIDATE TIER CONFIGURATION
     * ==================================================
     */

    private void validateTierCoverage(
            List<TariffTier> tiers) {

        if (tiers == null || tiers.isEmpty()) {
            throw new RuntimeException(
                    "No tariff tiers configured."
            );
        }

        /*
         * First tier must start from 0.
         */

        TariffTier firstTier =
                tiers.get(0);

        if (firstTier.getMinLitres() == null) {
            throw new RuntimeException(
                    "Tariff tier minimum litres cannot be null."
            );
        }

        if (firstTier.getMinLitres() != 0.0) {
            throw new RuntimeException(
                    "The first tariff tier must start from 0 litres."
            );
        }

        /*
         * Validate each tier.
         */

        for (int i = 0; i < tiers.size(); i++) {

            TariffTier current =
                    tiers.get(i);

            if (current.getMinLitres() == null) {
                throw new RuntimeException(
                        "Tariff tier minimum litres cannot be null."
                );
            }

            if (current.getPricePerLitre() == null) {
                throw new RuntimeException(
                        "Tariff tier price cannot be null."
                );
            }

            if (current.getMinLitres() < 0) {
                throw new RuntimeException(
                        "Tariff tier minimum litres cannot be negative."
                );
            }

            if (current.getPricePerLitre() < 0) {
                throw new RuntimeException(
                        "Tariff tier price cannot be negative."
                );
            }

            /*
             * max must be greater than min.
             */

            if (current.getMaxLitres() != null
                    && current.getMaxLitres()
                    <= current.getMinLitres()) {

                throw new RuntimeException(
                        "Tariff tier maximum litres must be greater than minimum litres."
                );
            }

            /*
             * Check the next tier.
             */

            if (i < tiers.size() - 1) {

                TariffTier next =
                        tiers.get(i + 1);

                if (current.getMaxLitres() == null) {

                    throw new RuntimeException(
                            "An unlimited tariff tier must be the last tier."
                    );
                }

                /*
                 * There must not be a gap.
                 *
                 * Example:
                 *
                 * 0 - 10000
                 * 10000 - 20000
                 *
                 * Valid.
                 *
                 * 0 - 10000
                 * 15000 - 20000
                 *
                 * Invalid.
                 */

                if (!current.getMaxLitres()
                        .equals(next.getMinLitres())) {

                    throw new RuntimeException(
                            "Tariff tiers contain a gap. " +
                                    "Tier ending at "
                                    + current.getMaxLitres()
                                    + " must be followed by a tier starting at "
                                    + next.getMinLitres()
                                    + "."
                    );
                }
            }
        }

        /*
         * The final tier must be unlimited.
         *
         * Otherwise consumption above the final
         * maximum would have no price.
         */

        TariffTier lastTier =
                tiers.get(tiers.size() - 1);

        if (lastTier.getMaxLitres() != null) {

            throw new RuntimeException(
                    "The final tariff tier must have no maximum limit."
            );
        }
    }

    /*
     * ==================================================
     * GET ALL READINGS
     * ==================================================
     */

    @Override
    @Transactional(readOnly = true)
    public List<WaterReadingResponse> getAllReadings() {

        return waterReadingRepository
                .findAll()
                .stream()
                .map(WaterReadingMapper::toResponse)
                .toList();
    }

    /*
     * ==================================================
     * GET RESIDENT READINGS
     * ==================================================
     */

    @Override
    @Transactional(readOnly = true)
    public List<WaterReadingResponse> getResidentReadings(
            Long residentId) {

        Resident resident =
                residentRepository.findById(
                        residentId
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Resident not found."
                        )
                );

        return waterReadingRepository
                .findByResident(resident)
                .stream()
                .map(WaterReadingMapper::toResponse)
                .toList();
    }
}