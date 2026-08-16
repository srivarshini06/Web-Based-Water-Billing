package com.water.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import com.water.backend.enums.BillingCycleStatus;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "billing_cycle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "community_id", nullable = false)
    private Long communityId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BillingCycleStatus status;

    @Column(name = "total_amount")
    private java.math.BigDecimal totalAmount;

    @Column(name = "total_invoices_generated")
    private Integer totalInvoicesGenerated = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "finalized_at")
    private LocalDateTime finalizedAt;

    @Column(name = "archived_at")
    private LocalDateTime archivedAt;

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

    // Getters for mapper
    public Long getId() { return id; }
    public Long getCommunityId() { return communityId; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public BillingCycleStatus getStatus() { return status; }
    public java.math.BigDecimal getTotalAmount() { return totalAmount; }
    public Integer getTotalInvoicesGenerated() { return totalInvoicesGenerated; }
    public LocalDateTime getFinalizedAt() { return finalizedAt; }
    public LocalDateTime getArchivedAt() { return archivedAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}