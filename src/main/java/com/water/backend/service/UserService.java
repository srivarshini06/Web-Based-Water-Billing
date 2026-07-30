package com.water.backend.service;

import com.water.backend.dto.request.LoginRequest;
import com.water.backend.dto.request.UserRequest;
import com.water.backend.dto.response.LoginResponse;
import com.water.backend.dto.response.UserResponse;

public interface UserService {

    UserResponse register(UserRequest request);

    LoginResponse login(LoginRequest request);
    UserResponse approveUser(Long id);
    UserResponse rejectUser(Long id);
    UserResponse suspendUser(Long id);
}