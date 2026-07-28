package com.water.backend.repository;

import com.water.backend.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

    boolean existsByEmail(String email);

    Optional<Resident> findByEmail(String email);

    List<Resident> findByCommunityId(Long communityId);
}