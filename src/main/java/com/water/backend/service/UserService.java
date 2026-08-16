package com.water.backend.service;

import com.water.backend.dto.request.LoginRequest;
import com.water.backend.dto.request.UserProfileUpdateRequest;
import com.water.backend.dto.request.UserRequest;
import com.water.backend.dto.response.LoginResponse;
import com.water.backend.dto.response.PaginatedUserResponse;
import com.water.backend.dto.response.UserResponse;

public interface UserService {

    UserResponse register(UserRequest request);

    LoginResponse login(LoginRequest request);

    UserResponse approveUser(Long id);

    UserResponse rejectUser(Long id);

    UserResponse suspendUser(Long id);

    PaginatedUserResponse getPendingUsers(
            int page,
            int size
    );

    PaginatedUserResponse getUsersByStatus(
            String status,
            int page,
            int size
    );

    UserResponse updateProfile(
            String email,
            UserProfileUpdateRequest request
    );
}