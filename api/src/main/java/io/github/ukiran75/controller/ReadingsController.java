package io.github.ukiran75.controller;

import io.github.ukiran75.service.ReadingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Class to create mappings for Readings Enity.
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/readings")
public class ReadingsController {

    @Autowired
    ReadingsService readingsService;


    //Mapping for post readings of vehicles
    @RequestMapping(method = RequestMethod.POST)
    void insertReadings(@RequestBody String readings) {
        readingsService.insertReadings(readings);
    }

    //Mapping for getting readings of a particular vehicle
    @RequestMapping(method = RequestMethod.GET, path = "/{vin}")
    String getReadingsofVehicle(@PathVariable("vin") String vin){
        return readingsService.getReadingsofVehicle(vin);
    }
}
