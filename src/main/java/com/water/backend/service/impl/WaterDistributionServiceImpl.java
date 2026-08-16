package com.water.backend.service.impl;

import com.water.backend.dto.response.ConsumptionDistributionResponse;
import com.water.backend.entity.*;
import com.water.backend.exception.ResourceNotFoundException;
import com.water.backend.repository.*;
import com.water.backend.service.WaterDistributionService;
import com.water.backend.service.WaterTariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WaterDistributionServiceImpl implements WaterDistributionService {

    private final BillingCycleRepository billingCycleRepository;
    private final ConsumerRepository consumerRepository;
    private final WaterReadingRepository waterReadingRepository;
    private final BulkWaterPurchaseRepository bulkWaterPurchaseRepository;
    private final WaterMeterRepository waterMeterRepository;
    private final WaterTariffService waterTariffService;

    /**
     * CORE ALGORITHM: Distribute apartment water cost across households proportionally
     *
     * Steps:
     * 1. Get all meters/consumers in community for this billing cycle
     * 2. Calculate total consumption for cycle
     * 3. Get total water purchased (cost) for cycle
     * 4. Calculate per-household tariff charge
     * 5. Calculate shared-area allocation per household
     * 6. Total charge = tariff charge + shared allocation
     */
    @Override
    @Transactional
    public ConsumptionDistributionResponse distributeConsumptionCosts(Long billingCycleId) {
        BillingCycle billingCycle = billingCycleRepository.findById(billingCycleId)
                .orElseThrow(() -> new ResourceNotFoundException("Billing cycle not found: " + billingCycleId));

        Long communityId = billingCycle.getCommunityId();

        // Fetch all consumers (households) in community
        List<Consumer> consumers = consumerRepository.findByCommunityIdCommunityId(communityId);
        log.info("Processing distribution for {} consumers in community: {}", consumers.size(), communityId);

        // Get all water readings for this cycle
        Map<Long, BigDecimal> consumerConsumption = getConsumerConsumptionMap(consumers, billingCycle);

        // Get total purchased volume and cost for cycle
        BigDecimal totalPurchasedVolume = getTotalPurchasedVolume(communityId, billingCycle);
        BigDecimal totalPurchasedCost = getTotalPurchasedCost(communityId, billingCycle);

        // Separate metered vs non-metered households
        Map<Long, BigDecimal> meteredConsumption = new HashMap<>();
        List<Consumer> nonMeteredConsumers = new ArrayList<>();

        for (Consumer consumer : consumers) {
            Long consumerId = consumer.getId();
            if (consumerConsumption.containsKey(consumerId)) {
                meteredConsumption.put(consumerId, consumerConsumption.get(consumerId));
            } else {
                nonMeteredConsumers.add(consumer);
            }
        }

        // Calculate charges
        Map<Long, DistributionBreakdown> consumerCharges = new HashMap<>();

        BigDecimal totalMeteredConsumption = meteredConsumption.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        log.info("Total metered consumption: {} kL, Total cost: Rs {}", totalMeteredConsumption, totalPurchasedCost);

        // === STEP 1: Tariff-based charges for metered consumers ===
        WaterTariff tariff = getTariffForCommunity(communityId);
        for (Map.Entry<Long, BigDecimal> entry : meteredConsumption.entrySet()) {
            Long consumerId = entry.getKey();
            BigDecimal consumption = entry.getValue();

            // Calculate tariff charge (tier-based)
            BigDecimal tariffCharge = waterTariffService.calculateChargeForConsumption(tariff.getId(), consumption);

            consumerCharges.put(consumerId, new DistributionBreakdown(
                    consumption,
                    tariffCharge,
                    BigDecimal.ZERO,  // shared allocation calculated next
                    consumption,
                    tariffCharge
            ));
        }

        // === STEP 2: Shared-area cost allocation ===
        // Allocate purchased cost proportionally by consumption (for metered households)
        // Non-metered households get equal flat share

        BigDecimal sharedCostPercentageMetered = BigDecimal.ZERO;
        if (totalMeteredConsumption.compareTo(BigDecimal.ZERO) > 0) {
            sharedCostPercentageMetered = totalPurchasedCost
                    .multiply(BigDecimal.valueOf(0.20))  // 20% for shared areas
                    .divide(totalMeteredConsumption, 4, RoundingMode.HALF_UP);
        }

        // Allocate to metered households proportionally
        for (Map.Entry<Long, BigDecimal> entry : meteredConsumption.entrySet()) {
            BigDecimal consumption = entry.getValue();
            BigDecimal sharedAllocation = consumption.multiply(sharedCostPercentageMetered);

            DistributionBreakdown existing = consumerCharges.get(entry.getKey());
            existing.setSharedAreaAllocation(sharedAllocation);
            existing.setTotalCharge(existing.getTariffCharge().add(sharedAllocation));
        }

        // Allocate equally to non-metered households
        BigDecimal flatShareForNonMetered = BigDecimal.ZERO;
        if (!nonMeteredConsumers.isEmpty()) {
            flatShareForNonMetered = totalPurchasedCost.divide(
                    BigDecimal.valueOf(nonMeteredConsumers.size()), 2, RoundingMode.HALF_UP);
        }

        for (Consumer consumer : nonMeteredConsumers) {
            consumerCharges.put(consumer.getId(), new DistributionBreakdown(
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    flatShareForNonMetered,
                    BigDecimal.ZERO,
                    flatShareForNonMetered
            ));
        }

        // === STEP 3: Build response ===
        BigDecimal totalDistributed = consumerCharges.values().stream()
                .map(DistributionBreakdown::getTotalCharge)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        log.info("Distribution complete. Total distributed: Rs {}", totalDistributed);

        return ConsumptionDistributionResponse.builder()
                .billingCycleId(billingCycleId)
                .communityId(communityId)
                .totalPurchasedVolume(totalPurchasedVolume)
                .totalPurchasedCost(totalPurchasedCost)
                .totalMeteredConsumption(totalMeteredConsumption)
                .totalDistributedCost(totalDistributed)
                .nonMeteredHouseholdsCount(nonMeteredConsumers.size())
                .consumerBreakdown(consumerCharges)
                .distributedAt(new Date())
                .build();
    }

    /**
     * Helper: Get consumption per consumer for a billing cycle
     */
    private Map<Long, BigDecimal> getConsumerConsumptionMap(List<Consumer> consumers, BillingCycle cycle) {
        Map<Long, BigDecimal> consumptionMap = new HashMap<>();

        for (Consumer consumer : consumers) {
            List<WaterReading> readings = waterReadingRepository
                    .findByConsumerIdAndBillingCycleId(consumer.getId(), cycle.getId());

            if (!readings.isEmpty()) {
                // Assume latest reading is final for cycle
                BigDecimal consumption = readings.stream()
                        .map(WaterReading::getReading)
                        .max(BigDecimal::compareTo)
                        .orElse(BigDecimal.ZERO);
                consumptionMap.put(consumer.getId(), consumption);
            }
        }

        return consumptionMap;
    }

    /**
     * Helper: Get total purchased volume for community in cycle
     */
    private BigDecimal getTotalPurchasedVolume(Long communityId, BillingCycle cycle) {
        List<BulkWaterPurchase> purchases = bulkWaterPurchaseRepository
                .findByCommunityIdAndBillingCycleId(communityId, cycle.getId());

        return purchases.stream()
                .map(BulkWaterPurchase::getTotalVolume)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Helper: Get total purchased cost (volume * unit cost)
     */
    private BigDecimal getTotalPurchasedCost(Long communityId, BillingCycle cycle) {
        List<BulkWaterPurchase> purchases = bulkWaterPurchaseRepository
                .findByCommunityIdAndBillingCycleId(communityId, cycle.getId());

        return purchases.stream()
                .map(p -> p.getTotalVolume().multiply(p.getUnitCost()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Helper: Fetch active tariff for community
     */
    // Add this repository as a dependency
    private final WaterTariffRepository waterTariffRepository;

    /**
     * Helper: Fetch active tariff for community
     */
    private WaterTariff getTariffForCommunity(Long communityId) {
        return waterTariffRepository.findByCommunityId(communityId)
                .orElseThrow(() -> new RuntimeException("No tariff found for community: " + communityId));
    }

    /**
     * Inner class: Breakdown of charges per consumer
     */
    public static class DistributionBreakdown {
        public BigDecimal consumption;
        public BigDecimal tariffCharge;
        public BigDecimal sharedAreaAllocation;
        public BigDecimal estimatedUsage;
        public BigDecimal totalCharge;

        public DistributionBreakdown(BigDecimal consumption, BigDecimal tariffCharge,
                                     BigDecimal sharedAreaAllocation, BigDecimal estimatedUsage,
                                     BigDecimal totalCharge) {
            this.consumption = consumption;
            this.tariffCharge = tariffCharge;
            this.sharedAreaAllocation = sharedAreaAllocation;
            this.estimatedUsage = estimatedUsage;
            this.totalCharge = totalCharge;
        }

        // Getters/Setters
        public BigDecimal getConsumption() { return consumption; }
        public void setConsumption(BigDecimal consumption) { this.consumption = consumption; }

        public BigDecimal getTariffCharge() { return tariffCharge; }
        public void setTariffCharge(BigDecimal tariffCharge) { this.tariffCharge = tariffCharge; }

        public BigDecimal getSharedAreaAllocation() { return sharedAreaAllocation; }
        public void setSharedAreaAllocation(BigDecimal sharedAreaAllocation) {
            this.sharedAreaAllocation = sharedAreaAllocation;
        }

        public BigDecimal getEstimatedUsage() { return estimatedUsage; }
        public void setEstimatedUsage(BigDecimal estimatedUsage) { this.estimatedUsage = estimatedUsage; }

        public BigDecimal getTotalCharge() { return totalCharge; }
        public void setTotalCharge(BigDecimal totalCharge) { this.totalCharge = totalCharge; }
    }

    @Override
    @Transactional(readOnly = true)
    public ConsumptionDistributionResponse getDistributionForBillingCycle(Long billingCycleId) {
        // Retrieve cached distribution from DB (if stored)
        // For now, re-calculate
        return distributeConsumptionCosts(billingCycleId);
    }
}