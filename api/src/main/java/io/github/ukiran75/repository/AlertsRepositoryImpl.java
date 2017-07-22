package io.github.ukiran75.repository;

import io.github.ukiran75.MongoDBHelper;
import io.github.ukiran75.entity.Alert;
import io.github.ukiran75.entity.VehicleAlerts;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.aggregation.Accumulator;
import org.mongodb.morphia.aggregation.Group;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

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
     * Method to use morphia to aggregate the High alerts which are
     * triggered in the last 2 hours from the alert table
     * according to vin number and returning them along with count
     *
     * @return Iterator which contains the Aggregated results
     */
    public Iterator<VehicleAlerts> getAllAlerts() {
        Datastore datastore = MongoDBHelper.INSTANCE.getDatastore();
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        String date = df.format(new java.util.Date(System.currentTimeMillis() - (2 * 60 * 60 * 1000)));
        Iterator<VehicleAlerts> vehicleAlerts = datastore.createAggregation(Alert.class)
                .match(datastore.getQueryFactory().createQuery(datastore)
                        .field("alertType").equal("HIGH"))
                .match(datastore.getQueryFactory().createQuery(datastore)
                        .field("timeStamp").greaterThan(date))
                .group("vin", Group.grouping("numberOfAlerts", Accumulator.accumulator("$sum", 1)))
                .aggregate(VehicleAlerts.class);
        return vehicleAlerts;

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
