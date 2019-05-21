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
            ackMessage: null,
            url: null,
            teamName: null,
            teamMember: null,
            role: 'hacker',
            eventName: null,
            teams: []
        };
        this.user = JSON.parse(localStorage.getItem(AppConstants.USER_DETAILS));
    }

    componentDidMount() {
        console.log(this.props.location.state.eventName, this.props.location.state.role);
        this.setState({ eventName: this.props.location.state.eventName, role: this.props.location.state.role }, () => {
            this.getHackathonDetails(this.state.eventName, this.state.role);
        })
    }

    getHackathonDetails(eventName, role) {
        WebService.getInstance().getHackathonDetail(eventName, role, (response) => {
            console.log(response);
            if (response.success) {
                if (this.state.role == 'hacker') {
                    let team = Object.keys(response.team);
                    let url = null
                    if (team.length != 0 && response.team[team[0]].submission) {
                        url = response.team[team[0]].submission
                    }
                    if (response.success) {
                        this.setState({
                            details: response,
                            url
                        });
                    }
                }
                else if (this.state.role == "judge") {
                    this.setState({ teams: response.teams });
                }
            }
            else {
                this.setState({ isAckPositive: false, ackMessage: response.message })
            }
        }, (error) => {
            console.log(error);
            this.setState({ isAckPositive: false, ackMessage: error })
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
            <div className="container hack-detail">
                {redirectVar}
                <Navbar></Navbar>
                <div className="row create-row">
                    {this.renderAcknowledgement()}
                </div>
                {this.state.details && this.state.role == 'hacker' ? this.renderDetails() : null}
                {this.state.role == 'judge' && this.state.teams ? this.renderEvaluation() : null}
                {this.state.role == 'hacker' && this.state.details ? this.renderDetailsInput() : null}
            </div>
        );
    }

    onChangeScore(event) {
        console.log(event.target.value);
        let team = this.state.teams[event.target.name];
        let teams = this.state.teams;
        team['score'] = event.target.value;
        teams.splice(event.target.name, 1, team);
        this.setState({ teams });
    }

    renderEvaluation() {
        let views = [];
        console.log(this.state.teams);

        return (
            <div class="container">
                <h2>{this.state.eventName}</h2>
                <br />
                <form onSubmit={this.onSubmitScore.bind(this)}>
                    <table class="table table-bordered ">
                        <thead>
                            <tr>
                                <th>Team Name</th>
                                <th>Submission</th>
                                <th>Score</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.teams.map((team, index) => {
                                    return (
                                        <tr key={index}>
                                            <td>{team.teamName}</td>
                                            <td>{team.submission}</td>
                                            <td><input className="form-control" name={index} type="number" onChange={this.onChangeScore.bind(this)} value={parseFloat(team.score)} min="0" max="10" step="0.1" required /></td>
                                        </tr>
                                    );
                                })

                            }
                        </tbody>
                    </table>
                    <div className="row" style={{ textAlign: "center" }}>
                        <button type="submit" className="btn btn-lg rajat_register">Save Score</button>
                    </div>
                </form>
            </div>
        );
    }

    onSubmitScore(event) {
        event.preventDefault();
        let req = {
            "judge": this.user.email,
            "eventName": this.state.eventName,
            "teams": this.state.teams
        }
        WebService.getInstance().saveHackathonGrade(req, (response) => {
            console.log(response);
            if(response.success){
                this.setState({ isAckPositive: true, ackMessage: response.message })
            }
            else{
                this.setState({ isAckPositive: false, ackMessage: response.message })
            }
        }, (error) => {
            console.log(error);
            this.setState({ isAckPositive: false, ackMessage: error })
        });
    }

    renderDetails() {
        return (
            <div class="container rajat_container">
                <div class="rajat_hackathon_detail_view_heading_div">
                    <h2>{this.state.details.eventName}</h2>
                </div>
                <div class="rajat_hackathon_detail_view_description_div">
                    <p>{this.state.details.description}</p>
                </div>

                <div class="rajat_hackathon_detail_view_details_div">
                    <div class="row">
                        <div class="col-sm-3">
                            <p><span class="rajat_hackathon_detail_heading">Start Date: </span><span class="glyphicon glyphicon-calendar" aria-hidden="true"> </span>{new Date(this.state.details.startDate).toDateString()}</p>
                        </div>
                        <div class="col-sm-3">
                            <p><span class="rajat_hackathon_detail_heading">End Date: </span><span class="glyphicon glyphicon-calendar" aria-hidden="true"> </span>{new Date(this.state.details.endDate).toDateString()}</p>
                        </div>
                        <div class="col-sm-3">
                            <p><span class="rajat_hackathon_detail_heading">Open for submission: </span>{this.state.details.open ? "Yes" : "No"}</p>
                        </div>
                        <div class="col-sm-3">
                            <p><span class="rajat_hackathon_detail_heading">Sponsors: </span>{this.state.details.sponsors.join(", ")}</p>
                        </div>
                        <div class="col-sm-3">
                            <p><span class="rajat_hackathon_detail_heading">Registration Fee: $</span>{this.state.details.registrationFee}</p>
                        </div>
                        <div class="col-sm-3">
                            <p><span class="rajat_hackathon_detail_heading">Discount: </span>{this.state.details.discount}</p>
                        </div>
                        <div class="col-sm-3">
                            <p><span class="rajat_hackathon_detail_heading">Minimum Team Size: </span>{this.state.details.teamMinSize}</p>
                        </div>
                        <div class="col-sm-3">
                            <p><span class="rajat_hackathon_detail_heading">Maximum Team Size: </span>{this.state.details.teamMaxSize}</p>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    renderDetailsInput() {
        let team = Object.keys(this.state.details.team);
        if (team.length == 0) {
            return this.renderRegistration();
        }
        else {
            if (this.state.details.team[team[0]].allPaid) {
                return this.renderSubmission(true);
            }
            else {
                return this.renderSubmission(false);
            }
        }
    }

    renderSubmission(isEnable, submission = "") {
        return (
            <div class="rajat_hackathon_form_wrapper">
                <div class="row">
                    <div class="col-md-4 rajat_hackathon_form_details">
                        <h2>Submit your Work</h2>
                    </div>
                    <div class="col-md-8 rajat_hackathon_form_wrapper_div">
                        <div class="rajat_hackathon_form_div">
                            <form class="rajat_hackathon_registration_form" onSubmit={this.submission.bind(this)}>
                                <div class="form-group">
                                    <label for="url">Github URL</label>
                                    <input type="text" class="form-control" name="url" onChange={this.onChange.bind(this)} value={this.state.url} placeholder="URL" required />
                                </div>
                                <button type="submit" class="btn rajat_register" disabled={!isEnable}>Submit</button>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        );
    }

    submission(event) {
        event.preventDefault();
        let team = Object.keys(this.state.details.team);
        let teamName = team[0];
        let req = {
            teamName,
            eventName: this.state.details.eventName,
            url: this.state.url
        };
        WebService.getInstance().submitHackathon(req, (response) => {
            if (response.success) {
                this.getHackathonDetails(this.props.location.state.eventName, 'hacker');
                this.setState({ ackMessage: response.message, isAckPositive: true });
            }
            else {
                this.setState({ ackMessage: response.message, isAckPositive: false })
            }
        }, (error) => {
            console.log(error);
            this.setState({ ackMessage: error, isAckPositive: false })
        })
    }

    onChange(event) {
        this.setState({ [event.target.name]: event.target.value, ackMessage: null });
    }


    renderRegistration() {
        return (
            <div class="rajat_hackathon_form_wrapper">
                <div class="row">
                    <div class="col-md-4 rajat_hackathon_form_details">
                        <h2>Register for this Hackathon</h2>
                    </div>
                    <div class="col-md-8 rajat_hackathon_form_wrapper_div">
                        <div class="rajat_hackathon_form_div">
                            <form class="rajat_hackathon_registration_form" onSubmit={this.registeration.bind(this)}>
                                <div class="form-group">
                                    <label for="eventName">Team Name</label>
                                    <input type="text" class="form-control" name="teamName" onChange={this.onChange.bind(this)} value={this.state.teamName} placeholder="Team name" required />
                                    <small id="emailHelp" class="form-text text-muted">Team name should be unique.</small>
                                </div>
                                <div class="teamMembers">
                                    <label for="judges">Team members</label>
                                    <input type="text" name="teamMember" class="form-control" onChange={this.onChange.bind(this)} value={this.state.teamMember} placeholder="Example: memberemail@gmail.com; memberRole, memberemail@gmail.com; memberRole" required />
                                </div>
                                <button type="submit" class="btn rajat_register">Submit</button>
                            </form>
                        </div>

                    </div>
                </div>

            </div>
        )
    }

    registeration(event) {
        event.preventDefault();
        let teams = this.state.teamMember.split(',').map((team) => {
            let temp = team.split(';');
            return {
                "role": temp[1],
                "email": temp[0].trim()
            }
        });
        let req = {
            "email": this.user.email,
            "eventName": this.props.location.state.eventName,
            "teamName": this.state.teamName,
            "teamMembers": teams
        }
        WebService.getInstance().registerHackathon(req, (response) => {
            console.log(response);
            if (response.success) {
                this.getHackathonDetails(this.props.location.state.eventName, 'hacker');
                this.setState({ ackMessage: response.message, isAckPositive: true })
            }
            else {
                this.setState({ ackMessage: response.message, isAckPositive: true })
            }
        }, (error) => {
            console.log(error);
            this.setState({ ackMessage: error, isAckPositive: false })
        })
    }
}
