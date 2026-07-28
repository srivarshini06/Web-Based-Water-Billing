package com.water.backend.controller;

import com.water.backend.dto.request.ResidentRequest;
import com.water.backend.dto.response.ResidentResponse;
import com.water.backend.service.ResidentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
@RequiredArgsConstructor
public class ResidentController {

    private final ResidentService residentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResidentResponse addResident(
            @Valid @RequestBody ResidentRequest request) {

        return residentService.addResident(request);
    }

    @GetMapping
    public List<ResidentResponse> getAllResidents() {

        return residentService.getAllResidents();
    }

    @GetMapping("/{id}")
    public ResidentResponse getResident(@PathVariable Long id) {

        return residentService.getResidentById(id);
    }

    @GetMapping("/community/{communityId}")
    public List<ResidentResponse> getResidentsByCommunity(
            @PathVariable Long communityId) {

        return residentService.getResidentsByCommunity(communityId);
    }

    @PutMapping("/{id}/invite")
    public ResidentResponse inviteResident(@PathVariable Long id) {

        return residentService.inviteResident(id);
    }

    @DeleteMapping("/{id}")
    public String deleteResident(@PathVariable Long id) {

        residentService.deleteResident(id);
        return "Resident deleted successfully.";
    }
}