package com.water.backend.service.impl;

import com.water.backend.dto.request.LoginRequest;
import com.water.backend.dto.request.UserRequest;
import com.water.backend.dto.response.LoginResponse;
import com.water.backend.dto.response.PaginatedUserResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.water.backend.dto.request.LoginRequest;
import com.water.backend.dto.request.UserProfileUpdateRequest;
import com.water.backend.dto.request.UserRequest;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /*
     * Normal user registration.
     *
     * IMPORTANT:
     * Community Admin registration does NOT use this method.
     * Community Admin registration happens through:
     *
     * POST /api/communities/register
     */
    @Override
    @Transactional
    public UserResponse register(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException(
                    "Email already exists."
            );
        }

        if (userRepository.existsByPhoneNumber(
                request.getPhoneNumber())) {

            throw new ResourceAlreadyExistsException(
                    "Phone number already exists."
            );
        }

        User user = UserMapper.toEntity(request);

        /*
         * Public user registration creates only RESIDENT.
         *
         * COMMUNITY_ADMIN and SUPERADMIN can NEVER
         * be created through /api/users/register.
         */
        user.setRole(UserRole.RESIDENT);

        user.setStatus(User.ApprovalStatus.APPROVED);

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "Invalid email or password."
                        )
                );

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid email or password."
            );
        }

        if (user.getStatus() == User.ApprovalStatus.PENDING) {
            throw new AccessDeniedException(
                    "Account is pending approval."
            );
        }

        if (user.getStatus() == User.ApprovalStatus.REJECTED) {
            throw new AccessDeniedException(
                    "Account has been rejected."
            );
        }

        if (user.getStatus() == User.ApprovalStatus.SUSPENDED) {
            throw new AccessDeniedException(
                    "Account is suspended."
            );
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return LoginResponse.builder()
                .token(token)
                .role(user.getRole().name())
                .fullName(user.getFullName())
                .build();
    }

    @Override
    @Transactional
    public UserResponse approveUser(Long id) {

        User user = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found."
                        )
                );

        if (user.getRole() != UserRole.COMMUNITY_ADMIN) {
            throw new IllegalStateException(
                    "Only Community Admin accounts require approval here."
            );
        }

        if (user.getStatus() != User.ApprovalStatus.PENDING) {
            throw new IllegalStateException(
                    "Only pending users can be approved."
            );
        }

        user.setStatus(
                User.ApprovalStatus.APPROVED
        );

        return UserMapper.toResponse(
                userRepository.save(user)
        );
    }

    @Override
    @Transactional
    public UserResponse rejectUser(Long id) {

        User user = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found."
                        )
                );

        if (user.getStatus() != User.ApprovalStatus.PENDING) {
            throw new IllegalStateException(
                    "Only pending users can be rejected."
            );
        }

        user.setStatus(
                User.ApprovalStatus.REJECTED
        );

        return UserMapper.toResponse(
                userRepository.save(user)
        );
    }

    @Override
    @Transactional
    public UserResponse suspendUser(Long id) {

        User user = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found."
                        )
                );

        if (user.getStatus() != User.ApprovalStatus.APPROVED) {
            throw new IllegalStateException(
                    "Only approved users can be suspended."
            );
        }

        user.setStatus(
                User.ApprovalStatus.SUSPENDED
        );

        return UserMapper.toResponse(
                userRepository.save(user)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PaginatedUserResponse getPendingUsers(
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        Page<User> usersPage =
                userRepository.findByStatus(
                        User.ApprovalStatus.PENDING,
                        pageable
                );

        return PaginatedUserResponse.builder()
                .users(
                        usersPage.getContent()
                                .stream()
                                .map(UserMapper::toResponse)
                                .toList()
                )
                .currentPage(
                        usersPage.getNumber()
                )
                .totalPages(
                        usersPage.getTotalPages()
                )
                .totalItems(
                        usersPage.getTotalElements()
                )
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public PaginatedUserResponse getUsersByStatus(
            String status,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        Page<User> usersPage;

        if (status == null
                || status.isBlank()
                || status.equalsIgnoreCase("ALL")) {

            usersPage =
                    userRepository.findAll(pageable);

        } else {

            try {

                User.ApprovalStatus enumStatus =
                        User.ApprovalStatus.valueOf(
                                status.toUpperCase()
                        );

                usersPage =
                        userRepository.findByStatus(
                                enumStatus,
                                pageable
                        );

            } catch (IllegalArgumentException e) {

                throw new IllegalArgumentException(
                        "Invalid status value: " + status
                );
            }
        }

        return PaginatedUserResponse.builder()
                .users(
                        usersPage.getContent()
                                .stream()
                                .map(UserMapper::toResponse)
                                .toList()
                )
                .currentPage(
                        usersPage.getNumber()
                )
                .totalPages(
                        usersPage.getTotalPages()
                )
                .totalItems(
                        usersPage.getTotalElements()
                )
                .build();
    }
    @Override
    @Transactional
    public UserResponse updateProfile(
            String email,
            UserProfileUpdateRequest request) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found."
                        )
                );

        /*
         * If the phone number is being changed,
         * make sure another user is not already using it.
         */
        if (!user.getPhoneNumber().equals(request.getPhoneNumber())
                && userRepository.existsByPhoneNumber(
                request.getPhoneNumber())) {

            throw new ResourceAlreadyExistsException(
                    "Phone number already exists."
            );
        }

        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }
}