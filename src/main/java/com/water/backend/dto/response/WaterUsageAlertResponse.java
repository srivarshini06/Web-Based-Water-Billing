package com.water.backend.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaterUsageAlertResponse {
    private Long id;
    private Long residentId;
    private String residentName;
    private String alertType;
    private String message;
    private Double detectedValue;
    private Double threshold;
    private Boolean acknowledged;
    private LocalDateTime createdAt;
}
