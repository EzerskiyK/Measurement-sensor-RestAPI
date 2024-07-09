package com.restAPI.measurementSensor.controllers;

import com.restAPI.measurementSensor.dto.SensorDTO;
import com.restAPI.measurementSensor.model.Sensor;
import com.restAPI.measurementSensor.services.SensorService;
import com.restAPI.measurementSensor.util.SensorErrorResponse;
import com.restAPI.measurementSensor.util.SensorNotCreatedException;
import com.restAPI.measurementSensor.util.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                    BindingResult bindingResult) {
        Sensor sensor = convertTosensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                errors.append(fieldError.getField())
                        .append(" : ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ");
            });
            throw new SensorNotCreatedException(errors.toString());

        }
        sensorService.save(sensor);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e){
        SensorErrorResponse errorResponse = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertTosensor(SensorDTO sensorDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(sensorDTO, Sensor.class);
    }

}
