package com.water.backend.repository;

import com.water.backend.entity.TariffTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TariffTierRepository extends JpaRepository<TariffTier, Long> {
    List<TariffTier> findByTariffId(Long tariffId);

    // ADD THIS METHOD
    List<TariffTier> findByTariffIdOrderByMinVolumeAsc(Long tariffId);

    void deleteByTariffId(Long tariffId);
}