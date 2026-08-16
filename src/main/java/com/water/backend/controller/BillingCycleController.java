package com.water.backend.controller;

import com.water.backend.dto.request.BillingCycleRequest;
import com.water.backend.dto.response.BillingCycleResponse;
import com.water.backend.service.BillingCycleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/billing-cycles")
@RequiredArgsConstructor
public class BillingCycleController {
    private final BillingCycleService service;
    @PostMapping @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN')") public ResponseEntity<BillingCycleResponse> create(@Valid @RequestBody BillingCycleRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.createBillingCycle(r));}
    @GetMapping("/{id}") @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN')") public ResponseEntity<BillingCycleResponse> get(@PathVariable Long id){return ResponseEntity.ok(service.getBillingCycleById(id));}
    @GetMapping("/community/{communityId}") @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN')") public ResponseEntity<List<BillingCycleResponse>> getByCommunity(@PathVariable Long communityId){return ResponseEntity.ok(service.getBillingCyclesByCommunity(communityId));}
    @PutMapping("/{id}/finalize") @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN')") public ResponseEntity<BillingCycleResponse> finalizeCycle(@PathVariable Long id){return ResponseEntity.ok(service.finalizeBillingCycle(id));}
    @PutMapping("/{id}/archive") @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN')") public ResponseEntity<BillingCycleResponse> archive(@PathVariable Long id){return ResponseEntity.ok(service.archiveBillingCycle(id));}
    @GetMapping @PreAuthorize("hasAuthority('SUPERADMIN')") public ResponseEntity<List<BillingCycleResponse>> all(){return ResponseEntity.ok(service.getAllBillingCycles());}
}
