package com.driver.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailsResponse {
    private String custno;
    private String firstname;
    private String lastname;
    private String email;
    private String mobnum;
    private String hometel;
    private String telephno;
    private String accountnum;
    private String profilename;


    
}
