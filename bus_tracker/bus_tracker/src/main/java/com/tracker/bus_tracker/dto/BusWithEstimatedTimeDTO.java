package com.tracker.bus_tracker.dto;

import com.tracker.bus_tracker.entity.Buses;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusWithEstimatedTimeDTO {
    private Buses bus;
    private double estimatedTime;

    // Constructors, getters, and setters
    public BusWithEstimatedTimeDTO() {}

    public BusWithEstimatedTimeDTO(Buses bus, double estimatedTime) {
        this.bus = bus;
        this.estimatedTime = estimatedTime;
    }

}
