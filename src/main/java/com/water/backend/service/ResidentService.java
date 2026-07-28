package com.water.backend.service;

import com.water.backend.dto.request.ResidentRequest;
import com.water.backend.dto.response.ResidentResponse;

import java.util.List;

public interface ResidentService {

    ResidentResponse addResident(ResidentRequest request);

    List<ResidentResponse> getAllResidents();

    ResidentResponse getResidentById(Long id);

    List<ResidentResponse> getResidentsByCommunity(Long communityId);

    ResidentResponse inviteResident(Long id);

    void deleteResident(Long id);
}