package io.github.ukiran75.controller;

import io.github.ukiran75.service.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Class to create Mappings for Vehicles Entity.
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/vehicles")
public class VehiclesController {

    @Autowired
    VehiclesService vehiclesService;

    @RequestMapping(method = RequestMethod.PUT)
    public void upsertVehicles(@RequestBody String vehicles) {
        vehiclesService.upsertVehicles(vehicles);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllVehicles() {
        return vehiclesService.getAllVehicles();
    }

}
