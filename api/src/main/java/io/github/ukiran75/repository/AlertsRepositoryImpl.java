package io.github.ukiran75.repository;

import io.github.ukiran75.MongoDBHelper;
import io.github.ukiran75.entity.Alert;
import org.mongodb.morphia.Datastore;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class Implementing the AlertsRepository.
 */
@Repository
public class AlertsRepositoryImpl implements AlertsRepository {


    /**
     * Method to insert alert into alert collection.
     *
     * @param alert
     */
    public void insertAlert(Alert alert) {
        Datastore datastore = MongoDBHelper.INSTANCE.getDatastore();
        datastore.save(alert);


    }

    /**
     * Method to get all documents(Alerts) of a vehicle.
     *
     * @param vin
     * @return list of alerts
     */
    public List<Alert> getAlertsofVehicle(String vin) {
        Datastore datastore = MongoDBHelper.INSTANCE.getDatastore();
        List<Alert> alerts = datastore.find(Alert.class).filter("vin", vin).asList();
        return alerts;
    }
}
