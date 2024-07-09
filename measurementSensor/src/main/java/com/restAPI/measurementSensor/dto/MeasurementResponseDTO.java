package com.restAPI.measurementSensor.dto;

import java.util.List;

public class MeasurementResponseDTO {
    List<MeasurementDTO> measurements;

    public MeasurementResponseDTO(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
