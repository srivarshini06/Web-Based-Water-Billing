package com.water.backend.mapper;

import com.water.backend.dto.response.BillResponse;
import com.water.backend.entity.Bill;

public final class BillMapper {

    private BillMapper() {
        // Utility class
    }

    public static BillResponse toResponse(Bill bill) {

        return BillResponse.builder()
                .id(bill.getId())
                .residentId(bill.getResident().getId())
                .residentName(bill.getResident().getFullName())
                .consumption(bill.getConsumption())
                .amount(bill.getAmount())
                .billMonth(bill.getBillMonth())
                .paid(bill.getPaid())
                .paidDate(bill.getPaidDate())
                .build();
    }
}