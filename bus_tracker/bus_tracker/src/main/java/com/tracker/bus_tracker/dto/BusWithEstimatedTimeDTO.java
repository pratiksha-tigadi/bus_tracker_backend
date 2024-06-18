package com.tracker.bus_tracker.dto;

import com.tracker.bus_tracker.entity.Buses;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusWithEstimatedTimeDTO {
    private Buses bus;
    private double estimatedTime;
    private  int  capacity_filled;

    // Constructors, getters, and setters
    public BusWithEstimatedTimeDTO() {}

    public BusWithEstimatedTimeDTO(Buses bus, double estimatedTime, int capacity_filled) {
        this.bus = bus;
        this.estimatedTime = estimatedTime;
        this.capacity_filled =capacity_filled;
    }

}
