import React from 'react';
import {Tab, Tabs} from 'material-ui/Tabs';
import VehicleMap from "./VehicleMap"
import Readings from "./Readings";
import VehicleAlerts from './VehicleAlerts'
import axios from 'axios';
import Snackbar from 'material-ui/Snackbar'
import NotoficationActive from 'material-ui/svg-icons/social/notifications-active'
import InsertChart from 'material-ui/svg-icons/editor/insert-chart'
import Place from 'material-ui/svg-icons/maps/place'
import './VehicleInfo.css'

class VehicleInfo extends React.Component {
    state = {
        readingsData: [],
        open: false
    };

    constructor(props) {
        super(props);
        axios.get(`http://localhost:8080/api/readings/${this.props.match.params.vinNumber}`)
            .then(res => this.setState({readingsData: res.data}, () => {
                if (this.state.readingsData.length < 1) {
                    this.setState({open: true});
                }
                else {
                    this.setState({open: false});
                }
            }))
            .catch(err => console.log(err));
    }

    render() {
        return (
            <div>
                <Tabs>
                    <Tab label="Readings" icon={<InsertChart/>}>
                        <div className="ReadingsMargin">
                            <Readings readings={this.state.readingsData}/>
                        </div>
                    </Tab>
                    <Tab label="Vehicle Map" icon={<Place/>}>
                        <div className="MapMargin">
                            <VehicleMap readings={this.state.readingsData}/>
                        </div>
                    </Tab>
                    <Tab label="All Alerts" icon={<NotoficationActive/>}>
                        <div className="AlertsMargin">
                            <VehicleAlerts vinNumber={this.props.match.params.vinNumber}/>
                        </div>
                    </Tab>
                </Tabs>
                <Snackbar
                    open={this.state.open}
                    message="NO DATA AVAILABLE FOR THE SELECTED VEHICLE"
                    autoHideDuration={4000}
                />
            </div>

        )
    }
}

export default VehicleInfo;