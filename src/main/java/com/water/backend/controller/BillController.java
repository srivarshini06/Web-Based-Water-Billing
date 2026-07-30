package com.water.backend.controller;

import com.water.backend.dto.request.BillRequest;
import com.water.backend.dto.response.BillResponse;
import com.water.backend.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @Operation(summary = "Generate water bill")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/generate")
    public ResponseEntity<BillResponse> generateBill(
            @Valid @RequestBody BillRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(billService.generateBill(request));
    }

    @Operation(summary = "Get all bills")
    @GetMapping
    public ResponseEntity<List<BillResponse>> getAllBills() {

        return ResponseEntity.ok(
                billService.getAllBills());
    }

    @Operation(summary = "Get bill by ID")
    @GetMapping("/{id}")
    public ResponseEntity<BillResponse> getBillById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                billService.getBillById(id));
    }

    @Operation(summary = "Get bills of a resident")
    @GetMapping("/resident/{residentId}")
    public ResponseEntity<List<BillResponse>> getResidentBills(
            @PathVariable Long residentId) {

        return ResponseEntity.ok(
                billService.getResidentBills(residentId));
    }

    @Operation(summary = "Pay bill")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}/pay")
    public ResponseEntity<BillResponse> payBill(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                billService.payBill(id));
    }
}