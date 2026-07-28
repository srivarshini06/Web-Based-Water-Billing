package com.water.backend.dto.response;

import com.water.backend.enums.CommunityStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityResponse {

    private Long id;

    private String communityName;

    private String ownerName;

    private String email;

    private String phone;

    private String address;

    private CommunityStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime approvedAt;
}