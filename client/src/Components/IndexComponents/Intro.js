/**
 * Created by uday on 7/15/2017.
 */
import React, {Component} from 'react';
import './Intro.css'
import {Card, CardMedia, CardText, CardTitle} from 'material-ui/Card';
import {List, ListItem} from 'material-ui/List'

const IntroText = "Our Project Vehicle Tracker is a real time dash board for" +
    " veicles, where we can get the details of the vehicle and  visualize the " +
    "data of the vehicle. The Actions we can perform are:";

class Intro extends Component {
    render() {
        return (
            <div className="container">
                <div className="IntroImage">
                    <Card>
                        <CardMedia overlay={<CardTitle title="Vehicle Tracker"/>}>
                            <img src='res/Analytics.png' alt={"Analytics"}/>
                        </CardMedia>
                    </Card>
                </div>
                <div className="IntroText">
                    <Card>
                        <CardText>
                            {IntroText}
                        </CardText>
                        <List>
                            <ListItem disabled={true} primaryText="List All Vehicles"
                                      secondaryText="Details of Individual vehicle"/>
                            <ListItem disabled={true} primaryText="Time Series Graph"
                                      secondaryText="RPM,Speed etc.., over time"/>
                            <ListItem disabled={true} primaryText="Mapping Vehicle Locations"
                                      secondaryText="Showing Vehicle recent locations on map"/>
                            <ListItem disabled={true} primaryText="Alerts of a Vehicle"
                                      secondaryText="Showing Individual Historical Alerts of a vehicle"/>
                            <ListItem disabled={true} primaryText="High Alerts of vehicles"
                                      secondaryText="Number of high alerts for each vehicle"/>
                            <ListItem disabled={true} primaryText="E-mail User"
                                      secondaryText="Mailing User when a High Alert is triggered"/>
                        </List>
                    </Card>
                </div>
            </div>);
    }
}

export default Intro