/**
 * Created by uday on 7/14/2017.
 */

import React, {Component} from 'react';
import AppBar from 'material-ui/AppBar';
import Home from 'material-ui/svg-icons/action/home'
import Drawer from 'material-ui/Drawer'
import MenuItem from 'material-ui/MenuItem'
import Divider from 'material-ui/Divider/Divider'
import Link from 'react-router-dom/Link'
import './Header.css'


const styles = {
    backgrouund: {
        backgroundColor: "#F44336"
    }
};

class Header extends Component {

    handleClose = () => this.setState({open: false});

    constructor(props) {
        super(props);
        this.state = {open: false};
    }

    handleTouchMap() {
        this.setState({open: !this.state.open});
    }

    render() {
        return (
            <div>
                <AppBar
                    title="Vehicle Tracker"
                    onLeftIconButtonTouchTap={this.handleTouchMap.bind(this)}
                    style={styles.backgrouund}
                />
                <Drawer docked={false}
                        open={this.state.open}
                        onRequestChange={(open) => this.setState({open})}>
                    <div className="MenuTitle">
                        <Link to={'/'}><MenuItem onTouchTap={this.handleClose}><Home/></MenuItem></Link>
                    </div>
                    <Divider/>
                    <Link to={'/vehicles'}><MenuItem primaryText="All Vehicles" onTouchTap={this.handleClose}/></Link>
                    <Link to={'/AllAlerts'}><MenuItem primaryText="High Alerts" onTouchTap={this.handleClose}/></Link>
                    <Link to={'/'}><MenuItem primaryText="Contact Us" onTouchTap={this.handleClose}/></Link>
                </Drawer>
            </div>);
    }
}

export default Header;
