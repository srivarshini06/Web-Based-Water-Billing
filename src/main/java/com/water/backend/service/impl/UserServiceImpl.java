package com.water.backend.service.impl;

import com.water.backend.dto.request.LoginRequest;
import com.water.backend.dto.request.UserRequest;
import com.water.backend.dto.response.LoginResponse;
import com.water.backend.dto.response.UserResponse;
import com.water.backend.entity.User;
import com.water.backend.enums.UserRole;
import com.water.backend.exception.InvalidCredentialsException;
import com.water.backend.exception.ResourceAlreadyExistsException;
import com.water.backend.mapper.UserMapper;
import com.water.backend.repository.UserRepository;
import com.water.backend.security.JwtService;
import com.water.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserResponse register(UserRequest request) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists.");
        }

        // Check if phone number already exists
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new ResourceAlreadyExistsException("Phone number already exists.");
        }

        // Convert DTO to Entity
        User user = UserMapper.toEntity(request);

        // 🔐 GET CURRENT USER ROLE (if logged in)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentRole = null;
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getAuthorities() != null
                && !authentication.getAuthorities().isEmpty()) {

            currentRole = authentication.getAuthorities().iterator().next().getAuthority();
        }

        // 🔐 ROLE ASSIGNMENT LOGIC
        if (request.getRole() == null) {

            // Default role
            user.setRole(UserRole.RESIDENT);

        } else {

            // 🚫 BLOCK SUPERADMIN CREATION
            if (request.getRole() == UserRole.SUPERADMIN) {
                throw new RuntimeException("Cannot assign SUPERADMIN role");
            }

            // 🚫 ONLY SUPERADMIN CAN CREATE COMMUNITY_ADMIN
            if (request.getRole() == UserRole.COMMUNITY_ADMIN) {

                if (currentRole == null || !currentRole.equals("SUPERADMIN")) {
                    throw new RuntimeException("Only SUPERADMIN can create COMMUNITY_ADMIN");
                }
            }

            user.setRole(request.getRole());
        }

        // ✅ APPROVAL LOGIC
        if (user.getRole() == UserRole.COMMUNITY_ADMIN) {
            user.setStatus(User.ApprovalStatus.PENDING);
        } else {
            user.setStatus(User.ApprovalStatus.APPROVED);
        }

        // Encrypt password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save user
        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password.");
        }

        // 🚫 BLOCK LOGIN IF NOT APPROVED
        if (user.getStatus() == User.ApprovalStatus.PENDING) {
            throw new RuntimeException("Account is pending approval");
        }

        if (user.getStatus() == User.ApprovalStatus.REJECTED) {
            throw new RuntimeException("Account has been rejected");
        }

        if (user.getStatus() == User.ApprovalStatus.SUSPENDED) {
            throw new RuntimeException("Account is suspended");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());

        return LoginResponse.builder()
                .token(token)
                .role(user.getRole().name())
                .fullName(user.getFullName())
                .build();
    }
    @Override
    public UserResponse approveUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(User.ApprovalStatus.APPROVED);

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }
    @Override
    public UserResponse rejectUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(User.ApprovalStatus.REJECTED);

        return UserMapper.toResponse(userRepository.save(user));
    }
    @Override
    public UserResponse suspendUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(User.ApprovalStatus.SUSPENDED);

        return UserMapper.toResponse(userRepository.save(user));
    }
}