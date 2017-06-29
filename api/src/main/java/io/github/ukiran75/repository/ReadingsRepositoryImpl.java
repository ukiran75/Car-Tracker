package io.github.ukiran75.repository;

import io.github.ukiran75.MongoDBHelper;
import io.github.ukiran75.entity.Reading;
import org.mongodb.morphia.Datastore;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class Implementing the ReadingsRepository
 */
@Repository
public class ReadingsRepositoryImpl implements ReadingsRepository {


    /**
     * Method to insert reading into the Readings Collection
     *
     * @param reading
     */
    public void insertReadings(Reading reading) {
        Datastore datastore = MongoDBHelper.INSTANCE.getDatastore();
        datastore.save(reading);

    }

    /**
     * Method to get all readings of a particular vehicle
     *
     * @param vin
     */
    public List<Reading> getReadingsofVehicle(String vin) {
        Datastore datastore = MongoDBHelper.INSTANCE.getDatastore();
        List<Reading> readings = datastore.find(Reading.class).filter("vin", vin).asList();
        return readings;
    }
}
