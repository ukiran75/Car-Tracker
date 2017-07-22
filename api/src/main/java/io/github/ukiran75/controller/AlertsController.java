package io.github.ukiran75.controller;

import io.github.ukiran75.repository.AlertsRepository;
import io.github.ukiran75.service.AlertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Class for Mappings of Alerts Entity
 */
@CrossOrigin
@RestController
@RequestMapping("/alerts")
public class AlertsController {

    @Autowired
    AlertsService alertsService;

    //Mapping to get alerts grouped by vin
    @RequestMapping(method = RequestMethod.GET)
    public String getAllAlerts(){ return alertsService.getAllAlertsofVehicles();};

    //Mapping for getting alerts of a particular vehicle
    @RequestMapping(method = RequestMethod.GET, path = "/{vin}")
    public String getAlertsofVehicle(@PathVariable String vin){
        return alertsService.getAlertsofVehicle(vin);
    }
}
