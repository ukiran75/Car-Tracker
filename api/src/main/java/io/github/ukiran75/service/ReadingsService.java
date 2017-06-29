package io.github.ukiran75.service;

public interface ReadingsService {
    void insertReadings(String readings);
    String getReadingsofVehicle(String vin);
}
