import React, { Component, Fragment } from 'react';
import './MyJudgement.css';
import { Link, Redirect } from 'react-router-dom';
import AppConstants from "../../constants/AppConstants";
import Navbar from '../Navbar/Navbar';
import WebService from '../../services/WebService';
import { history } from '../../router/history';
var Modal = require('react-bootstrap-modal')

export default class MyJudgement extends Component {

    constructor(props) {
        super(props);
        this.state = {
            modalIsOpen: false,
            hackathons: [],
            isAckPositive: null,
            ackMessage: null
        };
        this.user = JSON.parse(localStorage.getItem(AppConstants.USER_DETAILS));
    }

    componentDidMount() {
        this.getHackathon('judge')
    }

    getHackathon(role) {
        WebService.getInstance().getHackathon(role, (response) => {
            console.log(response);
            if(response.success){
                this.setState({hackathons:response.results})
            }
        }, (error) => {
            console.log(error);
            this.setState({isAckPositive:false, ackMessage:error})
        });
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
                <div className="row hack">
                    {this.renderHackathon()}
                </div>
            </div>
        );
    }

    renderHackathon() {
        let views = []
        if(this.state.hackathons.length == 0){
            return(
                <h3>No Hackathons Available for Judgement</h3>
            )
        }
        else{
            this.state.hackathons.map((hack, index) => {
                views.push(
                        <div class="card rajat_hack_hacakathon_list_card" onClick={()=>{this.onHackClick(hack.eventName)}}>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-8 rajat_hackathon_list_description_col">
                                        <h4 class="card-title">{hack.eventName}</h4>
                                        <p class="card-text rajat_hackathon_list_description">{hack.description}</p>
                                    </div>
                                    <div class="col-md-4 rajat_hackathon_info">
                                        <p><span class="rajat_muted_span">Starts On  </span>{new Date(hack.startDate).toDateString()}</p>
                                        <p><span class="rajat_muted_span">Ends On  </span>{new Date(hack.endDate).toDateString()}</p>
                                        <p><span class="rajat_muted_span">Team Size  </span>{hack.teamMinSize + ' to ' + hack.teamMaxSize +' members'}</p>
                                        <p><span class="rajat_muted_span">Registration Fee  $</span>{hack.registrationFee}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                );
            });
            return views;
        }
    }

    onHackClick(eventName){
        history.push('/detail',{eventName:eventName, role:'judge'})
    }
}
