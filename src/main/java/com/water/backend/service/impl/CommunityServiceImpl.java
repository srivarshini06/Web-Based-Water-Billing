package com.water.backend.service.impl;

import com.water.backend.dto.request.CommunityRequest;
import com.water.backend.dto.response.CommunityResponse;
import com.water.backend.entity.Community;
import com.water.backend.enums.CommunityStatus;
import com.water.backend.exception.ResourceAlreadyExistsException;
import com.water.backend.mapper.CommunityMapper;
import com.water.backend.repository.CommunityRepository;
import com.water.backend.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;

    @Override
    public CommunityResponse registerCommunity(CommunityRequest request) {

        if (communityRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already registered.");
        }

        Community community = CommunityMapper.toEntity(request);

        community.setStatus(CommunityStatus.PENDING);
        community.setCreatedAt(LocalDateTime.now());

        Community saved = communityRepository.save(community);

        return CommunityMapper.toResponse(saved);
    }

    @Override
    public List<CommunityResponse> getAllCommunities() {

        return communityRepository.findAll()
                .stream()
                .map(CommunityMapper::toResponse)
                .toList();
    }

    @Override
    public CommunityResponse getCommunityById(Long id) {

        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found."));

        return CommunityMapper.toResponse(community);
    }

    @Override
    public CommunityResponse approveCommunity(Long id) {

        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found."));

        community.setStatus(CommunityStatus.APPROVED);
        community.setApprovedAt(LocalDateTime.now());

        return CommunityMapper.toResponse(
                communityRepository.save(community));
    }
    @Override
    public List<CommunityResponse> getPendingCommunities() {

        return communityRepository
                .findByStatus(CommunityStatus.PENDING)
                .stream()
                .map(CommunityMapper::toResponse)
                .toList();
    }

    @Override
    public CommunityResponse rejectCommunity(Long id) {

        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found."));

        community.setStatus(CommunityStatus.REJECTED);

        return CommunityMapper.toResponse(
                communityRepository.save(community));
    }
}