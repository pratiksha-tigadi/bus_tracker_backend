package com.tracker.bus_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tracker.bus_tracker.entity.Vehicle;
import com.tracker.bus_tracker.service.VehicleService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }
    @GetMapping("/vbl")
    public List<Vehicle> getVehicleByLicensePlate(@RequestParam  String licensePlate) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleByLicensePlate(licensePlate);
        return vehicle.map(Collections::singletonList).orElseGet(List::of);
    }




}
