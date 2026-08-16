package com.water.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemResponse {
    private String description;           // "Base consumption 0-10kL", "Excess 10+ kL", "Shared area allocation"
    private BigDecimal quantity;          // Volume in kL
    private BigDecimal unitRate;          // Rate per kL
    private BigDecimal subtotal;          // quantity * unitRate
    private String category;              // "CONSUMPTION", "SHARED_AREA", "ADJUSTMENT"
}