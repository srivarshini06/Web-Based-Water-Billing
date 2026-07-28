package com.water.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BillRequest {

    /**
     * Water reading used for bill generation.
     */
    @NotNull(message = "Water Reading ID is required")
    private Long waterReadingId;

    /**
     * Billing month.
     */
    @NotNull(message = "Bill month is required")
    private LocalDate billMonth;
}