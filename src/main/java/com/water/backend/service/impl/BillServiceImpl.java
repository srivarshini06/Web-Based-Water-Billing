package com.water.backend.service.impl;

import com.water.backend.dto.request.BillRequest;
import com.water.backend.dto.response.BillResponse;
import com.water.backend.entity.Bill;
import com.water.backend.entity.Resident;
import com.water.backend.entity.WaterReading;
import com.water.backend.mapper.BillMapper;
import com.water.backend.repository.BillRepository;
import com.water.backend.repository.ResidentRepository;
import com.water.backend.repository.WaterReadingRepository;
import com.water.backend.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final WaterReadingRepository waterReadingRepository;
    private final ResidentRepository residentRepository;

    @Override
    public BillResponse generateBill(BillRequest request) {

        WaterReading reading = waterReadingRepository.findById(request.getWaterReadingId())
                .orElseThrow(() -> new IllegalArgumentException("Water reading not found"));

        Resident resident = reading.getResident();

        Bill bill = Bill.builder()
                .waterReading(reading)
                .resident(resident)
                .consumption(reading.getConsumption())
                .amount(reading.getAmount())
                .billMonth(request.getBillMonth())
                .paid(false)
                .build();

        Bill savedBill = billRepository.save(bill);

        return BillMapper.toResponse(savedBill);
    }

    @Override
    public List<BillResponse> getAllBills() {

        return billRepository.findAll()
                .stream()
                .map(BillMapper::toResponse)
                .toList();
    }

    @Override
    public BillResponse getBillById(Long id) {

        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bill not found"));

        return BillMapper.toResponse(bill);
    }

    @Override
    public List<BillResponse> getResidentBills(Long residentId) {

        Resident resident = residentRepository.findById(residentId)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

        return billRepository.findByResident(resident)
                .stream()
                .map(BillMapper::toResponse)
                .toList();
    }

    @Override
    public BillResponse payBill(Long id) {

        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bill not found"));

        if (bill.isPaid()) {
            throw new IllegalArgumentException("Bill is already paid");
        }

        bill.setPaid(true);
        bill.setPaidDate(LocalDate.now());

        Bill updatedBill = billRepository.save(bill);

        return BillMapper.toResponse(updatedBill);
    }
}