package com.water.backend.repository;

import com.water.backend.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    // ADD THIS METHOD
    List<Consumer> findByCommunityIdCommunityId(Long communityId);

    Optional<Consumer> findByEmail(String email);

    List<Consumer> findByCommunityId(Long communityId);

    List<Consumer> findByResidentId(Long residentId);
}