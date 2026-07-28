//package com.water.backend.service.impl;
//
//import com.water.backend.dto.request.WaterBillRequest;
//import com.water.backend.dto.response.WaterBillResponse;
//import com.water.backend.entity.Consumer;
//import com.water.backend.entity.WaterBill;
//import com.water.backend.mapper.WaterBillMapper;
//import com.water.backend.repository.ConsumerRepository;
//import com.water.backend.repository.WaterBillRepository;
//import com.water.backend.service.WaterBillService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class WaterBillServiceImpl implements WaterBillService {
//
//    private final WaterBillRepository waterBillRepository;
//    private final ConsumerRepository consumerRepository;
//
//    @Override
//    public WaterBillResponse createBill(WaterBillRequest request) {
//
//        Consumer consumer = consumerRepository.findById(request.getConsumerId())
//                .orElseThrow(() -> new RuntimeException("Consumer not found"));
//
//        WaterBill bill = WaterBill.builder()
//                .consumer(consumer)
//                .unitsConsumed(request.getUnitsConsumed())
//                .amount(request.getAmount())
//                .billingDate(request.getBillingDate())
//                .dueDate(request.getDueDate())
//                .status(request.getStatus())
//                .build();
//
//        return WaterBillMapper.toResponse(
//                waterBillRepository.save(bill));
//    }
//
//    @Override
//    public List<WaterBillResponse> getAllBills() {
//
//        return waterBillRepository.findAll()
//                .stream()
//                .map(WaterBillMapper::toResponse)
//                .toList();
//    }
//
//    @Override
//    public WaterBillResponse getBillById(Long id) {
//
//        WaterBill bill = waterBillRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Bill not found"));
//
//        return WaterBillMapper.toResponse(bill);
//    }
//
//    @Override
//    public WaterBillResponse updateBill(Long id, WaterBillRequest request) {
//
//        WaterBill bill = waterBillRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Bill not found"));
//
//        Consumer consumer = consumerRepository.findById(request.getConsumerId())
//                .orElseThrow(() -> new RuntimeException("Consumer not found"));
//
//        bill.setConsumer(consumer);
//        bill.setUnitsConsumed(request.getUnitsConsumed());
//        bill.setAmount(request.getAmount());
//        bill.setBillingDate(request.getBillingDate());
//        bill.setDueDate(request.getDueDate());
//        bill.setStatus(request.getStatus());
//
//        return WaterBillMapper.toResponse(
//                waterBillRepository.save(bill));
//    }
//
//    @Override
//    public void deleteBill(Long id) {
//
//        waterBillRepository.deleteById(id);
//    }
//}