package com.water.backend.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaterBillResponse {

    private Long id;

    private Long consumerId;

    private String consumerName;

    private Double unitsConsumed;

    private Double amount;

    private LocalDate billingDate;

    private LocalDate dueDate;

    private String status;
}