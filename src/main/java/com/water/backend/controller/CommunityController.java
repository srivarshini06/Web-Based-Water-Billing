package com.water.backend.controller;

import com.water.backend.dto.request.CommunityAdminRegistrationRequest;
import com.water.backend.dto.response.CommunityResponse;
import com.water.backend.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communities")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @Operation(
            summary = "Register Community Admin and Community"
    )
    @PostMapping("/register")
    public ResponseEntity<CommunityResponse> registerCommunity(
            @Valid
            @RequestBody
            CommunityAdminRegistrationRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        communityService.registerCommunity(request)
                );
    }

    @Operation(summary = "Get all communities")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @GetMapping
    public ResponseEntity<List<CommunityResponse>>
    getAllCommunities() {

        return ResponseEntity.ok(
                communityService.getAllCommunities()
        );
    }

    @Operation(summary = "Get my community")
    @PreAuthorize("hasAuthority('COMMUNITY_ADMIN')")
    @GetMapping("/my")
    public ResponseEntity<CommunityResponse>
    getMyCommunity() {

        return ResponseEntity.ok(
                communityService.getMyCommunity()
        );
    }

    @Operation(summary = "Get community by ID")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CommunityResponse>
    getCommunity(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                communityService.getCommunityById(id)
        );
    }

    @Operation(summary = "Approve community")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @PutMapping("/{id}/approve")
    public ResponseEntity<CommunityResponse>
    approve(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                communityService.approveCommunity(id)
        );
    }

    @Operation(summary = "Reject community")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @PutMapping("/{id}/reject")
    public ResponseEntity<CommunityResponse>
    reject(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                communityService.rejectCommunity(id)
        );
    }

    @Operation(summary = "Get pending communities")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @GetMapping("/pending")
    public ResponseEntity<List<CommunityResponse>>
    getPendingCommunities() {

        return ResponseEntity.ok(
                communityService.getPendingCommunities()
        );
    }
}