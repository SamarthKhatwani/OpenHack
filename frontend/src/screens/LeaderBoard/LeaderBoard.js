import React, { Component, Fragment } from 'react';
import './LeaderBoard.css';
import { Link, Redirect } from 'react-router-dom';
import AppConstants from "../../constants/AppConstants";
import Navbar from '../Navbar/Navbar';
import WebService from '../../services/WebService';
import { history } from '../../router/history';
var Modal = require('react-bootstrap-modal')

export default class LeaderBoard extends Component {

    constructor(props) {
        super(props);
        this.state = {
            modalIsOpen: false,
            result: [],
            isAckPositive: null,
            ackMessage: null,
            eventName: ""
        };
        this.user = JSON.parse(localStorage.getItem(AppConstants.USER_DETAILS));
    }

    componentDidMount() {
        console.log(this.props.location.state.result)
        this.setState({ result: this.props.location.state.result, eventName: this.props.location.state.eventName });
    }

    renderAcknowledgement() {
        if (this.state.ackMessage) {
            return (
                <div class="alert" className={this.state.isAckPositive ? 'alert-success' : 'alert-danger'} role="alert">
                    {this.state.ackMessage}
                </div>
            );
        }
    }


    render() {
        let redirectVar = null;
        if (!localStorage.getItem(AppConstants.AUTH_TOKEN)) {
            redirectVar = <Redirect to="/" />
        }
        return (
            <div className="container leader-board">
                {redirectVar}
                <Navbar></Navbar>
                <div className="row create-row">
                    {this.renderAcknowledgement()}
                </div>
                <div className="row hack">
                    {this.renderLeaderBoard()}
                </div>
            </div>
        );
    }

    renderLeaderBoard() {
        let views = [];
        console.log(this.state.result);

        return (
            <div class="container">
                <h2>{this.state.eventName + '  Leader Board'}</h2>
                <br />
                <table class="table table-bordered ">
                    <thead>
                        <tr>
                            <th>Rank</th>
                            <th>Team Name</th>
                            <th>Team Members</th>
                            <th>Score</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.result.map((team, index) => {
                                if (index == 0) {
                                    return (
                                        <tr key={index} style={{ backgroundColor: "#ff7575", fontWeight: "bold" }}>
                                            <td>{team.rank}</td>
                                            <td>{team.teamName}</td>
                                            <td>
                                                {team.teamMembers.map((name) => {
                                                    return (
                                                        <p><span class="glyphicon glyphicon-user" style={{fontSize:"10px", marginRight:"5px"}}></span>{name.email}</p>
                                                    )
                                                })}
                                            </td>
                                            <td>{team.score}</td>
                                        </tr>
                                    );
                                }
                                else if (index == 1) {
                                    return (
                                        <tr key={index} style={{ backgroundColor: "#47efc2", fontWeight: "bold" }}>
                                            <td>{team.rank}</td>
                                            <td>{team.teamName}</td>
                                            <td>
                                                {team.teamMembers.map((name) => {
                                                    return (
                                                        <p><span class="glyphicon glyphicon-user" style={{fontSize:"10px", marginRight:"5px"}}></span>{name.email}</p>
                                                    )
                                                })}
                                            </td>
                                            <td>{team.score}</td>
                                        </tr>
                                    );
                                }
                                else if (index == 2) {
                                    return (
                                        <tr key={index} style={{ backgroundColor: "#47a6ef", fontWeight: "bold" }}>
                                            <td>{team.rank}</td>
                                            <td>{team.teamName}</td>
                                            <td>
                                                {team.teamMembers.map((name) => {
                                                    return (
                                                        <p><span class="glyphicon glyphicon-user" style={{fontSize:"10px", marginRight:"5px"}}></span>{name.email}</p>
                                                    )
                                                })}
                                            </td>
                                            <td>{team.score}</td>
                                        </tr>
                                    );
                                }
                                else {
                                    return (
                                        <tr key={index}>
                                            <td>{team.rank}</td>
                                            <td>{team.teamName}</td>
                                            <td>
                                                {team.teamMembers.map((name) => {
                                                    return (
                                                        <p><span class="glyphicon glyphicon-user" style={{fontSize:"10px", marginRight:"5px"}}></span>{name.email}</p>
                                                    )
                                                })}
                                            </td>
                                            <td>{team.score}</td>
                                        </tr>
                                    );
                                }
                            })

                        }
                    </tbody>
                </table>
            </div>
        );
    }

    onHackClick(eventName) {
        history.push('/detail', { eventName: eventName, role: 'judge' })
    }
}
