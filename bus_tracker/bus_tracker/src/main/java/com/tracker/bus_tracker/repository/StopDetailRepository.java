package com.tracker.bus_tracker.repository;

import com.tracker.bus_tracker.entity.StopDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StopDetailRepository extends MongoRepository<StopDetail, String> {
    Optional<StopDetail> findByStopCode(String stopCode);
}
