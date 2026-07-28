package com.water.backend.service.impl;

import com.water.backend.dto.request.ResidentRequest;
import com.water.backend.dto.response.ResidentResponse;
import com.water.backend.entity.Community;
import com.water.backend.entity.Resident;
import com.water.backend.exception.ResourceAlreadyExistsException;
import com.water.backend.mapper.ResidentMapper;
import com.water.backend.repository.CommunityRepository;
import com.water.backend.repository.ResidentRepository;
import com.water.backend.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;
    private final CommunityRepository communityRepository;

    @Override
    public ResidentResponse addResident(ResidentRequest request) {

        if (residentRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Resident already exists.");
        }

        Community community = communityRepository.findById(request.getCommunityId())
                .orElseThrow(() -> new RuntimeException("Community not found."));

        Resident resident = ResidentMapper.toEntity(request, community);

        Resident saved = residentRepository.save(resident);

        return ResidentMapper.toResponse(saved);
    }

    @Override
    public List<ResidentResponse> getAllResidents() {

        return residentRepository.findAll()
                .stream()
                .map(ResidentMapper::toResponse)
                .toList();
    }

    @Override
    public ResidentResponse getResidentById(Long id) {

        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found."));

        return ResidentMapper.toResponse(resident);
    }

    @Override
    public List<ResidentResponse> getResidentsByCommunity(Long communityId) {

        return residentRepository.findByCommunityId(communityId)
                .stream()
                .map(ResidentMapper::toResponse)
                .toList();
    }

    @Override
    public ResidentResponse inviteResident(Long id) {

        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found."));

        resident.setInvited(true);

        return ResidentMapper.toResponse(residentRepository.save(resident));
    }

    @Override
    public void deleteResident(Long id) {

        residentRepository.deleteById(id);
    }
}