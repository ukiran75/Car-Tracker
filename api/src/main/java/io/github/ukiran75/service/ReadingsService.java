package io.github.ukiran75.service;

/*
 * Reading Service Interface
 */
public interface ReadingsService {
    void insertReadings(String readings);
    String getReadingsofVehicle(String vin);
}
