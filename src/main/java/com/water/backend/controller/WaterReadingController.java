package com.water.backend.controller;

import com.water.backend.dto.request.WaterReadingRequest;
import com.water.backend.dto.response.WaterReadingResponse;
import com.water.backend.service.WaterReadingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readings")
@RequiredArgsConstructor
public class WaterReadingController {

    private final WaterReadingService waterReadingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WaterReadingResponse addReading(
            @Valid @RequestBody WaterReadingRequest request) {

        return waterReadingService.addReading(request);
    }

    @GetMapping
    public List<WaterReadingResponse> getAllReadings() {

        return waterReadingService.getAllReadings();
    }

    @GetMapping("/resident/{residentId}")
    public List<WaterReadingResponse> getResidentReadings(
            @PathVariable Long residentId) {

        return waterReadingService.getResidentReadings(residentId);
    }
}