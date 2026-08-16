package com.water.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "water_tariffs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaterTariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    /*
     * Kept for backward compatibility.
     * Tiered billing will use TariffTier.pricePerLitre.
     */
    @Column(nullable = false)
    private Double pricePerLitre;

    @Column(nullable = false)
    private LocalDate effectiveFrom;

    @Column(nullable = false)
    private Boolean active;

    @Builder.Default
    @OneToMany(
            mappedBy = "tariff",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("minLitres ASC")
    private List<TariffTier> tiers = new ArrayList<>();
}