package com.water.backend.repository;

import com.water.backend.entity.Resident;
import com.water.backend.entity.WaterUsageAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WaterUsageAlertRepository extends JpaRepository<WaterUsageAlert, Long> {
    List<WaterUsageAlert> findByResident(Resident resident);
    List<WaterUsageAlert> findByResidentAndAcknowledgedFalse(Resident resident);
    List<WaterUsageAlert> findByAlertType(String alertType);
}
