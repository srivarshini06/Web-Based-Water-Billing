package com.water.backend.service;

import com.water.backend.dto.request.CommunityAdminRegistrationRequest;
import com.water.backend.dto.response.CommunityResponse;

import java.util.List;

public interface CommunityService {

    CommunityResponse registerCommunity(
            CommunityAdminRegistrationRequest request
    );

    List<CommunityResponse> getAllCommunities();

    List<CommunityResponse> getPendingCommunities();

    CommunityResponse getCommunityById(Long id);

    CommunityResponse getMyCommunity();

    CommunityResponse approveCommunity(Long id);

    CommunityResponse rejectCommunity(Long id);
}