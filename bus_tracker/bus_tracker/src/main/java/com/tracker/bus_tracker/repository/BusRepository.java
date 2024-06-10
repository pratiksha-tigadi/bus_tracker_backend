package com.tracker.bus_tracker.repository;

import com.tracker.bus_tracker.entity.Buses;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends MongoRepository<Buses, String> {
    List<Buses> findBySourceCodeLessThan(String sourceCode);
    List<Buses> findBySourceCodeGreaterThan(String sourceCode);
}
