package com.restAPI.measurementSensor.repositories;

import com.restAPI.measurementSensor.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepositories extends JpaRepository<Measurement, Integer> {
    public int countByRainingTrue();
}
