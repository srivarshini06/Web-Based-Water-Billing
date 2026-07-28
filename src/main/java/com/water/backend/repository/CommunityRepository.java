package com.water.backend.repository;

import com.water.backend.entity.Community;
import com.water.backend.enums.CommunityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    boolean existsByEmail(String email);

    List<Community> findByStatus(CommunityStatus status);
}