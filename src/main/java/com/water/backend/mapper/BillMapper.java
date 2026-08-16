package com.water.backend.mapper;

import com.water.backend.dto.response.BillResponse;
import com.water.backend.entity.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillMapper {

    public BillResponse toResponse(Bill bill) {
        if (bill == null) {
            return null;
        }

        return BillResponse.builder()
                .id(bill.getId())
                .consumption(bill.getConsumption())
                .amount(bill.getAmount())
                .billMonth(bill.getBillMonth())
                .paid(bill.getPaid())
                .build();
    }
}