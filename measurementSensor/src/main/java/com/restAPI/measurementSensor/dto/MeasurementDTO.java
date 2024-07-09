package com.restAPI.measurementSensor.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

public class MeasurementDTO {
    @NotNull(message = "значение не должно быть пустым")
    @Min(value = -100, message = "Значения должны быть от -100 до 100")
    @Max(value = 100, message = "Значения должны быть от -100 до 100")
    @Column(name = "value")
    private Double value;

    @NotNull(message = "значение не должно быть пустым")
    @Column(name = "raining")
    private Boolean raining;


    @ManyToOne()
    @JoinColumn(name = "sensor", referencedColumnName = "sensor_name")
    @NotNull(message = "значение не должно быть пустым")
    private SensorDTO sensor;

    public MeasurementDTO() {
    }

    public MeasurementDTO(double value, boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
