package com.water.backend.service.impl;

import com.water.backend.dto.request.ComplaintRequest;
import com.water.backend.dto.response.ComplaintResponse;
import com.water.backend.entity.Complaint;
import com.water.backend.entity.Resident;
import com.water.backend.enums.ComplaintStatus;
import com.water.backend.mapper.ComplaintMapper;
import com.water.backend.repository.ComplaintRepository;
import com.water.backend.repository.ResidentRepository;
import com.water.backend.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ResidentRepository residentRepository;

    @Override
    public ComplaintResponse createComplaint(ComplaintRequest request) {

        Resident resident = residentRepository.findById(request.getResidentId())
                .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

        Complaint complaint = Complaint.builder()
                .resident(resident)
                .subject(request.getSubject())
                .description(request.getDescription())
                .status(ComplaintStatus.OPEN)
                .createdAt(LocalDateTime.now())
                .build();

        Complaint savedComplaint = complaintRepository.save(complaint);

        return ComplaintMapper.toResponse(savedComplaint);
    }

    @Override
    public List<ComplaintResponse> getAllComplaints() {

        return complaintRepository.findAll()
                .stream()
                .map(ComplaintMapper::toResponse)
                .toList();
    }

    @Override
    public ComplaintResponse getComplaintById(Long id) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Complaint not found"));

        return ComplaintMapper.toResponse(complaint);
    }

    @Override
    public List<ComplaintResponse> getComplaintsByResident(Long residentId) {

        Resident resident = residentRepository.findById(residentId)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

        return complaintRepository.findByResident(resident)
                .stream()
                .map(ComplaintMapper::toResponse)
                .toList();
    }

    @Override
    public List<ComplaintResponse> getComplaintsByStatus(ComplaintStatus status) {

        return complaintRepository.findByStatus(status)
                .stream()
                .map(ComplaintMapper::toResponse)
                .toList();
    }

    @Override
    public ComplaintResponse updateStatus(Long id, ComplaintStatus status) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Complaint not found"));

        complaint.setStatus(status);

        if (status == ComplaintStatus.RESOLVED) {
            complaint.setResolvedAt(LocalDateTime.now());
        }

        Complaint updatedComplaint = complaintRepository.save(complaint);

        return ComplaintMapper.toResponse(updatedComplaint);
    }
}