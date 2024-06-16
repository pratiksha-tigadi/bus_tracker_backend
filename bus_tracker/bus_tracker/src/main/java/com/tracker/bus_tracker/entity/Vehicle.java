package com.tracker.bus_tracker.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
@Setter
@Getter
@Document(collection = "fleets")
public class Vehicle {

    @Id
    private String id;
  @Indexed
    private String status;

    private Double fuelLevel;

    private Integer lastMaintenanceDate;

    private String licensePlate;

}