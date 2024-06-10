package com.tracker.bus_tracker.controller;

import com.tracker.bus_tracker.dto.BusWithEstimatedTimeDTO;
import com.tracker.bus_tracker.entity.Buses;
import com.tracker.bus_tracker.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusController {
    @Autowired
    private BusService busService;

//    @GetMapping("/buses")
//    public List<Buses> getBuses(@RequestParam String sourceCode) {
//        return busService.getBusesWithSourceCodeLessThan(sourceCode);
//    }

    @GetMapping("/buses")
    public List<BusWithEstimatedTimeDTO> getBusesWithEstimatedTime(
            @RequestParam String sourceCode,
            @RequestParam String destinationCode
    ) {
        return busService.getBusesWithSourceAndDestination(sourceCode, destinationCode);
    }
}
