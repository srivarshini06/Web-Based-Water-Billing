package com.water.backend.controller;

import com.water.backend.dto.request.WaterTariffRequest;
import com.water.backend.dto.response.WaterTariffResponse;
import com.water.backend.service.WaterTariffService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tariffs")
@RequiredArgsConstructor
public class WaterTariffController {

    private final WaterTariffService waterTariffService;

    @Operation(summary = "Create a new water tariff")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<WaterTariffResponse> createTariff(
            @Valid @RequestBody WaterTariffRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(waterTariffService.createTariff(request));
    }

    @Operation(summary = "Get all tariffs")
    @GetMapping
    public ResponseEntity<List<WaterTariffResponse>> getAllTariffs() {

        return ResponseEntity.ok(
                waterTariffService.getAllTariffs());
    }

    @Operation(summary = "Get tariffs by community")
    @GetMapping("/community/{communityId}")
    public ResponseEntity<List<WaterTariffResponse>> getTariffsByCommunity(
            @PathVariable Long communityId) {

        return ResponseEntity.ok(
                waterTariffService.getTariffsByCommunity(communityId));
    }

    @Operation(summary = "Get active tariff of a community")
    @GetMapping("/community/{communityId}/active")
    public ResponseEntity<WaterTariffResponse> getActiveTariff(
            @PathVariable Long communityId) {

        return ResponseEntity.ok(
                waterTariffService.getActiveTariff(communityId));
    }

    @Operation(summary = "Update water tariff")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<WaterTariffResponse> updateTariff(
            @PathVariable Long id,
            @Valid @RequestBody WaterTariffRequest request) {

        return ResponseEntity.ok(
                waterTariffService.updateTariff(id, request));
    }

    @Operation(summary = "Delete water tariff")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTariff(
            @PathVariable Long id) {

        waterTariffService.deleteTariff(id);

        return ResponseEntity.ok("Tariff deleted successfully.");
    }
}