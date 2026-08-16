package com.water.backend.controller;

import com.water.backend.dto.request.WaterReadingRequest;
import com.water.backend.dto.response.WaterReadingResponse;
import com.water.backend.service.WaterReadingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readings")
@RequiredArgsConstructor
public class WaterReadingController {

    private final WaterReadingService waterReadingService;

    @Operation(summary = "Add water reading")
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')")
    @PostMapping
    public ResponseEntity<WaterReadingResponse> addReading(
            @Valid @RequestBody WaterReadingRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(waterReadingService.addReading(request));
    }

    @Operation(summary = "Get all water readings")
    @GetMapping
    public ResponseEntity<List<WaterReadingResponse>> getAllReadings() {

        return ResponseEntity.ok(
                waterReadingService.getAllReadings());
    }

    @Operation(summary = "Get readings of a resident")
    @GetMapping("/resident/{residentId}")
    public ResponseEntity<List<WaterReadingResponse>> getResidentReadings(
            @PathVariable Long residentId) {

        return ResponseEntity.ok(
                waterReadingService.getResidentReadings(residentId));
    }
}