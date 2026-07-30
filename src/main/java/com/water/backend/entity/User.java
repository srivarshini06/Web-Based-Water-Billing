package com.water.backend.entity;

import com.water.backend.dto.response.UserResponse;
import com.water.backend.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;


    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;
    public enum ApprovalStatus {
        PENDING,
        APPROVED,
        REJECTED,
        SUSPENDED
    }
}