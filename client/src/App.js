import React, {Component} from 'react';
import Header from './Components/Header/Header'
import Intro from './Components/IndexComponents/Intro'
import './App.css';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import injectTapEventPlugin from 'react-tap-event-plugin';
import {BrowserRouter as Router, Route} from 'react-router-dom'
import Vehicles from './Components/AllVehiclesComponents/Vehicles'
import VehicleInfo from './Components/SingleVehicleComponents/VehicleInfo'
import AllALerts from './Components/AllHighAlerts/AllAlerts'

injectTapEventPlugin();

class App extends Component {
    render() {
        return (
            <MuiThemeProvider>
                <Router>
                    <div className="back">
                        <Header/>
                        <Route exact={true} path="/" component={Intro}/>
                        <Route exact={true} path="/vehicles" component={Vehicles}/>
                        <Route exact={true} path="/AllAlerts" component={AllALerts}/>
                        <Route exact={true} path="/Vehicles/:vinNumber" component={VehicleInfo}/>
                    </div>
                </Router>
            </MuiThemeProvider>
        );
    }
}

export default App;
