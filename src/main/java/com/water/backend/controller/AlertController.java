package com.water.backend.controller;

import com.water.backend.dto.response.WaterUsageAlertResponse;
import com.water.backend.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {
    private final AlertService service;
    @GetMapping("/resident/{residentId}") @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN','RESIDENT')") public ResponseEntity<List<WaterUsageAlertResponse>> byResident(@PathVariable Long residentId){return ResponseEntity.ok(service.getAlertsByResident(residentId));}
    @PutMapping("/{alertId}/acknowledge") @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN','RESIDENT')") public ResponseEntity<WaterUsageAlertResponse> acknowledge(@PathVariable Long alertId){return ResponseEntity.ok(service.acknowledgeAlert(alertId));}
    @PostMapping("/run") @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN')") public ResponseEntity<Void> run(){service.detectThresholdViolations(); service.detectOutliers(); return ResponseEntity.accepted().build();}
}
