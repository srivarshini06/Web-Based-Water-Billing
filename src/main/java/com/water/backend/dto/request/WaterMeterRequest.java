package com.water.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaterMeterRequest {

    @NotNull
    private Long residentId;

    @NotBlank
    private String meterNumber;

    @NotNull
    private LocalDate installationDate;

    @NotNull
    @PositiveOrZero
    private Double initialReading;
}