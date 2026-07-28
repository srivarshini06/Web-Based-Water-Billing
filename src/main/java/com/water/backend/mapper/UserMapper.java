package com.water.backend.mapper;

import com.water.backend.dto.request.UserRequest;
import com.water.backend.dto.response.UserResponse;
import com.water.backend.entity.User;

public class UserMapper {

    public static User toEntity(UserRequest request) {

        return User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
    }

    public static UserResponse toResponse(User user) {

        return UserResponse.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }
}