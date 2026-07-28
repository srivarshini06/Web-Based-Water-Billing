package com.water.backend.dto.response;

import com.water.backend.enums.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long userId;

    private String fullName;

    private String email;

    private String phoneNumber;

    private UserRole role;
}