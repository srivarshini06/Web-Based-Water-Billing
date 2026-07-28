package com.water.backend.mapper;

import com.water.backend.dto.request.ResidentRequest;
import com.water.backend.dto.response.ResidentResponse;
import com.water.backend.entity.Community;
import com.water.backend.entity.Resident;

public class ResidentMapper {

    public static Resident toEntity(ResidentRequest request, Community community) {

        return Resident.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .building(request.getBuilding())
                .block(request.getBlock())
                .flatNumber(request.getFlatNumber())
                .community(community)
                .invited(false)
                .registered(false)
                .build();
    }

    public static ResidentResponse toResponse(Resident resident) {

        return ResidentResponse.builder()
                .id(resident.getId())
                .fullName(resident.getFullName())
                .email(resident.getEmail())
                .phoneNumber(resident.getPhoneNumber())
                .building(resident.getBuilding())
                .block(resident.getBlock())
                .flatNumber(resident.getFlatNumber())
                .invited(resident.getInvited())
                .registered(resident.getRegistered())
                .communityId(resident.getCommunity().getId())
                .build();
    }
}