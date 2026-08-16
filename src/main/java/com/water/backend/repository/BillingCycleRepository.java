package com.water.backend.repository;

import com.water.backend.entity.BillingCycle;
import com.water.backend.entity.Community;
import com.water.backend.enums.BillingCycleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BillingCycleRepository extends JpaRepository<BillingCycle, Long> {
    List<BillingCycle> findByCommunity(Community community);
    List<BillingCycle> findByCommunityId(Long communityId);
    Optional<BillingCycle> findByCommunityAndStatusAndId(Community community, BillingCycleStatus status, Long id);
    boolean existsByCommunityAndStartDateAndEndDate(Community community, java.time.LocalDate startDate, java.time.LocalDate endDate);
}
