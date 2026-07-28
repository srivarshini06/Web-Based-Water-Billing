package com.water.backend.controller;

import com.water.backend.dto.request.CommunityRequest;
import com.water.backend.dto.response.CommunityResponse;
import com.water.backend.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communities")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommunityResponse registerCommunity(
            @Valid @RequestBody CommunityRequest request) {

        return communityService.registerCommunity(request);
    }

    @GetMapping
    public List<CommunityResponse> getAllCommunities() {

        return communityService.getAllCommunities();
    }

    @GetMapping("/{id}")
    public CommunityResponse getCommunity(@PathVariable Long id) {

        return communityService.getCommunityById(id);
    }

    @PutMapping("/{id}/approve")
    public CommunityResponse approve(@PathVariable Long id) {

        return communityService.approveCommunity(id);
    }
    @GetMapping("/pending")
    public List<CommunityResponse> getPendingCommunities() {

        return communityService.getPendingCommunities();
    }

    @PutMapping("/{id}/reject")
    public CommunityResponse reject(@PathVariable Long id) {

        return communityService.rejectCommunity(id);
    }
}