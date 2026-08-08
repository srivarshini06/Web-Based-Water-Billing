package com.water.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bulk_water_purchases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulkWaterPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @Column(nullable = false)
    private Double quantityLitres;

    @Column(nullable = false)
    private Double totalCost;

    @Column(nullable = false)
    private LocalDate purchaseDate;

    private String supplier;

    private String referenceNumber;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;
}