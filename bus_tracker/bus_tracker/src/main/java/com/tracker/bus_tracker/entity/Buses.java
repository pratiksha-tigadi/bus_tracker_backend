package com.tracker.bus_tracker.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "bus_info")
public class Buses {
    // Getters and Setters
    @Id
    private String id;
    @Indexed
    private String licensePlate;
    private String busType;
    private String sourceCode;
    private String dropCode;

}
