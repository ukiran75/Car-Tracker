package io.github.ukiran75.service;

import io.github.ukiran75.entity.Vehicle;

public interface VehiclesService {
    void upsertVehicles(String Vehicles);
    String getAllVehicles();
    Vehicle getOneVehicle(String vin);
}
