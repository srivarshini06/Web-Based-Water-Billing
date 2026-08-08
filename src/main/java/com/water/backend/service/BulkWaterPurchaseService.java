package com.water.backend.service;

import com.water.backend.dto.request.BulkWaterPurchaseRequest;
import com.water.backend.dto.response.BulkWaterPurchaseResponse;

import java.util.List;

public interface BulkWaterPurchaseService {

    BulkWaterPurchaseResponse createPurchase(
            BulkWaterPurchaseRequest request);

    List<BulkWaterPurchaseResponse> getAllPurchases();

    List<BulkWaterPurchaseResponse> getPurchasesByCommunity(
            Long communityId);

    BulkWaterPurchaseResponse getPurchaseById(Long id);

    BulkWaterPurchaseResponse updatePurchase(
            Long id,
            BulkWaterPurchaseRequest request);

    void deletePurchase(Long id);
}