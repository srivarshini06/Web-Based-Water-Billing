package com.water.backend.controller;

import com.water.backend.dto.request.ResidentRequest;
import com.water.backend.dto.response.ResidentResponse;
import com.water.backend.service.ResidentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
@RequiredArgsConstructor
public class ResidentController {

    private final ResidentService residentService;

    @Operation(summary = "Add resident")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ResidentResponse> addResident(
            @Valid @RequestBody ResidentRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(residentService.addResident(request));
    }

    @Operation(summary = "Get all residents")
    @GetMapping
    public ResponseEntity<List<ResidentResponse>> getAllResidents() {

        return ResponseEntity.ok(
                residentService.getAllResidents());
    }

    @Operation(summary = "Get resident by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResidentResponse> getResident(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                residentService.getResidentById(id));
    }

    @Operation(summary = "Get residents by community")
    @GetMapping("/community/{communityId}")
    public ResponseEntity<List<ResidentResponse>> getResidentsByCommunity(
            @PathVariable Long communityId) {

        return ResponseEntity.ok(
                residentService.getResidentsByCommunity(communityId));
    }

    @Operation(summary = "Invite resident")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/invite")
    public ResponseEntity<ResidentResponse> inviteResident(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                residentService.inviteResident(id));
    }

    @Operation(summary = "Delete resident")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResident(
            @PathVariable Long id) {

        residentService.deleteResident(id);

        return ResponseEntity.ok(
                "Resident deleted successfully.");
    }
}