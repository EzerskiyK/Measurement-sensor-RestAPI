package com.restAPI.measurementSensor.util;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String msg) {
        super(msg);
    }
}
