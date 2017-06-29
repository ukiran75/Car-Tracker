package io.github.ukiran75.repository;

import io.github.ukiran75.entity.Reading;

import java.util.List;

public interface ReadingsRepository {
    void insertReadings(Reading reading);
    List<Reading> getReadingsofVehicle(String vin);
}
