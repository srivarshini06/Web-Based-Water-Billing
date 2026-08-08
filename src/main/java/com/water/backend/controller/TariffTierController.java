package com.water.backend.controller;

import com.water.backend.dto.request.TariffTierRequest;
import com.water.backend.dto.response.TariffTierResponse;
import com.water.backend.service.TariffTierService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tariff-tiers")
@RequiredArgsConstructor
public class TariffTierController {

    private final TariffTierService tariffTierService;

    @Operation(summary = "Create a tariff tier")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/tariff/{tariffId}")
    public ResponseEntity<TariffTierResponse> createTier(
            @PathVariable Long tariffId,
            @Valid @RequestBody TariffTierRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tariffTierService.createTier(tariffId, request));
    }

    @Operation(summary = "Get all tiers for a tariff")
    @GetMapping("/tariff/{tariffId}")
    public ResponseEntity<List<TariffTierResponse>> getTiersByTariff(
            @PathVariable Long tariffId) {

        return ResponseEntity.ok(
                tariffTierService.getTiersByTariff(tariffId)
        );
    }

    @Operation(summary = "Get tariff tier by ID")
    @GetMapping("/{id}")
    public ResponseEntity<TariffTierResponse> getTierById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                tariffTierService.getTierById(id)
        );
    }

    @Operation(summary = "Update tariff tier")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TariffTierResponse> updateTier(
            @PathVariable Long id,
            @Valid @RequestBody TariffTierRequest request) {

        return ResponseEntity.ok(
                tariffTierService.updateTier(id, request)
        );
    }

    @Operation(summary = "Delete tariff tier")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTier(
            @PathVariable Long id) {

        tariffTierService.deleteTier(id);

        return ResponseEntity.ok(
                "Tariff tier deleted successfully."
        );
    }
}