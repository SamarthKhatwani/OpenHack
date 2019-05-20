import React, { Component, Fragment } from 'react';
import './CreateHackathon.css';
import { Link, Redirect } from 'react-router-dom';
import AppConstants from "../../constants/AppConstants";
import Navbar from '../Navbar/Navbar';
import { history } from '../../router/history';
import WebService from '../../services/WebService';
var Modal = require('react-bootstrap-modal')

export default class CreateHackathon extends Component {
    constructor(props) {
        super(props);
        this.mode="create";
        this.state = {
            modalIsOpen: false,
            eventName: "",
            description: "",
            startDate: "",
            endDate: "",
            judges: "",
            sponsors: "",
            teamMaxSize: "",
            teamMinSize: "",
            registrationFee: "",
            discount: "",
            isOpen: false,
            isFinalized: false,
            failedCreation:false
        };
    }

    componentDidMount(){
        if(this.props.location){
            if(this.props.location.state && this.props.location.state.eventName){
                this.getHackathonDetails(this.props.location.state.eventName, "admin");
                this.mode="update";
            }
        }
    }

    getHackathonDetails(eventName, role) {
        WebService.getInstance().getHackathonDetail(eventName, role, (response) => {
            console.log(response);
            if (response.success) {
                this.setState({ 
                    eventName: response.eventName,
                    description: response.description,
                    startDate: new Date(response.startDate).toISOString().substr(0, 10),
                    endDate: new Date(response.endDate).toISOString().substr(0, 10),
                    judges: response.judges.join(','),
                    sponsors: response.sponsors.join(','),
                    teamMaxSize: response.teamMaxSize,
                    teamMinSize: response.teamMinSize,
                    registrationFee: response.registrationFee,
                    discount: response.discount,
                    isOpen: response.isOpen,
                    isFinalized: response.finalized,
                 });
            }
            else{
                this.setState({failedCreation:true, errorMessage:response.message});
            }
        }, (error) => {
            console.log(error);
            this.setState({failedCreation:true, errorMessage:error});
        });
    }

    onChange = (event) => {
        this.setState({ [event.target.name]: event.target.value, isFormDirty: true, failedCreation: false });
    }

    onSubmit(event) {
        event.preventDefault();
        console.log(this.state);
        let { eventName, description, startDate, endDate, judges, sponsors, teamMaxSize, teamMinSize,
            registrationFee, discount, isOpen, isFinalized } = this.state
        WebService.getInstance().createUpdateHackathon( { eventName, description, startDate, endDate, judges: judges.split(','), 
            sponsors: sponsors.split(','), teamMaxSize, teamMinSize, registrationFee, discount, 
            isOpen, isFinalized },(response) => {
            console.log(response);
            if (response.success) {
                alert(response.message);
                history.push('/dashboard');
            }
        }, (error) => {
            console.log(error);
            this.setState({failedCreation:true, errorMessage:error});
        });
    }

    renderFailed() {
        if (this.state.failedCreation) {
            return (
                <div class="alert alert-danger" role="alert">
                    {this.state.errorMessage}
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
            <div className="container create-hackathon">
                {redirectVar}
                <Navbar></Navbar>
                {this.renderFailed()}
                <div class="rajat_hackathon_form_wrapper">
                    <div class="row rajat-row">
                        <div class="col-md-4 rajat_hackathon_form_details">
                            <h2>{this.mode == 'create' ? "Create Hackathon" : "Update Hackathon"}</h2>
                        </div>
                        <div class="col-md-8 rajat_hackathon_form_wrapper_div">
                            <div class="rajat_hackathon_form_div">
                                <form class="rajat_hackathon_registration_form" onSubmit={this.onSubmit.bind(this)}>
                                    <div class="form-group">
                                        <label for="eventName">Event Name</label>
                                        <input type="text" class="form-control" name="eventName" onChange={this.onChange.bind(this)} value={this.state.eventName} disabled={this.mode != 'create'  ? true : false } placeholder="Event name" required />
                                        <small id="emailHelp" class="form-text text-muted">Event name should be unique.</small>
                                    </div>
                                    <div class="form-group">
                                        <label for="description">Description</label>
                                        <input type="text" class="form-control" name="description" onChange={this.onChange.bind(this)} value={this.state.description} placeholder="Description" minLength="10" required />
                                    </div>
                                    <div class="hackathon_dates">
                                        <div class="row">
                                            <div class='col-sm-6'>
                                                <div class="form-group">
                                                    <label for="startDate">Start Date</label>
                                                    <div class='input-group date' >
                                                        <input type='date' name='startDate' class="form-control" onChange={this.onChange.bind(this)} value={this.state.startDate} required />
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class='col-sm-6'>
                                                <div class="form-group">
                                                    <label for="endDate">End Date</label>
                                                    <div class='input-group date'>
                                                        <input type='date' class="form-control" name="endDate" onChange={this.onChange.bind(this)} value={this.state.endDate} required />
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="Judges">
                                        <label for="judges">Judges</label>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="judges" placeholder="Comma Separated Judges" onChange={this.onChange.bind(this)} value={this.state.judges} required />
                                        </div>
                                    </div>
                                    <div class="sponsors">
                                        <label for="judges">Sponsors</label>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="sponsors" placeholder="Comma Separated Sponsors" onChange={this.onChange.bind(this)} value={this.state.sponsors} required />
                                        </div>
                                    </div>
                                    <div class="team_size rajat_form_row">
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <label for="teanMinSize">Minimum team size</label>
                                                <input type="number" class="form-control" name="teamMinSize" placeholder="Minimum team size" onChange={this.onChange.bind(this)} value={this.state.teamMinSize} required />
                                            </div>
                                            <div class="col-sm-6">
                                                <label for="teanMaxSize">Maximum team size</label>
                                                <input type="number" class="form-control" name="teamMaxSize" placeholder="Maximum team size" onChange={this.onChange.bind(this)} value={this.state.teamMaxSize} required />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fees rajat_form_row">
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <label for="registrationFee">Registration Fee</label>
                                                <input type="number" class="form-control" name="registrationFee" placeholder="Registration Fee" onChange={this.onChange.bind(this)} value={this.state.registrationFee} required />
                                            </div>
                                            <div class="col-sm-6">
                                                <label for="discount">Discount</label>
                                                <input type="number" class="form-control" name="discount" onChange={this.onChange.bind(this)} value={this.state.discount} placeholder="discount" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="">
                                        <div class="row">
                                            <div class="col-sm-6"></div>
                                            <div class="col-sm-6"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <input type="checkbox" class="form-check-input" name="isOpen" onChange={this.onChange.bind(this)} value={this.state.isOpen} />
                                        <label class="form-check-label" for="isOpen">Is hackathon open for submission?</label>
                                    </div>
                                    <div class="form-group">
                                        <input type="checkbox" class="form-check-input" name="isFinalized" onChange={this.onChange.bind(this)} value={this.state.isFinalized} />
                                        <label class="form-check-label" for="isFinalized">Is Hackathon Finalized?</label>
                                    </div>
                                    <button type="submit" class="btn rajat_register">Submit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}