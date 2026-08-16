package com.water.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityAdminRegistrationRequest {

    /*
     * Community Admin details
     */

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    private String password;


    /*
     * Community details
     */

    @NotBlank(message = "Community name is required")
    private String communityName;

    @Email(message = "Invalid community email")
    @NotBlank(message = "Community email is required")
    private String communityEmail;

    @NotBlank(message = "Community phone is required")
    private String communityPhone;

    @NotBlank(message = "Community address is required")
    private String communityAddress;
}