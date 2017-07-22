package io.github.ukiran75.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ukiran75.entity.Alert;
import io.github.ukiran75.entity.Reading;
import io.github.ukiran75.entity.Tires;
import io.github.ukiran75.entity.Vehicle;
import io.github.ukiran75.exception.BadRequestException;
import io.github.ukiran75.exception.ResourceNotFoundException;
import io.github.ukiran75.mailService.MailService;
import io.github.ukiran75.repository.ReadingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
    @Autowired
    MailService mailService;

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
     *
     * @param vin
     * @return string containing readings of vehicle
     */
    public String getReadingsofVehicle(String vin) {
        List<Reading> readings = readingsRepository.getReadingsofVehicle(vin);
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
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        String date = df.format(new Date());
        Tires tires = reading.getTires();
        int[] tirePressures = {tires.getFrontLeft(), tires.getFrontRight(), tires.getRearLeft(), tires.getRearRight()};
        if (reading.getEngineRpm() > vehicle.getRedlineRpm()) {
            Alert alert = new Alert();
            alert.setVin(vehicle.getVin());
            alert.setTimeStamp(date);
            alert.setAlertReason("High RPM");
            alert.setAlertType("HIGH");
            mailService.sendEmail(alert);
            alertsService.insertAlert(alert);
        }
        if (reading.getFuelVolume() < (0.1 * vehicle.getMaxFuelVolume())) {
            Alert alert = new Alert();
            alert.setVin(vehicle.getVin());
            alert.setTimeStamp(date);
            alert.setAlertType("MEDIUM");
            alert.setAlertReason("Low Fuel Volume");
            alertsService.insertAlert(alert);
        }
        if (reading.isEngineCoolantLow() || reading.isCheckEngineLightOn()) {
            Alert alert = new Alert();
            alert.setVin(vehicle.getVin());
            alert.setTimeStamp(date);
            alert.setAlertType("LOW");
            if (reading.isEngineCoolantLow())
                alert.setAlertReason("Engine Coolant Low");
            else
                alert.setAlertReason("Check Engine Light On");
            alertsService.insertAlert(alert);
        }
        for (int pressure : tirePressures) {
            if (pressure < 32 || pressure > 36) {
                Alert alert = new Alert();
                alert.setVin(vehicle.getVin());
                alert.setTimeStamp(date);
                alert.setAlertType("LOW");
                if (pressure < 32)
                    alert.setAlertReason("Low Tire Pressure");
                else
                    alert.setAlertReason("High Tire Pressure");
                alertsService.insertAlert(alert);
                break;
            }
        }

    }
}

