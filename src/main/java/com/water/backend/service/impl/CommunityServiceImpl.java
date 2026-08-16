package com.water.backend.service.impl;

import com.water.backend.dto.request.CommunityAdminRegistrationRequest;
import com.water.backend.dto.response.CommunityResponse;
import com.water.backend.entity.Community;
import com.water.backend.entity.User;
import com.water.backend.enums.CommunityStatus;
import com.water.backend.enums.UserRole;
import com.water.backend.exception.ResourceAlreadyExistsException;
import com.water.backend.mapper.CommunityMapper;
import com.water.backend.repository.CommunityRepository;
import com.water.backend.repository.UserRepository;
import com.water.backend.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public CommunityResponse registerCommunity(
            CommunityAdminRegistrationRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException(
                    "Admin email already exists."
            );
        }

        if (userRepository.existsByPhoneNumber(
                request.getPhoneNumber())) {

            throw new ResourceAlreadyExistsException(
                    "Admin phone number already exists."
            );
        }

        if (communityRepository.existsByEmail(
                request.getCommunityEmail())) {

            throw new ResourceAlreadyExistsException(
                    "Community email already exists."
            );
        }

        User admin = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(UserRole.COMMUNITY_ADMIN)
                .status(User.ApprovalStatus.PENDING)
                .build();

        User savedAdmin = userRepository.save(admin);

        Community community = Community.builder()
                .communityName(request.getCommunityName())
                .ownerName(request.getFullName())
                .email(request.getCommunityEmail())
                .phone(request.getCommunityPhone())
                .address(request.getCommunityAddress())
                .status(CommunityStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .admin(savedAdmin)
                .build();

        Community savedCommunity =
                communityRepository.save(community);

        return CommunityMapper.toResponse(savedCommunity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunityResponse> getAllCommunities() {

        requireSuperAdmin();

        return communityRepository.findAll()
                .stream()
                .map(CommunityMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunityResponse> getPendingCommunities() {

        requireSuperAdmin();

        return communityRepository
                .findByStatus(CommunityStatus.PENDING)
                .stream()
                .map(CommunityMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CommunityResponse getCommunityById(Long id) {

        Community community =
                communityRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Community not found."
                                )
                        );

        User currentUser = getCurrentUser();

        /*
         * SUPERADMIN can view any community.
         */
        if (currentUser.getRole() == UserRole.SUPERADMIN) {
            return CommunityMapper.toResponse(community);
        }

        /*
         * COMMUNITY_ADMIN can view only his own community.
         */
        if (currentUser.getRole()
                != UserRole.COMMUNITY_ADMIN) {

            throw new AccessDeniedException(
                    "You are not allowed to access communities."
            );
        }

        if (community.getAdmin() == null
                || !community.getAdmin()
                .getUserId()
                .equals(currentUser.getUserId())) {

            throw new AccessDeniedException(
                    "You can access only your own community."
            );
        }

        return CommunityMapper.toResponse(community);
    }

    @Override
    @Transactional(readOnly = true)
    public CommunityResponse getMyCommunity() {

        User currentUser = getCurrentUser();

        if (currentUser.getRole()
                != UserRole.COMMUNITY_ADMIN) {

            throw new AccessDeniedException(
                    "Only COMMUNITY_ADMIN can access this endpoint."
            );
        }

        Community community =
                communityRepository
                        .findByAdminUserId(
                                currentUser.getUserId()
                        )
                        .orElseThrow(
                                () -> new AccessDeniedException(
                                        "No community is assigned to this admin."
                                )
                        );

        return CommunityMapper.toResponse(community);
    }

    @Override
    @Transactional
    public CommunityResponse approveCommunity(Long id) {

        requireSuperAdmin();

        Community community =
                communityRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Community not found."
                                )
                        );

        if (community.getStatus()
                != CommunityStatus.PENDING) {

            throw new IllegalStateException(
                    "Only pending communities can be approved."
            );
        }

        if (community.getAdmin() == null) {

            throw new IllegalStateException(
                    "Community has no Community Admin."
            );
        }

        User admin = community.getAdmin();

        if (admin.getRole()
                != UserRole.COMMUNITY_ADMIN) {

            throw new IllegalStateException(
                    "Linked user is not a Community Admin."
            );
        }

        if (admin.getStatus()
                != User.ApprovalStatus.PENDING) {

            throw new IllegalStateException(
                    "Community Admin is not pending."
            );
        }

        /*
         * Approve both together.
         */
        admin.setStatus(
                User.ApprovalStatus.APPROVED
        );

        community.setStatus(
                CommunityStatus.APPROVED
        );

        community.setApprovedAt(
                LocalDateTime.now()
        );

        userRepository.save(admin);

        Community saved =
                communityRepository.save(community);

        return CommunityMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public CommunityResponse rejectCommunity(Long id) {

        requireSuperAdmin();

        Community community =
                communityRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Community not found."
                                )
                        );

        if (community.getStatus()
                != CommunityStatus.PENDING) {

            throw new IllegalStateException(
                    "Only pending communities can be rejected."
            );
        }

        if (community.getAdmin() == null) {

            throw new IllegalStateException(
                    "Community has no Community Admin."
            );
        }

        User admin = community.getAdmin();

        admin.setStatus(
                User.ApprovalStatus.REJECTED
        );

        community.setStatus(
                CommunityStatus.REJECTED
        );

        userRepository.save(admin);

        Community saved =
                communityRepository.save(community);

        return CommunityMapper.toResponse(saved);
    }

    /*
     * -------------------------------
     * SECURITY HELPERS
     * -------------------------------
     */

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getName() == null) {

            throw new AccessDeniedException(
                    "Authentication required."
            );
        }

        return userRepository
                .findByEmail(
                        authentication.getName()
                )
                .orElseThrow(
                        () -> new AccessDeniedException(
                                "Authenticated user not found."
                        )
                );
    }

    private void requireSuperAdmin() {

        User user = getCurrentUser();

        if (user.getRole()
                != UserRole.SUPERADMIN) {

            throw new AccessDeniedException(
                    "Only SUPERADMIN can perform this operation."
            );
        }
    }
}