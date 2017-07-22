package io.github.ukiran75.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ukiran75.entity.Alert;
import io.github.ukiran75.entity.VehicleAlerts;
import io.github.ukiran75.exception.ResourceNotFoundException;
import io.github.ukiran75.repository.AlertsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class implementing the AlertsService
 */
@Service
public class AlertsServiceImpl implements AlertsService {

    @Autowired
    AlertsRepository alertsRepository;

    /**
     * Method to insert the alert which will call the
     * insertalert method in alertsRepository.
     * @param alert
     */
    public void insertAlert(Alert alert) {
        alertsRepository.insertAlert(alert);

    }


    /**
     * Method to get all alerts of all the vehicles along
     * with the count of alerts for each vehicle
     * @return Json String of all alerts
     */
    public String getAllAlertsofVehicles(){
        Iterator<VehicleAlerts> alerts = alertsRepository.getAllAlerts();
        List<VehicleAlerts> allVehicleAlerts = new ArrayList<VehicleAlerts>();
        while(alerts.hasNext()) {
            VehicleAlerts alert = alerts.next();
            allVehicleAlerts.add(alert);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(allVehicleAlerts);
        } catch (JsonProcessingException e) {
            throw new ResourceNotFoundException("Alerts data is not in valid format");
        }

    }

    /**
     * Method to get all the alerts of a vehicle
     *
     * @param vin
     * @return String of alerts
     */
    public String getAlertsofVehicle(String vin) {
        List<Alert> alerts = alertsRepository.getAlertsofVehicle(vin);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(alerts);
        } catch (JsonProcessingException e) {
            throw new ResourceNotFoundException("Alerts data is not in valid format");
        }
    }
}
