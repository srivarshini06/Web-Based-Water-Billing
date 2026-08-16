package com.water.backend.repository;

import com.water.backend.entity.Resident;
import com.water.backend.entity.WaterMeter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WaterMeterRepository
        extends JpaRepository<WaterMeter, Long> {

    Optional<WaterMeter> findByResidentAndActiveTrue(
            Resident resident
    );

    List<WaterMeter> findByResident(
            Resident resident
    );

    Optional<WaterMeter> findByMeterNumber(
            String meterNumber
    );

    boolean existsByMeterNumberAndIdNot(
            String meterNumber,
            Long id
    );
}