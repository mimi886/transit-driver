package com.driver.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.driver.portal.models.Vehicle;
import java.util.Optional;


public interface VehicleRepository extends JpaRepository<Vehicle,Long>{
    
    Optional<Vehicle> findByRegnum(String regnum);
}
