package com.driver.portal.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.driver.portal.dto.VehicleRequest;
import com.driver.portal.dto.VehicleResponse;
import com.driver.portal.services.VehicleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createVehicle(@RequestBody VehicleRequest request){
        vehicleService.createVehicle(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleResponse> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }
    
}
