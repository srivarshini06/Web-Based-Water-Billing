package com.water.backend.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaterMeterResponse {

    private Long id;

    private Long residentId;

    private String meterNumber;

    private LocalDate installationDate;

    private Double initialReading;

    private Boolean active;

    private LocalDateTime createdAt;
}