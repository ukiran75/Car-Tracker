package io.github.ukiran75.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ukiran75.entity.Vehicle;
import io.github.ukiran75.exception.BadRequestException;
import io.github.ukiran75.exception.ResourceNotFoundException;
import io.github.ukiran75.repository.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Class Implementing the VehiclesService Interface
 */
@Service
public class VehiclesServiceImpl implements VehiclesService {


    @Autowired
    VehiclesRepository vehiclesRepository;

    /**
     * Method to upsert(update & create if missing) the vehicles data.
     * This method will call the upsertVehicleMethod in VehicleRepository
     * Which has access to database.
     *
     * @param vehicles
     */
    public void upsertVehicles(String vehicles) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Vehicle[] vehicleArr = mapper.readValue(vehicles, Vehicle[].class);
            for (Vehicle v : vehicleArr) {
                vehiclesRepository.upsertVehicles(v);
            }
        } catch (IOException e) {
            throw new BadRequestException("Tee Vehicles data is not a valid format");
        }


    }

    /**
     * Method to get all the vehicles calling VehicleRepository
     *
     * @return JSON String of vehicles
     */
    public String getAllVehicles() {
        List<Vehicle> vehicles = vehiclesRepository.getAllVehicles();
        if (vehicles.isEmpty()) {
            throw new ResourceNotFoundException("No Vehicles uploaded yet.");
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(vehicles);
        } catch (IOException e) {
            throw new ResourceNotFoundException("Documents in databse are not in valid format");
        }

    }

    /**
     * Method to get a single vehicle details
     *
     * @param vin
     * @return vehicle
     */
    public Vehicle getOneVehicle(String vin) {
        Vehicle vehicle = vehiclesRepository.getVehicle(vin);
        return vehicle;
    }
}
