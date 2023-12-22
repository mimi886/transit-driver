package com.driver.portal.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.driver.portal.dto.VehicleRequest;
import com.driver.portal.dto.VehicleResponse;
import com.driver.portal.models.User;
import com.driver.portal.models.Vehicle;
import com.driver.portal.repository.UserRepository;
import com.driver.portal.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private final UserRepository userRepository;


    public void createVehicle(VehicleRequest request) {
        
       Optional<User> optUser= userRepository.findByEmail(request.getEmail());

       if(optUser.isPresent()){
            User user = optUser.get();
            Vehicle vehicle=Vehicle.builder()
                            .regnum(request.getRegnum())
                            .type(request.getType())
                            .status(request.getStatus())
                            .make(request.getMake())
                            .model(request.getModel())
                            .createdat(new Date())
                            .capacity(request.getCapacity())
                            .color(request.getColor())
                            .licence(request.getLicence())
                            .user(user)
                            .build();

            vehicleRepository.save(vehicle);
            log.info("Vehicle is saved. Registration Number:"+request.getRegnum());
       }

       

    }

    public List<VehicleResponse> getAllVehicles() {
        List<Vehicle> vehicles=vehicleRepository.findAll();
        return vehicles.stream().map(this::mapToVechileResponse).toList();
    }
    
    private VehicleResponse mapToVechileResponse(Vehicle vehicle){
        return VehicleResponse.builder()
                        .id(vehicle.getId())
                        .regnum(vehicle.getRegnum())
                        .type(vehicle.getType())
                        .status(vehicle.getStatus())
                        .make(vehicle.getMake())
                        .model(vehicle.getModel())
                        .createdat(vehicle.getCreatedat())
                        .capacity(vehicle.getCapacity())
                        .color(vehicle.getColor())
                        .build();
    }

   

}
