package com.water.backend.controller;

import com.water.backend.dto.request.ComplaintRequest;
import com.water.backend.dto.response.ComplaintResponse;
import com.water.backend.enums.ComplaintStatus;
import com.water.backend.service.ComplaintService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    @Operation(summary = "Create a complaint")
    @PostMapping
    public ResponseEntity<ComplaintResponse> createComplaint(
            @Valid @RequestBody ComplaintRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(complaintService.createComplaint(request));
    }

    @Operation(summary = "Get all complaints")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')")
    public ResponseEntity<List<ComplaintResponse>> getAllComplaints() {

        return ResponseEntity.ok(
                complaintService.getAllComplaints());
    }

    @Operation(summary = "Get complaint by ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')")
    public ResponseEntity<ComplaintResponse> getComplaintById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                complaintService.getComplaintById(id));
    }

    @Operation(summary = "Get complaints of a resident")
    @GetMapping("/resident/{residentId}")
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')")
    public ResponseEntity<List<ComplaintResponse>> getComplaintsByResident(
            @PathVariable Long residentId) {

        return ResponseEntity.ok(
                complaintService.getComplaintsByResident(residentId));
    }

    @Operation(summary = "Get complaints by status")
    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')")
    public ResponseEntity<List<ComplaintResponse>> getComplaintsByStatus(
            @PathVariable ComplaintStatus status) {

        return ResponseEntity.ok(
                complaintService.getComplaintsByStatus(status));
    }

    @Operation(summary = "Update complaint status")
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')")
    @PutMapping("/{id}/status")
    public ResponseEntity<ComplaintResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam ComplaintStatus status) {

        return ResponseEntity.ok(
                complaintService.updateStatus(id, status));
    }
}