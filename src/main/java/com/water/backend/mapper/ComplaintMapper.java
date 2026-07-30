package com.water.backend.mapper;

import com.water.backend.dto.response.ComplaintResponse;
import com.water.backend.entity.Complaint;

public class ComplaintMapper {

    private ComplaintMapper() {
    }

    public static ComplaintResponse toResponse(Complaint complaint) {

        return ComplaintResponse.builder()
                .id(complaint.getId())
                .residentId(complaint.getResident().getId())
                .residentName(complaint.getResident().getFullName())
                .subject(complaint.getSubject())
                .description(complaint.getDescription())
                .status(complaint.getStatus())
                .createdAt(complaint.getCreatedAt())
                .resolvedAt(complaint.getResolvedAt())
                .build();
    }
}