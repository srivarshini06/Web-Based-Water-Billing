package com.water.backend.controller;

import com.water.backend.dto.request.BulkWaterPurchaseRequest;
import com.water.backend.dto.response.BulkWaterPurchaseResponse;
import com.water.backend.service.BulkWaterPurchaseService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Create a bulk water purchase")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<BulkWaterPurchaseResponse> createPurchase(
            @Valid @RequestBody BulkWaterPurchaseRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bulkWaterPurchaseService.createPurchase(request));
    }

    @Operation(summary = "Get all bulk water purchases")
    @GetMapping
    public ResponseEntity<List<BulkWaterPurchaseResponse>> getAllPurchases() {

        return ResponseEntity.ok(
                bulkWaterPurchaseService.getAllPurchases()
        );
    }

    @Operation(summary = "Get bulk water purchases by community")
    @GetMapping("/community/{communityId}")
    public ResponseEntity<List<BulkWaterPurchaseResponse>> getPurchasesByCommunity(
            @PathVariable Long communityId) {

        return ResponseEntity.ok(
                bulkWaterPurchaseService
                        .getPurchasesByCommunity(communityId)
        );
    }

    @Operation(summary = "Get bulk water purchase by ID")
    @GetMapping("/{id}")
    public ResponseEntity<BulkWaterPurchaseResponse> getPurchaseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bulkWaterPurchaseService.getPurchaseById(id)
        );
    }

    @Operation(summary = "Update bulk water purchase")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BulkWaterPurchaseResponse> updatePurchase(
            @PathVariable Long id,
            @Valid @RequestBody BulkWaterPurchaseRequest request) {

        return ResponseEntity.ok(
                bulkWaterPurchaseService.updatePurchase(id, request)
        );
    }

    @Operation(summary = "Delete bulk water purchase")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchase(
            @PathVariable Long id) {

        bulkWaterPurchaseService.deletePurchase(id);

        return ResponseEntity.ok(
                "Bulk water purchase deleted successfully."
        );
    }
}