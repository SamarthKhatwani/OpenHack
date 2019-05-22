import React, { Component, Fragment } from 'react';
import './FinancialReport.css';
import { Link, Redirect } from 'react-router-dom';
import AppConstants from "../../constants/AppConstants";
import Navbar from '../Navbar/Navbar';
import WebService from '../../services/WebService';
import Collapsible from 'react-collapsible';
var Modal = require('react-bootstrap-modal')

export default class FinancialReport extends Component {
    constructor(props) {
        super(props);
        this.state = {
            modalIsOpen: false,
            details: null,
            isAckPositive: null,
            ackMessage: null,
        };
        this.user = JSON.parse(localStorage.getItem(AppConstants.USER_DETAILS));
    }

    componentDidMount() {
        this.setState({ details: this.props.location.state.details });
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
            <div className="container report">
                {redirectVar}
                <Navbar></Navbar>
                <div className="row create-row">
                    {this.renderAcknowledgement()}
                </div>
                <div className="row">
                    {this.state.details ? this.renderDetails() : null}
                </div>
                <div className="row stats">
                    <div className="col-md-6" style={{ borderRight: "2px solid #cccccc" }}>
                        {this.state.details ? this.renderTeams() : null}
                    </div>
                    <div className="col-md-5">
                        {this.state.details ? this.renderExpense() : null}
                    </div>
                </div>
            </div>
        );
    }

    renderDetails() {
        return (
            <div class="container rajat_container">
                <div class="rajat_hackathon_detail_view_heading_div">
                    <h2>{this.state.details.eventName + " Financial Report"}</h2>
                </div>
                <div class="rajat_hackathon_detail_view_details_div">
                    <div class="row" style={{ textAlign: 'center' }}>
                        <div class="col-sm-3">
                            <p><span class="rajat_hackathon_detail_heading">Revenue From Registration: </span>{'$' + this.state.details.revenueFromRegistration}</p>
                        </div>
                        <div class="col-sm-3">
                            <p><span class="rajat_hackathon_detail_heading">Revenue From Sponsors: </span>{'$' + this.state.details.revenueFromSponsor}</p>
                        </div>
                        <div class="col-sm-2">
                            <p><span class="rajat_hackathon_detail_heading">Total Expense: </span>{'$' + this.state.details.totalExpenses}</p>
                        </div>
                        <div class="col-sm-2">
                            <p><span class="rajat_hackathon_detail_heading">Amount Unpaid: </span>{'$' + this.state.details.amountUnpaid}</p>
                        </div>
                        <div class="col-sm-2">
                            <p><span class="rajat_hackathon_detail_heading">Profit: </span>{'$' + this.state.details.profit}</p>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    renderTeams() {
        let views = []
        this.state.details.teams.map((team, index) => {
            views.push(
                <Collapsible trigger={
                    <div style={{ display: "flex", justifyContent: "space-between", marginRight: "20%", alignItems: "center" }}>
                        <h4>{team.teamName}</h4>
                        <h4 style={team.allPaid ? { color: "gold" } : { color: "red", marginRight: "10px" }}>{team.allPaid ? "Payment Complete" : "Payment Pending"}</h4>
                    </div>
                }>
                    {this.renderTeamMeberPayment(team)}
                </Collapsible>
            )
        });
        return (
            <div>
                <h2>{'Team Payments'}</h2>
                <br />
                {views}
            </div>
        );
    }

    renderExpense() {
        return (
            <div>
                <h2>{'Expenses'}</h2>
                <br />
                <table class="table table-bordered ">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Description</th>
                            <th>When</th>
                            <th>Amount</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.details.expenses.map((expense, index) => {
                                return (
                                    <tr key={index}>
                                        <td>{expense.title}</td>
                                        <td>{expense.description}</td>
                                        <td>{new Date(expense.timeOfExpense).toDateString()}</td>
                                        <td>{expense.amount}</td>
                                    </tr>
                                );
                            })

                        }
                    </tbody>
                </table>
            </div>
        );
    }

    renderTeamMeberPayment(team) {
        return (
            <table class="table table-bordered ">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Amount</th>
                        <th>When</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        team.teamMembers.map((member, index) => {
                            return (
                                <tr key={index}>
                                    <td>{member.email}</td>
                                    <td>{member.amountPaid}</td>
                                    <td>{new Date(member.timeOfPayment).toDateString()}</td>
                                    <td>{member.paid ? "Paid" : "Unpaid"}</td>
                                </tr>
                            );
                        })

                    }
                </tbody>
            </table>
        );
    }
}
