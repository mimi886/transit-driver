package com.driver.portal.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.portal.dto.NewRequests;
import com.driver.portal.dto.RequestList;
import com.driver.portal.models.Locations;
import com.driver.portal.models.Requests;
import com.driver.portal.models.User;
import com.driver.portal.models.Vehicle;
import com.driver.portal.repository.RequestsRepository;
import com.driver.portal.repository.UserRepository;
import com.driver.portal.repository.VehicleRepository;

@Service
public class RequestsService {

    @Autowired
    RequestsRepository requestsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VehicleRepository vehicleRepository;
 
    public List<RequestList> getRequests() {
        List<Requests> requests= requestsRepository.findAll();
        return requests.stream().map(this::mapToRequest).toList();
    }
    
    private RequestList mapToRequest(Requests requests){
        return RequestList.builder()
                            .id(requests.getId())
                            .picklocation(requests.getPicklocation())
                            .destlocations(requests.getDestlocations())
                            .createdat(requests.getCreatedat())
                            .driver(requests.getUser().getFullname())
                            .regnum(requests.getVehicle().getRegnum())
                            .build();
    }

    public Requests newRequests(NewRequests request) {
        Requests requests =new Requests();
        Optional<User> optDriver= userRepository.findByEmail(request.getDriver());
        Optional<Vehicle> vehilce=vehicleRepository.findByRegnum(request.getRegnum());
        if(optDriver.isPresent() && vehilce.isPresent() ){
            
            requests.setPicklocation(getLocation(request.getPicklocation()));
            requests.setDestlocations(getLocation(request.getDestlocations()));
            requests.setUser(optDriver.get());
            requests.setVehicle(vehilce.get());
            requests.setCreatedat(LocalDateTime.now());

            requestsRepository.save(requests);

        }

         return requests;
    }

    private Locations getLocation(String location){
        switch(location){
            case "Balme library":
                return Locations.BALME;
            case "Night market":
                return Locations.NIGHT;
            case "Law school car park":
                return Locations.LAW;
            case "Central cafeteria":
                return Locations.CENT;
            case "Jones Quartey building":
                return Locations.JONES;
            default:
                return null;
        }
    }
}
