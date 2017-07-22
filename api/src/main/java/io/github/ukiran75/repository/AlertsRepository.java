package io.github.ukiran75.repository;

import io.github.ukiran75.entity.Alert;
import io.github.ukiran75.entity.VehicleAlerts;

import java.util.Iterator;
import java.util.List;


public interface AlertsRepository {
    void insertAlert(Alert alert);
    Iterator<VehicleAlerts> getAllAlerts();
    List<Alert> getAlertsofVehicle(String vin);
}
