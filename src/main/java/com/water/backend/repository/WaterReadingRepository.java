package com.water.backend.repository;

import com.water.backend.entity.WaterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterReadingRepository extends JpaRepository<WaterReading, Long> {
    // ADD THIS METHOD
    List<WaterReading> findByConsumerIdAndBillingCycleId(Long consumerId, Long billingCycleId);

    List<WaterReading> findByConsumerId(Long consumerId);

    List<WaterReading> findByBillingCycleId(Long billingCycleId);
}