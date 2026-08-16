package com.water.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tariff_tiers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TariffTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tariff_id", nullable = false)
    private WaterTariff tariff;

    /*
     * Minimum consumption for this tier.
     *
     * Example:
     * 0
     * 10000
     * 20000
     */
    @Column(nullable = false)
    private Double minLitres;

    /*
     * Maximum consumption for this tier.
     *
     * The maximum is treated as EXCLUSIVE.
     *
     * Example:
     * 0 - 10000
     * 10000 - 20000
     * 20000 - infinity
     */
    private Double maxLitres;

    @Column(nullable = false)
    private Double pricePerLitre;
}