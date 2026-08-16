package com.water.backend.service.impl;

import com.water.backend.dto.response.InvoiceResponse;
import com.water.backend.entity.BillingCycle;
import com.water.backend.entity.BulkWaterPurchase;
import com.water.backend.entity.Invoice;
import com.water.backend.entity.Resident;
import com.water.backend.entity.TariffTier;
import com.water.backend.entity.WaterReading;
import com.water.backend.entity.WaterTariff;
import com.water.backend.enums.BillingCycleStatus;
import com.water.backend.mapper.InvoiceMapper;
import com.water.backend.repository.BillingCycleRepository;
import com.water.backend.repository.BulkWaterPurchaseRepository;
import com.water.backend.repository.InvoiceRepository;
import com.water.backend.repository.ResidentRepository;
import com.water.backend.repository.TariffTierRepository;
import com.water.backend.repository.WaterReadingRepository;
import com.water.backend.repository.WaterTariffRepository;
import com.water.backend.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final BillingCycleRepository billingCycleRepository;
    private final ResidentRepository residentRepository;
    private final WaterReadingRepository readingRepository;
    private final BulkWaterPurchaseRepository purchaseRepository;
    private final WaterTariffRepository tariffRepository;
    private final TariffTierRepository tierRepository;

    @Override
    @Transactional
    public InvoiceResponse generateInvoice(Long cycleId, Long residentId) {

        BillingCycle cycle = billingCycleRepository.findById(cycleId)
                .orElseThrow(() ->
                        new RuntimeException("Billing cycle not found"));

        Resident resident = residentRepository.findById(residentId)
                .orElseThrow(() ->
                        new RuntimeException("Resident not found"));

        if (resident.getCommunity() == null
                || cycle.getCommunity() == null
                || !resident.getCommunity().getId()
                .equals(cycle.getCommunity().getId())) {

            throw new IllegalArgumentException(
                    "Resident does not belong to billing cycle community"
            );
        }

        if (cycle.getStatus() != BillingCycleStatus.OPEN) {
            throw new IllegalStateException(
                    "Invoices can only be generated for an OPEN billing cycle"
            );
        }

        if (invoiceRepository.existsByBillingCycleAndResident(
                cycle,
                resident)) {

            throw new IllegalStateException(
                    "Invoice already exists for this resident and billing cycle"
            );
        }

        /*
         * Get all readings for this resident that fall inside
         * the billing cycle.
         */
        List<WaterReading> readings = readingRepository.findByResident(resident)
                .stream()
                .filter(reading ->
                        reading.getReadingDate() != null
                                && !reading.getReadingDate()
                                .isBefore(cycle.getStartDate())
                                && !reading.getReadingDate()
                                .isAfter(cycle.getEndDate()))
                .toList();

        if (readings.isEmpty()) {
            throw new IllegalStateException(
                    "No water readings found in billing cycle"
            );
        }

        /*
         * Sum consumption from all readings in the cycle.
         */
        double consumption = readings.stream()
                .mapToDouble(reading ->
                        reading.getConsumption() == null
                                ? 0.0
                                : reading.getConsumption())
                .sum();

        /*
         * Find the active community tariff.
         */
        WaterTariff tariff = tariffRepository
                .findByCommunityIdAndActiveTrue(
                        cycle.getCommunity().getId()
                )
                .orElseThrow(() ->
                        new RuntimeException("Active tariff not found"));

        /*
         * Get tier configuration.
         */
        List<TariffTier> tiers =
                tierRepository.findByTariffIdOrderByMinLitresAsc(
                        tariff.getId()
                );

        /*
         * Calculate water charge using the configured
         * progressive tariff tiers.
         */
        BigDecimal waterCharge =
                calculateTieredAmount(
                        consumption,
                        tiers
                );

        /*
         * Allocate community procurement cost to this resident
         * based on the resident's share of total community
         * consumption during this billing cycle.
         */
        BigDecimal procurementCharge =
                calculateProcurementCharge(
                        cycle,
                        consumption
                );

        BigDecimal sharedAreaAllocation =
                BigDecimal.ZERO.setScale(2);

        BigDecimal adjustment =
                BigDecimal.ZERO.setScale(2);

        BigDecimal totalAmount =
                waterCharge
                        .add(procurementCharge)
                        .add(sharedAreaAllocation)
                        .add(adjustment)
                        .setScale(2, RoundingMode.HALF_UP);

        Invoice invoice = Invoice.builder()
                .billingCycle(cycle)
                .resident(resident)
                .waterConsumption(money(consumption))
                .waterCharge(waterCharge)
                .procurementCharge(procurementCharge)
                .sharedAreaAllocation(sharedAreaAllocation)
                .adjustment(adjustment)
                .totalAmount(totalAmount)
                .status("PENDING")
                .build();

        return InvoiceMapper.toResponse(
                invoiceRepository.save(invoice)
        );
    }

    /**
     * Allocates the community's bulk-water procurement cost
     * proportionally according to resident consumption.
     *
     * Example:
     *
     * Community consumption = 100,000 litres
     * Resident consumption  = 10,000 litres
     * Procurement cost      = 50,000
     *
     * Resident share = 10,000 / 100,000 = 10%
     * Procurement charge = 5,000
     */
    private BigDecimal calculateProcurementCharge(
            BillingCycle cycle,
            double residentConsumption) {

        if (residentConsumption <= 0) {
            return BigDecimal.ZERO.setScale(2);
        }

        /*
         * Get active purchases made during the billing cycle.
         */
        List<BulkWaterPurchase> purchases =
                purchaseRepository
                        .findByCommunityIdOrderByPurchaseDateDesc(
                                cycle.getCommunity().getId()
                        )
                        .stream()
                        .filter(p ->
                                Boolean.TRUE.equals(p.getActive()))
                        .filter(p ->
                                p.getPurchaseDate() != null
                                        && !p.getPurchaseDate()
                                        .isBefore(cycle.getStartDate())
                                        && !p.getPurchaseDate()
                                        .isAfter(cycle.getEndDate()))
                        .toList();

        if (purchases.isEmpty()) {
            return BigDecimal.ZERO.setScale(2);
        }

        /*
         * Calculate total procurement cost for the cycle.
         */
        BigDecimal totalProcurementCost =
                purchases.stream()
                        .map(purchase ->
                                BigDecimal.valueOf(
                                        purchase.getTotalCost() == null
                                                ? 0.0
                                                : purchase.getTotalCost()
                                ))
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        if (totalProcurementCost.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO.setScale(2);
        }

        /*
         * Get all readings belonging to residents of this
         * community during this billing cycle.
         */
        List<WaterReading> communityReadings =
                readingRepository
                        .findByResidentCommunity(
                                cycle.getCommunity()
                        )
                        .stream()
                        .filter(reading ->
                                reading.getReadingDate() != null
                                        && !reading.getReadingDate()
                                        .isBefore(cycle.getStartDate())
                                        && !reading.getReadingDate()
                                        .isAfter(cycle.getEndDate()))
                        .toList();

        double communityConsumption =
                communityReadings.stream()
                        .mapToDouble(reading ->
                                reading.getConsumption() == null
                                        ? 0.0
                                        : reading.getConsumption())
                        .sum();

        if (communityConsumption <= 0) {
            return BigDecimal.ZERO.setScale(2);
        }

        /*
         * Resident's share of community consumption.
         */
        BigDecimal residentShare =
                BigDecimal.valueOf(residentConsumption)
                        .divide(
                                BigDecimal.valueOf(communityConsumption),
                                10,
                                RoundingMode.HALF_UP
                        );

        return totalProcurementCost
                .multiply(residentShare)
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calculates progressive/tiered water charges.
     *
     * Example:
     *
     * Tier 1: 0 - 10,000     @ price A
     * Tier 2: 10,000 - 20,000 @ price B
     * Tier 3: 20,000+         @ price C
     */
    private BigDecimal calculateTieredAmount(
            double consumption,
            List<TariffTier> tiers) {

        if (tiers == null || tiers.isEmpty()) {
            throw new IllegalArgumentException(
                    "No tariff tiers configured"
            );
        }

        if (consumption < 0) {
            throw new IllegalArgumentException(
                    "Water consumption cannot be negative"
            );
        }

        double remaining = consumption;

        BigDecimal total = BigDecimal.ZERO;

        for (TariffTier tier : tiers) {

            if (remaining <= 0) {
                break;
            }

            double min = tier.getMinLitres();

            double max =
                    tier.getMaxLitres() == null
                            ? Double.POSITIVE_INFINITY
                            : tier.getMaxLitres();

            double start = Math.max(0, min);
            double end = Math.max(start, max);

            /*
             * Determine how much consumption falls inside
             * this tier.
             */
            double lowerBound =
                    Math.min(consumption, start);

            double upperBound =
                    Math.min(consumption, end);

            double litres =
                    Math.max(
                            0,
                            upperBound - lowerBound
                    );

            if (litres > 0) {

                BigDecimal tierCharge =
                        BigDecimal.valueOf(litres)
                                .multiply(
                                        BigDecimal.valueOf(
                                                tier.getPricePerLitre()
                                        )
                                );

                total = total.add(tierCharge);

                remaining -= litres;
            }
        }

        /*
         * Consumption must fit inside the configured tiers.
         */
        if (remaining > 0.000001) {
            throw new IllegalArgumentException(
                    "Consumption exceeds configured tariff tiers"
            );
        }

        return money(total);
    }

    private BigDecimal money(double value) {

        return BigDecimal
                .valueOf(value)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal money(BigDecimal value) {

        return value
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceResponse getInvoiceById(Long id) {

        return invoiceRepository
                .findById(id)
                .map(InvoiceMapper::toResponse)
                .orElseThrow(() ->
                        new RuntimeException("Invoice not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceResponse> getInvoicesByBillingCycle(
            Long cycleId) {

        BillingCycle cycle =
                billingCycleRepository.findById(cycleId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Billing cycle not found"
                                ));

        return invoiceRepository
                .findByBillingCycle(cycle)
                .stream()
                .map(InvoiceMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceResponse> getInvoicesByResident(
            Long residentId) {

        Resident resident =
                residentRepository.findById(residentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Resident not found"
                                ));

        return invoiceRepository
                .findByResident(resident)
                .stream()
                .map(InvoiceMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public InvoiceResponse markAsPaid(Long id) {

        Invoice invoice =
                invoiceRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invoice not found"
                                ));

        if ("PAID".equals(invoice.getStatus())) {
            throw new IllegalStateException(
                    "Invoice is already paid"
            );
        }

        invoice.setStatus("PAID");

        return InvoiceMapper.toResponse(
                invoiceRepository.save(invoice)
        );
    }
}