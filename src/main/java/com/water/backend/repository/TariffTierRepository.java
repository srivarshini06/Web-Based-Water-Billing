package com.water.backend.repository;

import com.water.backend.entity.TariffTier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TariffTierRepository extends JpaRepository<TariffTier, Long> {

    List<TariffTier> findByTariffIdOrderByMinLitresAsc(Long tariffId);

    void deleteByTariffId(Long tariffId);
}