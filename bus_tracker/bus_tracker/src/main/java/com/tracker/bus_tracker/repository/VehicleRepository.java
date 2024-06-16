package com.tracker.bus_tracker.repository;


import java.util.Optional;
import com.tracker.bus_tracker.entity.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, Long> {
    Optional<Vehicle> findByLicensePlate(String licensePlate);
}
