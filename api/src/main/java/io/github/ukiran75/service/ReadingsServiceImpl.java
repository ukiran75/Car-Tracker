package io.github.ukiran75.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.regexp.internal.RE;
import io.github.ukiran75.entity.Alert;
import io.github.ukiran75.entity.Reading;
import io.github.ukiran75.entity.Tires;
import io.github.ukiran75.entity.Vehicle;
import io.github.ukiran75.exception.BadRequestException;
import io.github.ukiran75.exception.ResourceNotFoundException;
import io.github.ukiran75.repository.ReadingsRepository;
import io.github.ukiran75.repository.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class Implementing the ReadingsService Interface.
 */
@Service
public class ReadingsServiceImpl implements ReadingsService {


    @Autowired
    ReadingsRepository readingsRepository;
    @Autowired
    VehiclesService vehiclesService;
    @Autowired
    AlertsService alertsService;

    /**
     * Method to insert the Readings data data and creating alerts
     * This method will call the insertReadings method in ReadingsRepository
     * Which has access to database.
     *
     * @param readings
     */
    public void insertReadings(String readings) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Reading reading = mapper.readValue(readings, Reading.class);
            Vehicle vehicle = vehiclesService.getOneVehicle(reading.getVin());
            //Insert the readings only if the corresponding vehicle is present
            if (vehicle != null) {
                //check if any alerts have to be inserted
                checkAlerts(reading, vehicle);
                readingsRepository.insertReadings(reading);
            }

        } catch (IOException e) {
            throw new BadRequestException("The Readings data is not in a valid format");
        }


    }

    /**
     * Method to get all Readings oa particular vehicle
     * @param vin
     * @return  string containing readings of vehicle
     */
    public String getReadingsofVehicle(String vin) {
        List<Reading> readings = readingsRepository.getReadingsofVehicle(vin);
        if(readings.isEmpty())
        {
            throw new ResourceNotFoundException("No readings uploaded yet for the vehicle with vin: "+vin);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(readings);
        } catch (JsonProcessingException e) {
            throw new ResourceNotFoundException("Readings in database are not in valid format");
        }
    }

    /**
     * Method to check if alerts have to be created and if alerts has to
     * be created for the reading call AlertsService to insert the alert.
     *
     * @param reading
     * @param vehicle
     */
    @Async
    private void checkAlerts(Reading reading, Vehicle vehicle) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Tires tires = reading.getTires();
        int[] tirePressures = {tires.getFrontLeft(), tires.getFrontRight(), tires.getRearLeft(), tires.getRearRight()};
        if (reading.getEngineRpm() > vehicle.getRedlineRpm()) {
            Alert alert = new Alert();
            alert.setVin(vehicle.getVin());
            alert.setTimeStamp(date);
            alert.setAlertType("HIGH");
            alertsService.insertAlert(alert);
        }
        if (reading.getFuelVolume() < (0.1 * vehicle.getMaxFuelVolume())) {
            Alert alert = new Alert();
            alert.setVin(vehicle.getVin());
            alert.setTimeStamp(date);
            alert.setAlertType("MEDIUM");
            alertsService.insertAlert(alert);
        }
        if (reading.isEngineCoolantLow() || reading.isCheckEngineLightOn()) {
            Alert alert = new Alert();
            alert.setVin(vehicle.getVin());
            alert.setTimeStamp(date);
            alert.setAlertType("LOW");
            alertsService.insertAlert(alert);
        }
        for (int pressure : tirePressures) {
            if (pressure < 32 || pressure > 36) {
                Alert alert = new Alert();
                alert.setVin(vehicle.getVin());
                alert.setTimeStamp(date);
                alert.setAlertType("LOW");
                alertsService.insertAlert(alert);
                break;
            }
        }

    }
}

