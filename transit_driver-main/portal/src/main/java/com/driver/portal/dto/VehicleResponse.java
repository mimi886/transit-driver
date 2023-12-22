package com.driver.portal.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.driver.portal.models.Status;
import com.driver.portal.models.VecType;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {
   private long id;
    private String regnum;
    private VecType type;
    private Status status;
    private String make;
    private String model;
    private String color;
    private Date createdat;
    private int capacity;  
}
