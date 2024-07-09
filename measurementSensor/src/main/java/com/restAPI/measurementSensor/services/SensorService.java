package com.restAPI.measurementSensor.services;

import com.restAPI.measurementSensor.model.Sensor;
import com.restAPI.measurementSensor.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> findByName(String name) {
       return sensorRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        enrich(sensor);
        sensorRepository.save(sensor);
    }

    public void enrich(Sensor sensor){
        sensor.setCreated_at(LocalDateTime.now());
    }
}
