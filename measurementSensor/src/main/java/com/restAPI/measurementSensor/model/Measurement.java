package com.restAPI.measurementSensor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measurement_id")
    private int id;

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
    private Sensor sensor;

    @NotNull(message = "значение не должно быть пустым")
    @Column(name = "created_at")
    private LocalDateTime created_at;

    public Measurement() {
    }

    public Measurement(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
