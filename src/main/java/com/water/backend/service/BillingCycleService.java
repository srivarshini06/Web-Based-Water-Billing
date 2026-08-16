package com.water.backend.service;

import com.water.backend.dto.request.BillingCycleRequest;
import com.water.backend.dto.response.BillingCycleResponse;
import java.util.List;

public interface BillingCycleService {
    BillingCycleResponse createBillingCycle(BillingCycleRequest request);
    BillingCycleResponse getBillingCycleById(Long id);
    List<BillingCycleResponse> getBillingCyclesByCommunity(Long communityId);
    BillingCycleResponse finalizeBillingCycle(Long id);
    BillingCycleResponse archiveBillingCycle(Long id);
    List<BillingCycleResponse> getAllBillingCycles();
}
