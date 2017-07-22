package io.github.ukiran75.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Entity to store the grouped(count) alerts of a vehicle
 */
@Entity("VehicleAlerts")
public class VehicleAlerts {
    @Id
    String id;
    int numberOfAlerts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumberOfAlerts() {
        return numberOfAlerts;
    }

    public void setNumberOfAlerts(int numberOfAlerts) {
        this.numberOfAlerts = numberOfAlerts;
    }


}
