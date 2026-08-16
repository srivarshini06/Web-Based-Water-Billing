package com.water.backend.repository;

import com.water.backend.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByConsumerId(Long consumerId);

    // ADD THIS METHOD
    List<Invoice> findByBillingCycleId(Long billingCycleId);

    List<Invoice> findByStatus(String status);
}