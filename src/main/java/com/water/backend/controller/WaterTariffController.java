package com.water.backend.controller;

import com.water.backend.dto.request.WaterTariffRequest;
import com.water.backend.dto.response.WaterTariffResponse;
import com.water.backend.service.WaterTariffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tariffs")
@RequiredArgsConstructor
public class WaterTariffController {

    private final WaterTariffService waterTariffService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WaterTariffResponse createTariff(
            @RequestBody WaterTariffRequest request) {

        return waterTariffService.createTariff(request);
    }

    @GetMapping
    public List<WaterTariffResponse> getAllTariffs() {

        return waterTariffService.getAllTariffs();
    }

    @GetMapping("/community/{communityId}")
    public List<WaterTariffResponse> getTariffsByCommunity(
            @PathVariable Long communityId) {

        return waterTariffService.getTariffsByCommunity(communityId);
    }

    @GetMapping("/community/{communityId}/active")
    public WaterTariffResponse getActiveTariff(
            @PathVariable Long communityId) {

        return waterTariffService.getActiveTariff(communityId);
    }

    @PutMapping("/{id}")
    public WaterTariffResponse updateTariff(
            @PathVariable Long id,
            @RequestBody WaterTariffRequest request) {

        return waterTariffService.updateTariff(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteTariff(@PathVariable Long id) {

        waterTariffService.deleteTariff(id);

        return "Tariff deleted successfully.";
    }
}