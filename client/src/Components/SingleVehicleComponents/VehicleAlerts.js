import React, {Component} from 'react'
import {Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn,} from 'material-ui/Table';
import FlatButton from 'material-ui/FlatButton'
import ExpandMore from 'material-ui/svg-icons/navigation/expand-more'
import ExpandLess from 'material-ui/svg-icons/navigation/expand-less'
import Paper from 'material-ui/Paper';

import axios from 'axios';

const styles = {
    root: {
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'space-around',
    },
    gridList: {
        textAlignAll: 'center'
    },
    tile: {
        backgroundColor: '#eeeeee',
    },
};

class VehicleAlerts extends Component {

    state = {
        alerts: [],
        icons: [<ExpandMore/>, <ExpandLess/>],
        inc: 0,
        open: false

    };

    componentWillReceiveProps(nextProps) {
        axios.get(`http://localhost:8080/api/alerts/${nextProps.vinNumber}`)
            .then(res => this.setState({alerts: res.data}))
            .catch(err => console.log(err));

    }

    render() {
        return (
            <div className="margin">
                <Paper zDepth={5}>
                    <Table selectable={false}>
                        <TableHeader adjustForCheckbox={false} displaySelectAll={false}>
                            <TableRow>
                                <TableHeaderColumn><FlatButton label="Alert Type"/>
                                </TableHeaderColumn>
                                <TableHeaderColumn><FlatButton label="Alert For"/>
                                </TableHeaderColumn>
                                <TableHeaderColumn><FlatButton label="Time"/>
                                </TableHeaderColumn>
                            </TableRow>
                        </TableHeader>
                        <TableBody displayRowCheckbox={false} showRowHover={true}>
                            {this.state.alerts.map((alert) => (
                                <TableRow key={alert.timestamp} style={styles.gridList}>
                                    <TableRowColumn>{alert.alertType}</TableRowColumn>
                                    <TableRowColumn>{alert.alertReason}</TableRowColumn>
                                    <TableRowColumn>{alert.timeStamp}</TableRowColumn>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </Paper>
            </div>

        )
    }


}

export default VehicleAlerts;