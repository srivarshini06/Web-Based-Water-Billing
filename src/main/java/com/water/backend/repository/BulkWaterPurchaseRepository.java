package com.water.backend.repository;

import com.water.backend.entity.BulkWaterPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BulkWaterPurchaseRepository extends JpaRepository<BulkWaterPurchase, Long> {
    List<BulkWaterPurchase> findByCommunityId(Long communityId);

    // ADD THIS METHOD
    List<BulkWaterPurchase> findByCommunityIdAndBillingCycleId(Long communityId, Long billingCycleId);

    List<BulkWaterPurchase> findByBillingCycleId(Long billingCycleId);
}