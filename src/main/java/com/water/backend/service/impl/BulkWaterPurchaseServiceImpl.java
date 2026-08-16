package com.water.backend.service.impl;

import com.water.backend.dto.request.BulkWaterPurchaseRequest;
import com.water.backend.dto.response.BulkWaterPurchaseResponse;
import com.water.backend.entity.BulkWaterPurchase;
import com.water.backend.entity.Community;
import com.water.backend.mapper.BulkWaterPurchaseMapper;
import com.water.backend.repository.BulkWaterPurchaseRepository;
import com.water.backend.repository.CommunityRepository;
import com.water.backend.service.BulkWaterPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BulkWaterPurchaseServiceImpl
        implements BulkWaterPurchaseService {

    private final BulkWaterPurchaseRepository bulkWaterPurchaseRepository;
    private final CommunityRepository communityRepository;

    @Override
    @Transactional
    public BulkWaterPurchaseResponse createPurchase(
            BulkWaterPurchaseRequest request) {

        validatePurchaseRequest(request);

        Community community =
                communityRepository.findById(
                        request.getCommunityId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Community not found."
                        )
                );

        double totalCost =
                calculateTotalCost(
                        request.getQuantityLitres(),
                        request.getPricePerLitre()
                );

        BulkWaterPurchase purchase =
                BulkWaterPurchaseMapper.toEntity(
                        request,
                        community,
                        totalCost
                );

        BulkWaterPurchase saved =
                bulkWaterPurchaseRepository.save(purchase);

        return BulkWaterPurchaseMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BulkWaterPurchaseResponse> getAllPurchases() {

        return bulkWaterPurchaseRepository
                .findAll()
                .stream()
                .map(BulkWaterPurchaseMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BulkWaterPurchaseResponse> getPurchasesByCommunity(
            Long communityId) {

        if (!communityRepository.existsById(communityId)) {
            throw new RuntimeException(
                    "Community not found."
            );
        }

        return bulkWaterPurchaseRepository
                .findByCommunityIdOrderByPurchaseDateDesc(
                        communityId
                )
                .stream()
                .map(BulkWaterPurchaseMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BulkWaterPurchaseResponse getPurchaseById(Long id) {

        BulkWaterPurchase purchase =
                bulkWaterPurchaseRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Bulk water purchase not found."
                                )
                        );

        return BulkWaterPurchaseMapper.toResponse(purchase);
    }

    @Override
    @Transactional
    public BulkWaterPurchaseResponse updatePurchase(
            Long id,
            BulkWaterPurchaseRequest request) {

        validatePurchaseRequest(request);

        BulkWaterPurchase purchase =
                bulkWaterPurchaseRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Bulk water purchase not found."
                                )
                        );

        Community community =
                communityRepository.findById(
                        request.getCommunityId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Community not found."
                        )
                );

        double totalCost =
                calculateTotalCost(
                        request.getQuantityLitres(),
                        request.getPricePerLitre()
                );

        purchase.setCommunity(community);
        purchase.setPurchaseDate(request.getPurchaseDate());
        purchase.setQuantityLitres(request.getQuantityLitres());
        purchase.setPricePerLitre(request.getPricePerLitre());
        purchase.setTotalCost(totalCost);
        purchase.setSupplierName(request.getSupplierName());
        purchase.setInvoiceNumber(request.getInvoiceNumber());

        BulkWaterPurchase updated =
                bulkWaterPurchaseRepository.save(purchase);

        return BulkWaterPurchaseMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deletePurchase(Long id) {

        if (!bulkWaterPurchaseRepository.existsById(id)) {
            throw new RuntimeException(
                    "Bulk water purchase not found."
            );
        }

        bulkWaterPurchaseRepository.deleteById(id);
    }

    private double calculateTotalCost(
            Double quantityLitres,
            Double pricePerLitre) {

        return quantityLitres * pricePerLitre;
    }

    private void validatePurchaseRequest(
            BulkWaterPurchaseRequest request) {

        if (request == null) {
            throw new IllegalArgumentException(
                    "Purchase request cannot be null."
            );
        }

        if (request.getCommunityId() == null) {
            throw new IllegalArgumentException(
                    "Community ID is required."
            );
        }

        if (request.getPurchaseDate() == null) {
            throw new IllegalArgumentException(
                    "Purchase date is required."
            );
        }

        if (request.getQuantityLitres() == null
                || request.getQuantityLitres() <= 0) {

            throw new IllegalArgumentException(
                    "Quantity of water must be greater than zero."
            );
        }

        if (request.getPricePerLitre() == null
                || request.getPricePerLitre() <= 0) {

            throw new IllegalArgumentException(
                    "Price per litre must be greater than zero."
            );
        }
    }
}