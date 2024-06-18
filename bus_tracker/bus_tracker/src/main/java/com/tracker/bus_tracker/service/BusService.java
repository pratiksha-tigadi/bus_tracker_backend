package com.tracker.bus_tracker.service;

import com.tracker.bus_tracker.dto.BusWithEstimatedTimeDTO;
import com.tracker.bus_tracker.entity.Buses;
import com.tracker.bus_tracker.entity.StopDetail;
import com.tracker.bus_tracker.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    @Autowired
    private StopDetailService stopDetailService;

    private final Random random = new Random();

//    public List<Buses> getBusesWithSourceCodeLessThan(String sourceCode) {
//        return busRepository.findBySourceCodeLessThan(sourceCode);
//    }
    public List<BusWithEstimatedTimeDTO> getBusesWithSourceAndDestination(String sourceCode, String destinationCode) {
        List<Buses> buses;
        if (sourceCode.compareTo(destinationCode) >= 0) {
            buses = busRepository.findBySourceCodeGreaterThan(sourceCode);
        } else {
            buses = busRepository.findBySourceCodeLessThan(sourceCode);
        }
        return calculateEstimatedTime(buses, sourceCode, destinationCode).stream().limit(6).collect(Collectors.toList());
    }

    private List<BusWithEstimatedTimeDTO> calculateEstimatedTime(List<Buses> buses, String userSourceCode, String userDestinationCode) {
        List<BusWithEstimatedTimeDTO> updatedBuses = new ArrayList<>();

        for (Buses bus : buses) {
            Optional<StopDetail> busStopDetailOpt = stopDetailService.getStopDetailByStopCode(bus.getSourceCode());
            Optional<StopDetail> userSourceStopDetailOpt = stopDetailService.getStopDetailByStopCode(userSourceCode);
            Optional<StopDetail> userDestinationStopDetailOpt = stopDetailService.getStopDetailByStopCode(userDestinationCode);

            if (busStopDetailOpt.isPresent() && userSourceStopDetailOpt.isPresent() && userDestinationStopDetailOpt.isPresent()) {
                StopDetail busStopDetail = busStopDetailOpt.get();
                StopDetail userSourceStopDetail = userSourceStopDetailOpt.get();
                StopDetail userDestinationStopDetail = userDestinationStopDetailOpt.get();

                int distance = Math.abs(busStopDetail.getDistanceFromStart() - userSourceStopDetail.getDistanceFromStart());
                int trafficSigns = busStopDetail.getTrafficSigns().split(",").length; // Assuming traffic signs are comma-separated

                double time1 = distance / 60.0; // distance in kilometers divided by average speed (60 km/h)
                double time2 = trafficSigns * 30.0 / 60.0; // traffic signs multiplied by 30 seconds each, converted to minutes

                double randomAdjustment = random.nextDouble() * 0.15; // random number between 0.0 and 0.15
                double estimatedTime = time1 + time2 + randomAdjustment;
                int capacity_filled = random.nextInt(10,55);
                BusWithEstimatedTimeDTO busDetailDTO = new BusWithEstimatedTimeDTO(bus, estimatedTime,capacity_filled);
                updatedBuses.add(busDetailDTO);
            }
        }

        updatedBuses.sort(Comparator.comparingDouble(BusWithEstimatedTimeDTO::getEstimatedTime)); // Optional: Sort by estimated time
        return updatedBuses;
    }
}
