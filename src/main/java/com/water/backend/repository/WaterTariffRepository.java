package com.water.backend.repository;

import com.water.backend.entity.WaterTariff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WaterTariffRepository extends JpaRepository<WaterTariff, Long> {

    Optional<WaterTariff> findByCommunityIdAndActiveTrue(Long communityId);

    List<WaterTariff> findByCommunityId(Long communityId);
}