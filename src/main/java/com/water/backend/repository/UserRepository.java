package com.water.backend.repository;

import com.water.backend.entity.User;
import com.water.backend.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByRole(UserRole role);

    Page<User> findByStatus(User.ApprovalStatus status, Pageable pageable);

    // ✅ NEW (for ALL users pagination)
    Page<User> findAll(Pageable pageable);
}