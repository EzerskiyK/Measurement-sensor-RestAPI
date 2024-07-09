package com.restAPI.measurementSensor.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "значение не должно быть пустым")
    @Size(min = 2, max = 30, message = "Название сенсора должно быть от 2 до 30 символов")
    @Column(name = "sensor_name")
    private String name;

    public SensorDTO(String name) {
        this.name = name;
    }

    public SensorDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
