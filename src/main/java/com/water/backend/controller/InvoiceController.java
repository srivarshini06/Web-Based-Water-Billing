package com.water.backend.controller;

import com.water.backend.dto.response.InvoiceResponse;
import com.water.backend.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService service;
    @PostMapping("/cycle/{billingCycleId}/resident/{residentId}") @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN')") public ResponseEntity<InvoiceResponse> generate(@PathVariable Long billingCycleId,@PathVariable Long residentId){return ResponseEntity.ok(service.generateInvoice(billingCycleId,residentId));}
    @GetMapping("/{id}") @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN','RESIDENT')") public ResponseEntity<InvoiceResponse> get(@PathVariable Long id){return ResponseEntity.ok(service.getInvoiceById(id));}
    @GetMapping("/cycle/{billingCycleId}") @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN')") public ResponseEntity<List<InvoiceResponse>> byCycle(@PathVariable Long billingCycleId){return ResponseEntity.ok(service.getInvoicesByBillingCycle(billingCycleId));}
    @GetMapping("/resident/{residentId}") @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN','RESIDENT')") public ResponseEntity<List<InvoiceResponse>> byResident(@PathVariable Long residentId){return ResponseEntity.ok(service.getInvoicesByResident(residentId));}
    @PutMapping("/{id}/pay") @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN')") public ResponseEntity<InvoiceResponse> pay(@PathVariable Long id){return ResponseEntity.ok(service.markAsPaid(id));}
}
