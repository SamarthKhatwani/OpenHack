import React, { Component, Fragment } from 'react';
import './HackathonDetail.css';
import { Link, Redirect } from 'react-router-dom';
import AppConstants from "../../constants/AppConstants";
import Navbar from '../Navbar/Navbar';
import WebService from '../../services/WebService';
var Modal = require('react-bootstrap-modal')

export default class HackathonDetail extends Component {
    constructor(props) {
        super(props);
        this.state = {
            modalIsOpen: false,
            details: null,
            isAckPositive: null,
            ackMessage: null
        };
        this.user = JSON.parse(localStorage.getItem(AppConstants.USER_DETAILS));
    }

    componentDidMount() {
        this.getHackathonDetails(this.user.admin ? "admin" : "hacker")
    }

    getHackathonDetails(role) {
        // WebService.getInstance().getHackathon(role, (response) => {
        //     console.log(response);
        //     if(response.success){
        //         this.setState({hackathons:response.results})
        //     }
        // }, (error) => {
        //     console.log(error);
        //     this.setState({isAckPositive:false, ackMessage:error})
        // });
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
            <div className="container dashboard">
                {redirectVar}
                <Navbar></Navbar>
                <div className="row create-row">
                    {this.renderAcknowledgement()}
                </div>
            </div>
        );
    }

}