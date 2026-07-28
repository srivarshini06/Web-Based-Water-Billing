package com.water.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillResponse {

    private Long id;

    private Long residentId;

    private String residentName;

    private Double consumption;

    private Double amount;

    private LocalDate billMonth;

    private Boolean paid;

    private LocalDate paidDate;
}