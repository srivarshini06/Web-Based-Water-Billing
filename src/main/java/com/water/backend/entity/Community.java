package com.water.backend.entity;

import com.water.backend.enums.CommunityStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "communities",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_communities_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_communities_admin", columnNames = "admin_user_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String communityName;

    @Column(nullable = false)
    private String ownerName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommunityStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime approvedAt;

    /*
     * One Community Admin -> One Community
     *
     * unique = true guarantees that one user cannot own
     * multiple communities.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "admin_user_id",
            unique = true
    )
    private User admin;

    @OneToMany(mappedBy = "community")
    private List<WaterTariff> tariffs;
}