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

    @Column(nullable = false)
    private Double minLitres;

    private Double maxLitres;

    @Column(nullable = false)
    private Double pricePerLitre;
}