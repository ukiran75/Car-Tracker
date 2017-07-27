import React from 'react';
import {Gmaps, Marker,} from 'react-gmaps'
import './VehicleMap.css'
import SelectField from 'material-ui/SelectField'
import MenuItem from 'material-ui/MenuItem';
import Snackbar from 'material-ui/Snackbar';


const timeRanges = [
    <MenuItem key={1} value={"15"} primaryText="15 Min"/>,
    <MenuItem key={2} value={"30"} primaryText="30 Min"/>,
    <MenuItem key={3} value={"45"} primaryText="45 Min"/>,
    <MenuItem key={4} value={"60"} primaryText="1 Hour"/>,
    <MenuItem key={5} value={"90"} primaryText="1 Hr 30 Min"/>,
    <MenuItem key={6} value={"120"} primaryText="2 Hour"/>,
    <MenuItem key={7} value={"1000000"} primaryText="All"/>
];

const params = {v: '3.exp', key: 'AIzaSyAyesbQMyKVVbBgKVi2g6VX7mop2z96jBo'};

export class VehicleMap extends React.Component {

    changeData = () => {
        let newData = [];
        for (let i = 0; i < this.state.Readings.length; i++) {
            let diff = Math.floor((Date.now() - new Date(this.state.Readings[i].timestamp)) / 60000);
            if (diff < this.state.range) {
                newData.push(this.state.Readings[i])
            }
        }
        this.setState({TimeSortedReadings: newData}, () => {
            if ((this.state.TimeSortedReadings.length < 1)) {
                this.setState({open: true})
            }
            else {
                this.setState({open: false})
            }
        });

    };
    handleTimeChange = (event, index, value) => {
        this.setState({range: value}, this.changeData)
    };
    state = {
        Readings: [],
        open: false,
        TimeSortedReadings: [],
        range: 30
    };

    componentWillReceiveProps(nextProps) {
        this.setState({Readings: nextProps.readings});

    }

    render() {
        return (
            <div id="container">
                <div className="timeRange">
                    <SelectField
                        value={this.state.range}
                        floatingLabelText="Select the Time Range"
                        onChange={this.handleTimeChange}
                    >
                        {timeRanges}
                    </SelectField>
                </div>
                <Gmaps
                    width={'100%'}
                    height={'80%'}
                    lat={41.881832}
                    lng={-87.623177}
                    zoom={2}
                    loadingMessage={'Map is loading'}
                    params={params}>
                    {this.state.TimeSortedReadings.map((reading) =>
                        <Marker
                            key={reading.timestamp}
                            lat={reading.latitude}
                            lng={reading.longitude}
                            draggable={true}/>
                    )}
                </Gmaps>
                <Snackbar
                    open={this.state.open}
                    message="NO DATA WITH THE GIVEN FILTER TO PLOT"
                    autoHideDuration={2000}
                />

            </div>
        );
    }
}

export default VehicleMap;