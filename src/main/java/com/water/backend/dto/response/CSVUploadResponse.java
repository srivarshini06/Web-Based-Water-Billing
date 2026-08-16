package com.water.backend.dto.response;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CSVUploadResponse {
    private int totalRows;
    private int successfulRows;
    private int duplicateRows;
    private int failedRows;
    private List<String> errors;
    private List<String> warnings;
    private String message;
}
