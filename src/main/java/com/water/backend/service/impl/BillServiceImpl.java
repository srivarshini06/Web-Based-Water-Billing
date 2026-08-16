package com.water.backend.service.impl;

import com.water.backend.dto.request.BillRequest;
import com.water.backend.dto.response.BillResponse;
import com.water.backend.entity.Bill;
import com.water.backend.entity.Resident;
import com.water.backend.entity.TariffTier;
import com.water.backend.entity.WaterReading;
import com.water.backend.entity.WaterTariff;
import com.water.backend.mapper.BillMapper;
import com.water.backend.repository.BillRepository;
import com.water.backend.repository.ResidentRepository;
import com.water.backend.repository.TariffTierRepository;
import com.water.backend.repository.WaterReadingRepository;
import com.water.backend.repository.WaterTariffRepository;
import com.water.backend.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final WaterReadingRepository waterReadingRepository;
    private final ResidentRepository residentRepository;
    private final WaterTariffRepository waterTariffRepository;
    private final TariffTierRepository tariffTierRepository;

    @Override
    public BillResponse generateBill(BillRequest request) {

        // Prevent duplicate bills
        if (billRepository.existsByWaterReadingId(
                request.getWaterReadingId())) {

            throw new IllegalArgumentException(
                    "Bill has already been generated for this water reading."
            );
        }

        // Get water reading
        WaterReading reading = waterReadingRepository
                .findById(request.getWaterReadingId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Water reading not found"
                        )
                );

        Resident resident = reading.getResident();

        if (resident == null) {
            throw new IllegalArgumentException(
                    "Resident is not associated with this water reading."
            );
        }

        if (reading.getConsumption() == null
                || reading.getConsumption() < 0) {

            throw new IllegalArgumentException(
                    "Invalid water consumption."
            );
        }

        /*
         * Get the active tariff for the resident's community.
         */
        if (resident.getCommunity() == null) {
            throw new IllegalArgumentException(
                    "Resident is not associated with a community."
            );
        }

        Long communityId =
                resident.getCommunity().getId();

        WaterTariff tariff =
                waterTariffRepository
                        .findByCommunityIdAndActiveTrue(communityId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "No active tariff found for this community."
                                )
                        );

        // Get tariff tiers
        List<TariffTier> tiers =
                tariffTierRepository
                        .findByTariffIdOrderByMinLitresAsc(
                                tariff.getId()
                        );

        if (tiers.isEmpty()) {
            throw new IllegalArgumentException(
                    "No tariff tiers configured for this tariff."
            );
        }

        // Calculate bill using progressive tiered pricing
        BigDecimal amount =
                calculateTieredAmount(
                        reading.getConsumption(),
                        tiers
                );

        Bill bill = Bill.builder()
                .waterReading(reading)
                .resident(resident)
                .consumption(reading.getConsumption())
                .amount(amount.doubleValue())
                .billMonth(request.getBillMonth())
                .paid(false)
                .build();

        Bill savedBill = billRepository.save(bill);

        return BillMapper.toResponse(savedBill);
    }

    /**
     * Calculates the bill progressively across tariff tiers.
     */
    private BigDecimal calculateTieredAmount(
            Double consumption,
            List<TariffTier> tiers) {

        BigDecimal total = BigDecimal.ZERO;

        double remaining = consumption;

        for (TariffTier tier : tiers) {

            if (remaining <= 0) {
                break;
            }

            double min = tier.getMinLitres();

            Double maxValue = tier.getMaxLitres();

            double litresInTier;

            if (maxValue == null) {

                // Final unlimited tier
                litresInTier = remaining;

            } else {

                double tierCapacity =
                        maxValue - min;

                litresInTier =
                        Math.min(
                                remaining,
                                tierCapacity
                        );
            }

            if (litresInTier <= 0) {
                continue;
            }

            BigDecimal rate =
                    BigDecimal.valueOf(
                            tier.getPricePerLitre()
                    );

            BigDecimal tierAmount =
                    rate.multiply(
                            BigDecimal.valueOf(litresInTier)
                    );

            total = total.add(tierAmount);

            remaining -= litresInTier;
        }

        if (remaining > 0) {
            throw new IllegalArgumentException(
                    "Consumption exceeds the configured tariff tiers."
            );
        }

        return total.setScale(
                2,
                RoundingMode.HALF_UP
        );
    }

    @Override
    public List<BillResponse> getAllBills() {

        return billRepository.findAll()
                .stream()
                .map(BillMapper::toResponse)
                .toList();
    }

    @Override
    public BillResponse getBillById(Long id) {

        Bill bill = billRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Bill not found"
                        )
                );

        return BillMapper.toResponse(bill);
    }

    @Override
    public List<BillResponse> getResidentBills(
            Long residentId) {

        Resident resident =
                residentRepository.findById(residentId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Resident not found"
                                )
                        );

        return billRepository.findByResident(resident)
                .stream()
                .map(BillMapper::toResponse)
                .toList();
    }

    @Override
    public BillResponse payBill(Long id) {

        Bill bill = billRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Bill not found"
                        )
                );

        /*
         * Get the currently authenticated user's email
         * from the JWT/Spring Security context.
         */
        String currentUserEmail =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        /*
         * Find the resident associated with
         * the currently logged-in user.
         */
        Resident resident =
                residentRepository
                        .findByEmail(currentUserEmail)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Resident not found"
                                )
                        );

        /*
         * Make sure the resident can only pay
         * their own bill.
         */
        if (!bill.getResident()
                .getId()
                .equals(resident.getId())) {

            throw new AccessDeniedException(
                    "You can only pay your own bill."
            );
        }

        if (Boolean.TRUE.equals(bill.getPaid())) {
            throw new IllegalStateException(
                    "Bill is already paid"
            );
        }

        bill.setPaid(true);
        bill.setPaidDate(LocalDate.now());

        Bill updatedBill =
                billRepository.save(bill);

        return BillMapper.toResponse(updatedBill);
    }
}