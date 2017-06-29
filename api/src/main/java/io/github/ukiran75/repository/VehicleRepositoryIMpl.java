package io.github.ukiran75.repository;

import io.github.ukiran75.MongoDBHelper;
import io.github.ukiran75.entity.Vehicle;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class Implementing the VehicleRepository
 */
@Repository
public class VehicleRepositoryIMpl implements VehiclesRepository {


    /**
     * Method to insert vehicle into Vehicle Collections
     *
     * @param vehicle
     */
    public void upsertVehicles(Vehicle vehicle) {
        Datastore datastore = MongoDBHelper.INSTANCE.getDatastore();
        datastore.save(vehicle);

    }

    /**
     * Method to get a vehicle document based on vin number
     *
     * @param vin
     * @return Vehicle
     */
    public Vehicle getVehicle(String vin) {
        Datastore datastore = MongoDBHelper.INSTANCE.getDatastore();
        final Query<Vehicle> query = datastore.createQuery(Vehicle.class).field("vin").equal(vin);
        final Vehicle vehicle = query.get();
        return vehicle;
    }

    /**
     * Method to retrive details of all vehicles
     *
     * @return LIst<Vehicles>
     */
    public List<Vehicle> getAllVehicles() {
        Datastore datastore = MongoDBHelper.INSTANCE.getDatastore();
        List<Vehicle> vehicles = datastore.find(Vehicle.class).asList();
        return vehicles;
    }
}
