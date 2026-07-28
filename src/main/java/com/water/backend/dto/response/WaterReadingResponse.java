package com.water.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class WaterReadingResponse {

    private Long id;

    private Long residentId;

    private String residentName;

    private LocalDate readingDate;

    private Double previousReading;

    private Double currentReading;

    private Double consumption;

    private Double tariffPerLitre;

    private Double amount;
}