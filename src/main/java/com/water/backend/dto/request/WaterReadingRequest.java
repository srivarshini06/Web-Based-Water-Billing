package com.water.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WaterReadingRequest {

    @NotNull
    private Long residentId;

    @NotNull
    private Double currentReading;

    @NotNull
    private LocalDate readingDate;
}