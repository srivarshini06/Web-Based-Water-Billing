package com.water.backend.service;

import com.water.backend.dto.request.CommunityRequest;
import com.water.backend.dto.response.CommunityResponse;

import java.util.List;

public interface CommunityService {

    CommunityResponse registerCommunity(CommunityRequest request);

    List<CommunityResponse> getAllCommunities();

    List<CommunityResponse> getPendingCommunities();

    CommunityResponse getCommunityById(Long id);

    CommunityResponse approveCommunity(Long id);

    CommunityResponse rejectCommunity(Long id);
}