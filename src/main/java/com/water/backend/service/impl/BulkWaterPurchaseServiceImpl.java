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

import java.util.List;

@Service
@RequiredArgsConstructor
public class BulkWaterPurchaseServiceImpl
        implements BulkWaterPurchaseService {

    private final BulkWaterPurchaseRepository bulkWaterPurchaseRepository;
    private final CommunityRepository communityRepository;

    @Override
    public BulkWaterPurchaseResponse createPurchase(
            BulkWaterPurchaseRequest request) {

        Community community = communityRepository
                .findById(request.getCommunityId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Community not found."
                        ));

        BulkWaterPurchase purchase =
                BulkWaterPurchaseMapper.toEntity(
                        request,
                        community
                );

        BulkWaterPurchase savedPurchase =
                bulkWaterPurchaseRepository.save(purchase);

        return BulkWaterPurchaseMapper.toResponse(savedPurchase);
    }

    @Override
    public List<BulkWaterPurchaseResponse> getAllPurchases() {

        return bulkWaterPurchaseRepository.findAll()
                .stream()
                .map(BulkWaterPurchaseMapper::toResponse)
                .toList();
    }

    @Override
    public List<BulkWaterPurchaseResponse> getPurchasesByCommunity(
            Long communityId) {

        return bulkWaterPurchaseRepository
                .findByCommunityId(communityId)
                .stream()
                .map(BulkWaterPurchaseMapper::toResponse)
                .toList();
    }

    @Override
    public BulkWaterPurchaseResponse getPurchaseById(Long id) {

        BulkWaterPurchase purchase =
                bulkWaterPurchaseRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Bulk water purchase not found."
                                ));

        return BulkWaterPurchaseMapper.toResponse(purchase);
    }

    @Override
    public BulkWaterPurchaseResponse updatePurchase(
            Long id,
            BulkWaterPurchaseRequest request) {

        BulkWaterPurchase purchase =
                bulkWaterPurchaseRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Bulk water purchase not found."
                                ));

        Community community = communityRepository
                .findById(request.getCommunityId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Community not found."
                        ));

        purchase.setCommunity(community);
        purchase.setQuantityLitres(request.getQuantityLitres());
        purchase.setTotalCost(request.getTotalCost());
        purchase.setPurchaseDate(request.getPurchaseDate());

        BulkWaterPurchase updatedPurchase =
                bulkWaterPurchaseRepository.save(purchase);

        return BulkWaterPurchaseMapper.toResponse(updatedPurchase);
    }

    @Override
    public void deletePurchase(Long id) {

        BulkWaterPurchase purchase =
                bulkWaterPurchaseRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Bulk water purchase not found."
                                ));

        bulkWaterPurchaseRepository.delete(purchase);
    }
}