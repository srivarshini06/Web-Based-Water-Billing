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

    /*
     * SUPERADMIN + COMMUNITY_ADMIN
     * can generate bills.
     */
    @Operation(summary = "Generate water bill")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')"
    )
    @PostMapping("/generate")
    public ResponseEntity<BillResponse> generateBill(
            @Valid @RequestBody BillRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        billService.generateBill(request)
                );
    }

    /*
     * All authenticated roles can access this endpoint.
     *
     * Later we can restrict this further based on
     * community ownership.
     */
    @Operation(summary = "Get all bills")
    @PreAuthorize(
            "hasAnyAuthority(" +
                    "'SUPERADMIN', " +
                    "'COMMUNITY_ADMIN', " +
                    "'RESIDENT'" +
                    ")"
    )
    @GetMapping
    public ResponseEntity<List<BillResponse>> getAllBills() {

        return ResponseEntity.ok(
                billService.getAllBills()
        );
    }

    /*
     * All authenticated roles can retrieve a bill by ID.
     */
    @Operation(summary = "Get bill by ID")
    @PreAuthorize(
            "hasAnyAuthority(" +
                    "'SUPERADMIN', " +
                    "'COMMUNITY_ADMIN', " +
                    "'RESIDENT'" +
                    ")"
    )
    @GetMapping("/{id}")
    public ResponseEntity<BillResponse> getBillById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                billService.getBillById(id)
        );
    }

    /*
     * Get bills belonging to a particular resident.
     */
    @Operation(summary = "Get bills of a resident")
    @PreAuthorize(
            "hasAnyAuthority(" +
                    "'SUPERADMIN', " +
                    "'COMMUNITY_ADMIN', " +
                    "'RESIDENT'" +
                    ")"
    )
    @GetMapping("/resident/{residentId}")
    public ResponseEntity<List<BillResponse>> getResidentBills(
            @PathVariable Long residentId) {

        return ResponseEntity.ok(
                billService.getResidentBills(residentId)
        );
    }

    /*
     * SUPERADMIN + COMMUNITY_ADMIN
     * can mark a bill as paid.
     */
    @Operation(summary = "Pay bill")
    @PreAuthorize(
            "hasAnyAuthority('SUPERADMIN', 'COMMUNITY_ADMIN')"
    )
    @PutMapping("/{id}/pay")
    public ResponseEntity<BillResponse> payBill(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                billService.payBill(id)
        );
    }
}