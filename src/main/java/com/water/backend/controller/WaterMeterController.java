package com.water.backend.controller;

import com.water.backend.dto.request.WaterMeterRequest;
import com.water.backend.dto.response.WaterMeterResponse;
import com.water.backend.service.WaterMeterService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/water-meters")
@RequiredArgsConstructor
public class WaterMeterController {

    private final WaterMeterService waterMeterService;

    @Operation(summary = "Create water meter")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')"
    )
    @PostMapping
    public ResponseEntity<WaterMeterResponse> createWaterMeter(
            @Valid @RequestBody WaterMeterRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        waterMeterService
                                .createWaterMeter(request)
                );
    }

    @Operation(summary = "Get active water meter by resident")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN', 'RESIDENT')"
    )
    @GetMapping("/resident/{residentId}")
    public ResponseEntity<WaterMeterResponse>
    getWaterMeterByResident(
            @PathVariable Long residentId
    ) {

        return ResponseEntity.ok(
                waterMeterService
                        .getWaterMeterByResident(residentId)
        );
    }

    @Operation(summary = "Get all water meters of resident")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN', 'RESIDENT')"
    )
    @GetMapping("/resident/{residentId}/all")
    public ResponseEntity<List<WaterMeterResponse>>
    getWaterMetersByResident(
            @PathVariable Long residentId
    ) {

        return ResponseEntity.ok(
                waterMeterService
                        .getWaterMetersByResident(residentId)
        );
    }

    @Operation(summary = "Get water meter by ID")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')"
    )
    @GetMapping("/{meterId}")
    public ResponseEntity<WaterMeterResponse>
    getWaterMeterById(
            @PathVariable Long meterId
    ) {

        return ResponseEntity.ok(
                waterMeterService
                        .getWaterMeterById(meterId)
        );
    }

    @Operation(summary = "Update water meter")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')"
    )
    @PutMapping("/{meterId}")
    public ResponseEntity<WaterMeterResponse>
    updateWaterMeter(
            @PathVariable Long meterId,
            @Valid @RequestBody WaterMeterRequest request
    ) {

        return ResponseEntity.ok(
                waterMeterService
                        .updateWaterMeter(
                                meterId,
                                request
                        )
        );
    }

    @Operation(summary = "Deactivate water meter")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')"
    )
    @DeleteMapping("/{meterId}")
    public ResponseEntity<Void> deleteWaterMeter(
            @PathVariable Long meterId
    ) {

        waterMeterService.deleteWaterMeter(meterId);

        return ResponseEntity.noContent().build();
    }
}