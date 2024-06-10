package com.tracker.bus_tracker.service;

import com.tracker.bus_tracker.entity.StopDetail;
import com.tracker.bus_tracker.repository.StopDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StopDetailService {

    @Autowired
    private StopDetailRepository stopDetailRepository;

    public Optional<StopDetail> getStopDetailByStopCode(String stopCode) {
        return stopDetailRepository.findByStopCode(stopCode);
    }
}
