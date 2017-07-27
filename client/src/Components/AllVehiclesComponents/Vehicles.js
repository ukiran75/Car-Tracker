/**
 * Created by uday on 7/16/2017.
 */
import React, {Component} from 'react';
import Flag from 'material-ui/svg-icons/content/flag'
import Opacity from 'material-ui/svg-icons/action/opacity'
import Build from 'material-ui/svg-icons/action/build'
import Vpnkey from 'material-ui/svg-icons/communication/vpn-key'
import {List, ListItem} from 'material-ui/List';
import {Card, CardHeader} from 'material-ui/Card/'
import {blue500, green500, red500, white, yellow900} from 'material-ui/styles/colors'
import './Vehicles.css'

import Link from 'react-router-dom/Link'
import Paper from 'material-ui/Paper'
import axios from 'axios'
import Snackbar from 'material-ui/Snackbar'
import RaisedButton from 'material-ui/RaisedButton';


const styles = {
    tile: {
        backgroundColor: '#3F51B5',
    },
    list: {
        borderColor: '#4A148C',
    },
};


/**
 * A simple example of a scrollable `GridList` containing a [Subheader](/#/components/subheader).
 */
class Vehicles extends Component {

    state = {
        vehiclesData: [],
        open: false
    };

    constructor(props) {
        super(props);
        axios.get('http://localhost:8080/api/vehicles')
            .then(res => this.setState({vehiclesData: res.data}, () => {
                if (this.state.vehiclesData.length < 1) {
                    this.setState({open: true});
                }
                else {
                    this.setState({open: false});
                }
            }))
            .catch(err => console.log(err))

    }

    render() {
        return (
            <div className="container">
                {this.state.vehiclesData.map((vehicle) => (

                    <div className="card">
                        <Card>
                            <Paper zDepth={4} style={styles.list}>
                                <CardHeader
                                    title={vehicle.year + ' ' + vehicle.make + ' - ' + vehicle.model}
                                    subtitle={<Link to={"/vehicles/" + vehicle.vin} key={vehicle.vin}><RaisedButton
                                        label="Get Vehicle Data" primary={true}/></Link>}
                                    style={styles.tile}
                                    titleColor={white}
                                />
                            </Paper>
                            <List style={styles.list}>
                                <ListItem primaryText="VIN" secondaryText={vehicle.vin}
                                          leftIcon={<Vpnkey color={green500}/>}/>
                                <ListItem primaryText="Red Line RPM" secondaryText={vehicle.redlineRpm}
                                          leftIcon={<Flag color={red500}/>}/>
                                <ListItem primaryText="Fuel Capacity" secondaryText={vehicle.maxFuelVolume}
                                          leftIcon={<Opacity color={blue500}/>}/>
                                <ListItem primaryText="Last Service"
                                          secondaryText={new Date(vehicle.lastServiceDate).toString()}
                                          leftIcon={<Build color={yellow900}/>}/>
                            </List>
                        </Card>
                    </div>
                ))}
                <Snackbar
                    open={this.state.open}
                    message="NO VEHICLES ARE UPLOADED YET"
                    autoHideDuration={4000}
                />
            </div>
        );
    }
}

export default Vehicles;