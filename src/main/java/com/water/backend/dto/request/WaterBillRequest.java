package com.water.backend.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaterBillRequest {

    private Long consumerId;

    private Double unitsConsumed;

    private Double amount;

    private LocalDate billingDate;

    private LocalDate dueDate;

    private String status;
}