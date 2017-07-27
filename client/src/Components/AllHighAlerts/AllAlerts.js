import React, {Component} from 'react'
import axios from 'axios'
import {Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn,} from 'material-ui/Table';
import Snackbar from 'material-ui/Snackbar'
import FlatButton from 'material-ui/FlatButton'
import ExpandMore from 'material-ui/svg-icons/navigation/expand-more'
import ExpandLess from 'material-ui/svg-icons/navigation/expand-less'
import Paper from 'material-ui/Paper';
import './AllAlerts.css'


class AllAlerts extends Component {
    state = {
        alerts: [],
        icons: [<ExpandMore/>, <ExpandLess/>],
        inc: 0,
        open: false,

    };

    constructor(props) {
        super(props);
        axios.get(`http://localhost:8080/api/alerts`)
            .then(res => this.setState({alerts: res.data}, () => {
                if ((this.state.alerts.length < 1)) {
                    this.setState({open: true})
                }
                else {
                    this.setState({open: false})
                }
            }))
            .catch(err => console.log(err));
    }

    render() {
        return (
            <div className="margin">
                <Paper zDepth={5}>
                    <Table selectable={false}>
                        <TableHeader fixedHeader={false} adjustForCheckbox={false} displaySelectAll={false}>
                            <TableRow>
                                <TableHeaderColumn><FlatButton label="VIN"/>
                                </TableHeaderColumn>
                                <TableHeaderColumn><FlatButton label="High Alerts" labelPosition="before"
                                                               onTouchTap={() => {
                                                                   this.setState({inc: this.state.inc + 1})
                                                               }}
                                                               icon={this.state.icons[this.state.inc % 2]}/>
                                </TableHeaderColumn>
                            </TableRow>
                        </TableHeader>
                        <TableBody displayRowCheckbox={false} showRowHover={true}>
                            {this.state.alerts.sort((a, b) => {
                                if (this.state.inc % 2)
                                    return parseInt(a.numberOfAlerts, 10) - parseInt(b.numberOfAlerts, 10);
                                else
                                    return parseInt(b.numberOfAlerts, 10) - parseInt(a.numberOfAlerts, 10);

                            }).map((alert) => (
                                <TableRow key={alert.id}>
                                    <TableRowColumn>{alert.id}</TableRowColumn>
                                    <TableRowColumn>{alert.numberOfAlerts}</TableRowColumn>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </Paper>
                <Snackbar
                    open={this.state.open}
                    message="NO HIGH ALERTS IN THE PAST 2 HOURS"
                    autoHideDuration={4000}
                />
            </div>
        )
    }
}

export default AllAlerts;