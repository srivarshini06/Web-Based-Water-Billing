package com.water.backend.mapper;

import com.water.backend.dto.request.UserRequest;
import com.water.backend.dto.response.UserResponse;
import com.water.backend.entity.User;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(UserRequest request) {

        return User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();
    }

    public static UserResponse toResponse(User user) {

        Long communityId = null;

        if (user.getCommunity() != null) {
            communityId = user.getCommunity().getId();
        }

        return UserResponse.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .status(user.getStatus())
                .communityId(communityId)
                .build();
    }
}