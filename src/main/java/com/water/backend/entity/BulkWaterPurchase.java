package com.water.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bulk_water_purchase")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulkWaterPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "community_id", nullable = false)
    private Long communityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_fk")
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_cycle_id")
    private BillingCycle billingCycle;

    @Column(name = "quantity_litres", nullable = false)  // in litres
    private Long quantityLitres;

    @Column(name = "price_per_litre", nullable = false)  // rate per litre
    private BigDecimal pricePerLitre;

    @Column(name = "total_cost", nullable = false)  // quantityLitres * pricePerLitre
    private BigDecimal totalCost;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "source")  // e.g., "TANKER", "MUNICIPAL", "BOREWELL"
    private String source;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        // Calculate total cost
        if (quantityLitres != null && pricePerLitre != null) {
            totalCost = pricePerLitre.multiply(new BigDecimal(quantityLitres));
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters (Lombok @Data generates these, but explicit for clarity)
    public Long getId() { return id; }
    public Long getCommunityId() { return communityId; }
    public Community getCommunity() { return community; }
    public BillingCycle getBillingCycle() { return billingCycle; }
    public Long getQuantityLitres() { return quantityLitres; }
    public BigDecimal getPricePerLitre() { return pricePerLitre; }
    public BigDecimal getTotalCost() { return totalCost; }
    public LocalDateTime getPurchaseDate() { return purchaseDate; }
    public LocalDateTime getDeliveryDate() { return deliveryDate; }
    public String getSource() { return source; }
    public String getSupplierName() { return supplierName; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public String getRemarks() { return remarks; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}