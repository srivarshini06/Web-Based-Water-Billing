package com.water.backend.repository;

import com.water.backend.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    boolean existsByConnectionNumber(String connectionNumber);

    boolean existsByEmail(String email);

    Optional<Consumer> findByConnectionNumber(String connectionNumber);
}