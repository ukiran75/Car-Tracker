package io.github.ukiran75;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.Arrays;

/**
 * A single ton class to create a Datastore object which will be
 * used by all the repositories for persisting entities, querying from database.
 */
public enum MongoDBHelper {
    INSTANCE;

    private final String SERVER_URL = "localhost";
    private final int SERVER_PORT = 27013;
    private final String USERNAME = "uday";
    private final String PASSWORD = "password";
    private final String DATABASE_NAME = "VehicleTracker";
    private DB db;
    private Datastore datastore;


    private MongoDBHelper() {

        final Morphia morphia = new Morphia();
        morphia.mapPackage("io.github.ukiran75.entity");
        MongoCredential credential = MongoCredential.createCredential(USERNAME, DATABASE_NAME, PASSWORD.toCharArray());
        datastore = morphia.createDatastore(new MongoClient(new ServerAddress(), Arrays.asList(credential)), DATABASE_NAME);

    }

    public Datastore getDatastore() {
        return this.datastore;
    }
}