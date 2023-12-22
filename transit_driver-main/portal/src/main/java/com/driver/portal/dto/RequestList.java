package com.driver.portal.dto;

import java.time.LocalDateTime;

import com.driver.portal.models.Locations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestList {

    private long id;
    private Locations picklocation;
    private Locations destlocations;
    private LocalDateTime createdat;
    private String regnum;
    private String  driver;
}
