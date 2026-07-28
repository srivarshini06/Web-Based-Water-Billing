package com.water.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reading_id", nullable = false)
    private WaterReading waterReading;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_id", nullable = false)
    private Resident resident;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double consumption;

    @Column(nullable = false)
    private LocalDate billMonth;

    @Builder.Default
    @Column(nullable = false)
    private Boolean paid = false;

    private LocalDate paidDate;
}