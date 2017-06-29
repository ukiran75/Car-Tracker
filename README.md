# Car Tracker
Application built on SpringMVC, MongoDB for real time analysis of vehicle sensor data.

## Collections in the Database
1. **Vehicles :** Individual  details of a Vehicle.
2. **Readings :** Individual details of a reading coming from a vehicle sensor.
3. **Alerts :** Individual alerts created from the readings.

## API End Points
1. `PUT: localhost:8080/api/vehicles`  : To put(upsert) the details of a vehicle to the database.
2. `POST: localhost:8080/api/readings` : To post the readings to the database.
3. `GET: localhost:8080/api/vehicles`  : To get all the vehicles details in the database.
4. `GET: localhost:8080/api/readings/{vin}` : To get the readings history of a vehicle.
5. `GET: localhost:8080/api/alerts/{vin}` : To get all the alerts of a vehicle.
6. `<TO-DO> GET: localhost:8080/api/alerts` : TO get the number of high alerts in the past 2 hours grouped by vehicles vin.



## mock sensor:
To see how the data format will be.


[http://mocker.egen.io](http://mocker.egen.io)

## Requirements

1. `Spring-webmvc` : For creating a Spring web(RESTful) application.
2. `jackson-databind` : For mapping of entities from/to JSON. 
3. `javax.servlet-api` : Interfaces for servlets which are base building blocks of java web application.
4. `morphia` : ORM for mongoDB, Supported by mongoDB.

