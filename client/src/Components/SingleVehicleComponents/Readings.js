/**
 * Created by uday on 7/17/2017.
 */
import React, {Component} from 'react';
import {Area, AreaChart, CartesianGrid, ResponsiveContainer, Tooltip, XAxis, YAxis} from 'recharts';
import './Readings.css'
import SelectField from 'material-ui/SelectField'
import MenuItem from 'material-ui/MenuItem';
import Snackbar from 'material-ui/Snackbar';


const items = [
    <MenuItem key={1} value={"engineRpm"} primaryText="RPM"/>,
    <MenuItem key={2} value={"fuelVolume"} primaryText="Fuel Volume"/>,
    <MenuItem key={3} value={"speed"} primaryText="Speed"/>,
    <MenuItem key={4} value={"engineHp"} primaryText="Engine HP"/>,

];
const timeRanges = [
    <MenuItem key={1} value={"15"} primaryText="15 Min"/>,
    <MenuItem key={2} value={"30"} primaryText="30 Min"/>,
    <MenuItem key={3} value={"45"} primaryText="45 Min"/>,
    <MenuItem key={4} value={"60"} primaryText="1 Hour"/>,
    <MenuItem key={5} value={"90"} primaryText="1 Hr 30 Min"/>,
    <MenuItem key={6} value={"120"} primaryText="2 Hour"/>,
    <MenuItem key={7} value={"1000000"} primaryText="All"/>
];

class Readings extends Component {

    state = {
        open: false,
        range: 1000000,
        value: 1,
        yValue: 'engineRpm',
        Readings: [],
        TimeSortedReadings: [],
        diff: []
    };
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
    handleChange = (event, index, value) => this.setState({yValue: value, value: value});
    handleTimeChange = (event, index, value) => {
        this.setState({range: value}, this.changeData)
    };

    componentWillReceiveProps(nextProps) {
        this.setState({Readings: nextProps.readings}, function () {
        });

    }

    render() {
        return (
            <div id="container">
                <span className="selectSignal">
                <SelectField
                    value={this.state.value}
                    floatingLabelText="Select the Signal"
                    onChange={this.handleChange}>
                    {items}
                </SelectField>
                </span>
                <span className="timeRange">
                    <SelectField
                        value={this.state.range}
                        floatingLabelText="Select the Time Range"
                        onChange={this.handleTimeChange}
                    >
                    {timeRanges}
                    </SelectField>
                </span>
                <div className="graph">
                    <ResponsiveContainer>
                        <AreaChart width={800} height={400} data={this.state.TimeSortedReadings}
                                   margin={{top: 10, right: 10, left: 0, bottom: 0}}>
                            <XAxis dataKey="timestamp"/>
                            <YAxis/>
                            <CartesianGrid strokeDasharray="3 3"/>
                            <Tooltip/>
                            <Area type='monotone' dataKey={this.state.yValue} stroke='#34A853' fill='#34A853'/>
                        </AreaChart>
                    </ResponsiveContainer>
                </div>
                <Snackbar
                    open={this.state.open}
                    message="NO DATA WITH THE GIVEN FILTER TO PLOT"
                    autoHideDuration={4000}
                />

            </div>


        );
    }
}

export default Readings;