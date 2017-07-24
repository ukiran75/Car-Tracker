# Car Tracker
Application built on SpringMVC, MongoDB, React.js, Material-UI, Node.js, Tomcat for real time analysis of vehicle sensor data.

## Live Version Running on AWS
You can find the live running version at the below link and it is also a responsive website so try it on your mobile:


[Vehicle Tracker Home Page](http://54.193.51.179:3000/ "Vehicle Tracker Homepage")

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
6. `GET: localhost:8080/api/alerts` : TO get the number of high alerts in the past 2 hours grouped by vehicles vin.


## UI Design
1. Home Page `http://localhost:3000/`
![alt HomePage](https://s3-us-west-1.amazonaws.com/full-stack-projects/Spring/Home+Page.png)

2. All Vehicles `http://localhost:3000/vehicles`
![alt AllVehicles](https://s3-us-west-1.amazonaws.com/full-stack-projects/Spring/All+Vehicle+List.png)

3. Vehicle Info - Signal Readings `http://localhost:3000/vehicles/{VIN_NUMBER}`
![alt AllVehicles](https://s3-us-west-1.amazonaws.com/full-stack-projects/Spring/Vehicle+Info+-+Readings.png)

4. Vehicle Info - Vehicle Map `http://localhost:3000/vehicles/{VIN_NUMBER}`
![alt AllVehicles](https://s3-us-west-1.amazonaws.com/full-stack-projects/Spring/Vehicle+Info+-+Map.png)

5. Vehicle Info - Vehicle Alerts  `http://localhost:3000/vehicles/{VIN_NUMBER}`
![alt AllVehicles](https://s3-us-west-1.amazonaws.com/full-stack-projects/Spring/Vehicle+Info+-+Alerts.png)

6. High Alerts of All Vehicles `http://localhost:3000/AllAlerts`
![alt AllVehicles](https://s3-us-west-1.amazonaws.com/full-stack-projects/Spring/High+Alerts.png)

## Steps to Run:
1. For API run `WAR` file on `Tomcat` server which is created by building the project with `Maven`.
2. For UI to run run the api module using `Node.js` server by `npm start`.

## Mock sensor:
To see how the data format will be.


[http://mocker.egen.io](http://mocker.egen.io)

## Requirements

1. `Spring-webmvc` : For creating a Spring web(RESTful) application.
2. `jackson-databind` : For mapping of entities from/to JSON.
3. `javax.servlet-api` : Interfaces for servlets which are base building blocks of java web application.
4. `morphia` : ORM for mongoDB, Supported by mongoDB.
5. `React.js` : React.js for UI.
6. `Node.js` : Node.Js to run the react app.
7  `Material-UI` : UI library for custom designing.

## Missing Credentials(Please make sure your gmail credialtials updated below(or the App will crash)

1. Missing USERNAME, PASSWORD in `Applicattion.java` config file.

