//package com.water.backend.mapper;
//
//import com.water.backend.dto.response.WaterBillResponse;
//import com.water.backend.entity.WaterBill;
//
//public class WaterBillMapper {
//
//    public static WaterBillResponse toResponse(WaterBill bill) {
//
//        return WaterBillResponse.builder()
//                .id(bill.getId())
//                .consumerId(bill.getConsumer().getId())
//                .consumerName(bill.getConsumer().getConsumerName())
//                .unitsConsumed(bill.getUnitsConsumed())
//                .amount(bill.getAmount())
//                .billingDate(bill.getBillingDate())
//                .dueDate(bill.getDueDate())
//                .status(bill.getStatus())
//                .build();
//    }
//}