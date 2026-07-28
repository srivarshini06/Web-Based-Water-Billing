package com.water.backend.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResidentResponse {

    private Long id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String building;

    private String block;

    private String flatNumber;

    private Boolean invited;

    private Boolean registered;

    private Long communityId;
}