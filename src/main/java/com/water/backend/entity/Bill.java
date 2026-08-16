package com.water.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bill")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_id")
    private Resident resident;

    @Column(name = "consumption", nullable = false)
    private BigDecimal consumption;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "bill_month")
    private String billMonth;

    @Column(name = "is_paid", nullable = false)
    private Boolean paid = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Explicit getters for mapper
    public Long getId() { return id; }
    public Resident getResident() { return resident; }
    public BigDecimal getConsumption() { return consumption; }
    public BigDecimal getAmount() { return amount; }
    public String getBillMonth() { return billMonth; }
    public Boolean getPaid() { return paid; }
}