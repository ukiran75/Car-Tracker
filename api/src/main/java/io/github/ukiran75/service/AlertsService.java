package io.github.ukiran75.service;

import io.github.ukiran75.entity.Alert;


public interface AlertsService {

    void insertAlert(Alert alert);
    public String getAllAlertsofVehicles();
    String getAlertsofVehicle(String vin);
}
