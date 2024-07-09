package com.restAPI.measurementSensor.services;

import com.restAPI.measurementSensor.model.Measurement;
import com.restAPI.measurementSensor.repositories.MeasurementRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepositories measurementRepositories;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepositories measurementRepositories, SensorService sensorService) {
        this.measurementRepositories = measurementRepositories;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll() {
        return measurementRepositories.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
        enrich(measurement);
        measurementRepositories.save(measurement);
    }

    public int numberOfRainDays(){
        return measurementRepositories.countByRainingTrue();
    }

    public void enrich(Measurement measurement){
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName())
                .orElseThrow(() -> new IllegalArgumentException("Sensor not found")));
        measurement.setCreated_at(LocalDateTime.now());

    }
}
