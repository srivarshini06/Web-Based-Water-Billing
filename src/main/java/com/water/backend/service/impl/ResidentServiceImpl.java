package com.water.backend.service.impl;

import com.water.backend.dto.request.ResidentRequest;
import com.water.backend.dto.response.ResidentResponse;
import com.water.backend.entity.Community;
import com.water.backend.entity.Resident;
import com.water.backend.entity.User;
import com.water.backend.enums.UserRole;
import com.water.backend.exception.ResourceAlreadyExistsException;
import com.water.backend.mapper.ResidentMapper;
import com.water.backend.repository.CommunityRepository;
import com.water.backend.repository.ResidentRepository;
import com.water.backend.repository.UserRepository;
import com.water.backend.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl
        implements ResidentService {

    private final ResidentRepository residentRepository;
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResidentResponse addResident(
            ResidentRequest request) {

        if (residentRepository.existsByEmail(
                request.getEmail())) {

            throw new ResourceAlreadyExistsException(
                    "Resident already exists."
            );
        }

        Community community =
                communityRepository
                        .findById(
                                request.getCommunityId()
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Community not found."
                                )
                        );

        /*
         * Only SUPERADMIN can add residents to any
         * community.
         *
         * COMMUNITY_ADMIN can add residents ONLY
         * to his own community.
         */
        validateCommunityAccess(community);

        /*
         * Do not allow adding residents to a rejected
         * or pending community.
         */
        if (community.getStatus()
                != com.water.backend.enums.CommunityStatus.APPROVED) {

            throw new IllegalStateException(
                    "Residents can only be added to an approved community."
            );
        }

        Resident resident =
                ResidentMapper.toEntity(
                        request,
                        community
                );

        Resident saved =
                residentRepository.save(resident);

        return ResidentMapper.toResponse(saved);
    }

    /*
     * IMPORTANT:
     *
     * Do not expose ALL residents to Community Admin.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ResidentResponse> getAllResidents() {

        User user = getCurrentUser();

        if (user.getRole()
                == UserRole.SUPERADMIN) {

            return residentRepository.findAll()
                    .stream()
                    .map(ResidentMapper::toResponse)
                    .toList();
        }

        if (user.getRole()
                == UserRole.COMMUNITY_ADMIN) {

            Community community =
                    getOwnCommunity(user);

            return residentRepository
                    .findByCommunityId(
                            community.getId()
                    )
                    .stream()
                    .map(ResidentMapper::toResponse)
                    .toList();
        }

        throw new AccessDeniedException(
                "You are not allowed to view residents."
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ResidentResponse getResidentById(
            Long id) {

        Resident resident =
                residentRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Resident not found."
                                )
                        );

        validateCommunityAccess(
                resident.getCommunity()
        );

        return ResidentMapper.toResponse(
                resident
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResidentResponse>
    getResidentsByCommunity(
            Long communityId) {

        Community community =
                communityRepository.findById(
                        communityId
                ).orElseThrow(
                        () -> new RuntimeException(
                                "Community not found."
                        )
                );

        validateCommunityAccess(community);

        return residentRepository
                .findByCommunityId(communityId)
                .stream()
                .map(ResidentMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public ResidentResponse inviteResident(
            Long id) {

        Resident resident =
                residentRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Resident not found."
                                )
                        );

        validateCommunityAccess(
                resident.getCommunity()
        );

        resident.setInvited(true);

        return ResidentMapper.toResponse(
                residentRepository.save(resident)
        );
    }

    @Override
    @Transactional
    public void deleteResident(Long id) {

        Resident resident =
                residentRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Resident not found."
                                )
                        );

        validateCommunityAccess(
                resident.getCommunity()
        );

        residentRepository.delete(resident);
    }

    /*
     * -------------------------------
     * SECURITY HELPERS
     * -------------------------------
     */

    private void validateCommunityAccess(
            Community community) {

        User currentUser =
                getCurrentUser();

        /*
         * SUPERADMIN can access all communities.
         */
        if (currentUser.getRole()
                == UserRole.SUPERADMIN) {

            return;
        }

        /*
         * COMMUNITY_ADMIN can access ONLY
         * his own community.
         */
        if (currentUser.getRole()
                == UserRole.COMMUNITY_ADMIN) {

            Community ownCommunity =
                    getOwnCommunity(currentUser);

            if (!ownCommunity.getId()
                    .equals(community.getId())) {

                throw new AccessDeniedException(
                        "You can access only residents of your own community."
                );
            }

            return;
        }

        throw new AccessDeniedException(
                "You are not allowed to access residents."
        );
    }

    private Community getOwnCommunity(
            User user) {

        return communityRepository
                .findByAdminUserId(
                        user.getUserId()
                )
                .orElseThrow(
                        () -> new AccessDeniedException(
                                "No community is assigned to this admin."
                        )
                );
    }

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()) {

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
                                "User not found."
                        )
                );
    }
}