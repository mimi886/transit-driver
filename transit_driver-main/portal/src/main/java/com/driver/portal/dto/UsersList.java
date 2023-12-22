package com.driver.portal.dto;

import java.util.Date;

import com.driver.portal.models.Role;
import com.driver.portal.models.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersList {
    private Integer id;
    private String fullname;
    private String email;
    private String password;
    private Role role;
    private Status status;
    private String phonenumber;
    private Date createat;
}
