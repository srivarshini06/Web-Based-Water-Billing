package com.water.backend.service;

import com.water.backend.dto.request.ComplaintRequest;
import com.water.backend.dto.response.ComplaintResponse;
import com.water.backend.enums.ComplaintStatus;

import java.util.List;

public interface ComplaintService {

    ComplaintResponse createComplaint(ComplaintRequest request);

    List<ComplaintResponse> getAllComplaints();

    ComplaintResponse getComplaintById(Long id);

    List<ComplaintResponse> getComplaintsByResident(Long residentId);

    List<ComplaintResponse> getComplaintsByStatus(ComplaintStatus status);

    ComplaintResponse updateStatus(Long id, ComplaintStatus status);
}