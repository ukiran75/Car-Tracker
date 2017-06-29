package io.github.ukiran75.repository;

import io.github.ukiran75.entity.Alert;

import java.util.List;


public interface AlertsRepository {
    void insertAlert(Alert alert);
    List<Alert> getAlertsofVehicle(String vin);
}
