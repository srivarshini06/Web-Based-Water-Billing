package com.water.backend.repository;

import com.water.backend.entity.Resident;
import com.water.backend.entity.WaterReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WaterReadingRepository
        extends JpaRepository<WaterReading, Long> {

    List<WaterReading> findByResident(Resident resident);

    Optional<WaterReading>
    findTopByResidentOrderByReadingDateDesc(Resident resident);
}