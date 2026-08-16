package com.water.backend.mapper;

import com.water.backend.dto.response.CommunityResponse;
import com.water.backend.entity.Community;

public class CommunityMapper {

    public static CommunityResponse toResponse(Community community) {

        return CommunityResponse.builder()
                .id(community.getId())
                .communityName(community.getCommunityName())
                .ownerName(community.getOwnerName())
                .email(community.getEmail())
                .phone(community.getPhone())
                .address(community.getAddress())
                .status(community.getStatus())
                .createdAt(community.getCreatedAt())
                .approvedAt(community.getApprovedAt())
                .adminUserId(
                        community.getAdmin() != null
                                ? community.getAdmin().getUserId()
                                : null
                )
                .build();
    }
}