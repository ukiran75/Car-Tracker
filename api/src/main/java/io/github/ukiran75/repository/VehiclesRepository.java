package io.github.ukiran75.repository;

import io.github.ukiran75.entity.Vehicle;

import java.util.List;

public interface VehiclesRepository {
    void upsertVehicles(Vehicle vehicle);

    Vehicle getVehicle(String vin);

    List<Vehicle> getAllVehicles();
}
