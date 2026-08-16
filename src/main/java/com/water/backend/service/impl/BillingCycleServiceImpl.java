package com.water.backend.service.impl;

import com.water.backend.dto.request.BillingCycleRequest;
import com.water.backend.dto.response.BillingCycleResponse;
import com.water.backend.entity.BillingCycle;
import com.water.backend.entity.Community;
import com.water.backend.entity.Resident;
import com.water.backend.entity.User;
import com.water.backend.enums.BillingCycleStatus;
import com.water.backend.enums.UserRole;
import com.water.backend.mapper.BillingCycleMapper;
import com.water.backend.repository.BillingCycleRepository;
import com.water.backend.repository.CommunityRepository;
import com.water.backend.repository.ResidentRepository;
import com.water.backend.service.BillingCycleService;
import com.water.backend.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingCycleServiceImpl
        implements BillingCycleService {

    private final BillingCycleRepository cycleRepository;
    private final CommunityRepository communityRepository;
    private final ResidentRepository residentRepository;
    private final InvoiceService invoiceService;

    @Override
    @Transactional
    public BillingCycleResponse createBillingCycle(
            BillingCycleRequest request) {

        if (request.getStartDate() == null
                || request.getEndDate() == null) {

            throw new IllegalArgumentException(
                    "Start date and end date are required"
            );
        }

        if (request.getStartDate()
                .isAfter(request.getEndDate())) {

            throw new IllegalArgumentException(
                    "Start date must be before end date"
            );
        }

        Community community =
                communityRepository.findById(
                        request.getCommunityId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Community not found"
                        ));

        if (cycleRepository
                .existsByCommunityAndStartDateAndEndDate(
                        community,
                        request.getStartDate(),
                        request.getEndDate()
                )) {

            throw new IllegalArgumentException(
                    "Billing cycle already exists for these dates"
            );
        }

        BillingCycle cycle =
                BillingCycle.builder()
                        .community(community)
                        .startDate(request.getStartDate())
                        .endDate(request.getEndDate())
                        .status(BillingCycleStatus.OPEN)
                        .totalAmount(0.0)
                        .totalInvoicesGenerated(0L)
                        .build();

        return BillingCycleMapper.toResponse(
                cycleRepository.save(cycle)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public BillingCycleResponse getBillingCycleById(
            Long id) {

        return cycleRepository
                .findById(id)
                .map(BillingCycleMapper::toResponse)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Billing cycle not found"
                        ));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BillingCycleResponse>
    getBillingCyclesByCommunity(Long communityId) {

        if (!communityRepository.existsById(communityId)) {

            throw new RuntimeException(
                    "Community not found"
            );
        }

        return cycleRepository
                .findByCommunityId(communityId)
                .stream()
                .map(BillingCycleMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public BillingCycleResponse finalizeBillingCycle(
            Long id) {

        BillingCycle cycle =
                cycleRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Billing cycle not found"
                                ));

        if (cycle.getStatus()
                != BillingCycleStatus.OPEN) {

            throw new IllegalStateException(
                    "Only OPEN cycles can be finalized"
            );
        }

        List<Resident> residents =
                residentRepository.findByCommunityId(
                        cycle.getCommunity().getId()
                );

        double totalAmount = 0.0;
        long invoiceCount = 0L;

        for (Resident resident : residents) {

            InvoiceGenerationResult result =
                    generateIfUsable(
                            cycle,
                            resident
                    );

            if (result.generated()) {

                totalAmount += result.amount();
                invoiceCount++;
            }
        }

        cycle.setTotalAmount(totalAmount);
        cycle.setTotalInvoicesGenerated(invoiceCount);
        cycle.setStatus(BillingCycleStatus.FINALIZED);
        cycle.setFinalizedAt(LocalDateTime.now());

        return BillingCycleMapper.toResponse(
                cycleRepository.save(cycle)
        );
    }

    /**
     * A resident without a reading in the billing cycle
     * is simply not invoiced.
     *
     * Other invoice-generation errors are allowed to
     * propagate because they indicate a real configuration
     * or business-rule problem.
     */
    private InvoiceGenerationResult generateIfUsable(
            BillingCycle cycle,
            Resident resident) {

        try {

            var invoice =
                    invoiceService.generateInvoice(
                            cycle.getId(),
                            resident.getId()
                    );

            return new InvoiceGenerationResult(
                    true,
                    invoice.getTotalAmount().doubleValue()
            );

        } catch (IllegalStateException e) {

            if ("No water readings found in billing cycle"
                    .equals(e.getMessage())) {

                return new InvoiceGenerationResult(
                        false,
                        0.0
                );
            }

            throw e;
        }
    }

    private record InvoiceGenerationResult(
            boolean generated,
            double amount
    ) {
    }

    @Override
    @Transactional
    public BillingCycleResponse archiveBillingCycle(
            Long id) {

        BillingCycle cycle =
                cycleRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Billing cycle not found"
                                ));

        if (cycle.getStatus()
                != BillingCycleStatus.FINALIZED) {

            throw new IllegalStateException(
                    "Only FINALIZED cycles can be archived"
            );
        }

        cycle.setStatus(
                BillingCycleStatus.ARCHIVED
        );

        cycle.setArchivedAt(
                LocalDateTime.now()
        );

        return BillingCycleMapper.toResponse(
                cycleRepository.save(cycle)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<BillingCycleResponse>
    getAllBillingCycles() {

        return cycleRepository
                .findAll()
                .stream()
                .map(BillingCycleMapper::toResponse)
                .toList();
    }

    /**
     * Finalize billing cycle: generate invoices, distribute costs, send notifications
     */
    @Override
    @Transactional
    public void finalizeBillingCycle(Long billingCycleId) {
        BillingCycle cycle = billingCycleRepository.findById(billingCycleId)
                .orElseThrow(() -> new ResourceNotFoundException("Cycle not found: " + billingCycleId));

        log.info("Finalizing billing cycle: {}", billingCycleId);

        // Step 1: Distribute costs
        waterDistributionService.distributeConsumptionCosts(billingCycleId);

        // Step 2: Generate invoices for all consumers
        invoiceService.generateInvoicesForBillingCycle(billingCycleId);

        // Step 3: Update cycle status
        cycle.setStatus(BillingCycleStatus.FINALIZED);
        cycle.setFinalizedAt(new Date());
        billingCycleRepository.save(cycle);

        // Step 4: Send notifications to admins
        List<User> admins = userRepository.findByRoleAndCommunityId(UserRole.COMMUNITY_ADMIN, cycle.getCommunityId());
        for (User admin : admins) {
            emailService.sendBillingCycleClosureNotification(admin.getEmail(), cycle.getCommunityId().toString());
        }

        log.info("Billing cycle finalized: {}", billingCycleId);
    }
}