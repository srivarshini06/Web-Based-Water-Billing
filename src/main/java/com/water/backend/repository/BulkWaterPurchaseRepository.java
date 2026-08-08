package com.water.backend.repository;

import com.water.backend.entity.BulkWaterPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BulkWaterPurchaseRepository
        extends JpaRepository<BulkWaterPurchase, Long> {

    List<BulkWaterPurchase> findByCommunityId(Long communityId);

    List<BulkWaterPurchase> findByCommunityIdAndActiveTrue(Long communityId);
}