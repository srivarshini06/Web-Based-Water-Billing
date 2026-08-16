package com.water.backend.controller;

import com.water.backend.dto.request.BulkWaterPurchaseRequest;
import com.water.backend.dto.response.BulkWaterPurchaseResponse;
import com.water.backend.service.BulkWaterPurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bulk-water-purchases")
@RequiredArgsConstructor
public class BulkWaterPurchaseController {

    private final BulkWaterPurchaseService bulkWaterPurchaseService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')")
    public ResponseEntity<BulkWaterPurchaseResponse> createPurchase(
            @Valid @RequestBody BulkWaterPurchaseRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        bulkWaterPurchaseService.createPurchase(request)
                );
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')")
    public ResponseEntity<List<BulkWaterPurchaseResponse>> getAllPurchases() {

        return ResponseEntity.ok(
                bulkWaterPurchaseService.getAllPurchases()
        );
    }

    @GetMapping("/community/{communityId}")
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN', 'RESIDENT')")
    public ResponseEntity<List<BulkWaterPurchaseResponse>>
    getPurchasesByCommunity(
            @PathVariable Long communityId) {

        return ResponseEntity.ok(
                bulkWaterPurchaseService
                        .getPurchasesByCommunity(communityId)
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')")
    public ResponseEntity<BulkWaterPurchaseResponse> getPurchaseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bulkWaterPurchaseService.getPurchaseById(id)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')")
    public ResponseEntity<BulkWaterPurchaseResponse> updatePurchase(
            @PathVariable Long id,
            @Valid @RequestBody BulkWaterPurchaseRequest request) {

        return ResponseEntity.ok(
                bulkWaterPurchaseService
                        .updatePurchase(id, request)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')")
    public ResponseEntity<Void> deletePurchase(
            @PathVariable Long id) {

        bulkWaterPurchaseService.deletePurchase(id);

        return ResponseEntity.noContent().build();
    }
}