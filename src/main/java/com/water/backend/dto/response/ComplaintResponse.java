package com.water.backend.dto.response;

import com.water.backend.enums.ComplaintStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintResponse {

    private Long id;

    private Long residentId;

    private String residentName;

    private String subject;

    private String description;

    private ComplaintStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime resolvedAt;
}