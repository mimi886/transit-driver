package com.driver.portal.dto;

import com.driver.portal.models.Role;
import com.driver.portal.models.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private Boolean success;
    private Status status;
    private String fullname;
    private String phonenumber;
    private String email;
    private Role role;
}
