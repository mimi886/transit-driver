package com.driver.portal.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewRequests {
    private String picklocation;
    private String destlocations;
    private String regnum;
    private String  driver;

}
