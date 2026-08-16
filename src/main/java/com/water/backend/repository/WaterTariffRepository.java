package com.water.backend.repository;

import com.water.backend.entity.WaterTariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface WaterTariffRepository extends JpaRepository<WaterTariff, Long> {
    // ADD THIS METHOD
    Optional<WaterTariff> findByCommunityId(Long communityId);

    List<WaterTariff> findAll();
}