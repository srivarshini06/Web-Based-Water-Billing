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

    /*
     * SUPERADMIN + COMMUNITY_ADMIN
     */
    @Operation(summary = "Add resident")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')"
    )
    @PostMapping
    public ResponseEntity<ResidentResponse>
    addResident(
            @Valid
            @RequestBody
            ResidentRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        residentService.addResident(
                                request
                        )
                );
    }

    /*
     * SUPERADMIN -> all residents
     *
     * COMMUNITY_ADMIN -> only his residents
     */
    @Operation(summary = "Get residents")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')"
    )
    @GetMapping
    public ResponseEntity<List<ResidentResponse>>
    getAllResidents() {

        return ResponseEntity.ok(
                residentService.getAllResidents()
        );
    }

    /*
     * SUPERADMIN -> any resident
     *
     * COMMUNITY_ADMIN -> own community only
     */
    @Operation(summary = "Get resident by ID")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResidentResponse>
    getResident(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                residentService
                        .getResidentById(id)
        );
    }

    /*
     * SUPERADMIN -> any community
     *
     * COMMUNITY_ADMIN -> own community only
     */
    @Operation(summary = "Get residents by community")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')"
    )
    @GetMapping("/community/{communityId}")
    public ResponseEntity<List<ResidentResponse>>
    getResidentsByCommunity(
            @PathVariable Long communityId) {

        return ResponseEntity.ok(
                residentService
                        .getResidentsByCommunity(
                                communityId
                        )
        );
    }

    /*
     * Invite resident.
     */
    @Operation(summary = "Invite resident")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')"
    )
    @PutMapping("/{id}/invite")
    public ResponseEntity<ResidentResponse>
    inviteResident(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                residentService
                        .inviteResident(id)
        );
    }

    /*
     * Delete resident.
     */
    @Operation(summary = "Delete resident")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteResident(
            @PathVariable Long id) {

        residentService.deleteResident(id);

        return ResponseEntity.ok(
                "Resident deleted successfully."
        );
    }
}