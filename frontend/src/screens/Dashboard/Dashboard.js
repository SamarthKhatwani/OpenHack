import React, { Component, Fragment } from 'react';
import './Dashboard.css';
import { Link, Redirect } from 'react-router-dom';
import AppConstants from "../../constants/AppConstants";
import Navbar from '../Navbar/Navbar';
import WebService from '../../services/WebService';
import { history } from '../../router/history';
var Modal = require('react-bootstrap-modal')

export default class Dashboard extends Component {

    constructor(props) {
        super(props);
        this.state = {
            modalIsOpen: false,
            hackathons: [],
            isAckPositive: null,
            ackMessage: null,
            eventName: null
        };
        this.user = JSON.parse(localStorage.getItem(AppConstants.USER_DETAILS));
        console.log(this.user);

    }

    componentDidMount() {
        this.getHackathon(this.user.admin ? "admin" : "hacker")
    }

    getHackathon(role) {
        WebService.getInstance().getHackathon(role, (response) => {
            console.log(response);
            if (response.success) {
                this.setState({ hackathons: response.results })
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
            <div className="container dashboard">
                {redirectVar}
                <Navbar></Navbar>
                <div className="row create-row">
                    {this.renderAcknowledgement()}
                    {
                        this.user.admin ?
                            <Link to="/createHackathon"><button type="button" class="btn btn-dark btn-lg">
                                <span class="glyphicon glyphicon-plus"></span> Create Hackathon
                            </button>
                            </Link>
                            : null
                    }
                </div>
                <div className="row hack">
                    {this.renderHackathon()}
                </div>
                {this.renderCreateModal()}
            </div>
        );
    }

    renderHackathon() {
        let views = []
        if (this.state.hackathons.length == 0) {
            return (
                <h3>No Hackathons Available</h3>
            )
        }
        else {
            this.state.hackathons.map((hack, index) => {
                views.push(
                    <div class="card rajat_hack_hacakathon_list_card" onClick={() => { this.onHackClick(hack.eventName) }}>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-6 rajat_hackathon_list_description_col">
                                    <h4 class="card-title">{hack.eventName}</h4>
                                    <p class="card-text rajat_hackathon_list_description">{hack.description}</p>
                                </div>
                                <div class="col-md-3 rajat_hackathon_info">
                                    <p><span class="rajat_muted_span">Starts On  </span>{new Date(hack.startDate).toDateString()}</p>
                                    <p><span class="rajat_muted_span">Ends On  </span>{new Date(hack.endDate).toDateString()}</p>
                                    <p><span class="rajat_muted_span">Team Size  </span>{hack.teamMinSize + ' to ' + hack.teamMaxSize + ' members'}</p>
                                    <p><span class="rajat_muted_span">Registration Fee  $</span>{hack.registrationFee}</p>
                                </div>
                                <div class="col-md-3" style={{ textAlign: "center" }}>
                                    {
                                        hack.finalized ?
                                            <Fragment>
                                                <button type="button" class="btn btn-info" onClick={(event) => { this.getLeaderBoard(hack.eventName); event.stopPropagation() }}>
                                                    <span class="glyphicon glyphicon-th-list">  </span> LeaderBoard
                                                </button>
                                                <br />
                                                <br />
                                            </Fragment>
                                            : null
                                    }
                                    {
                                        this.user.admin && !hack.finalized ?
                                            <Fragment>
                                                <button type="button" class="btn btn-info" onClick={(event) => { this.openModal(hack.eventName); event.stopPropagation() }}>
                                                    <span class="glyphicon glyphicon-usd">  </span> Add Expenses
                                            </button>
                                                <br />
                                                <br />
                                            </Fragment>
                                            : null
                                    }
                                    {
                                        this.user.admin ?
                                            <button type="button" class="btn btn-info" onClick={(event) => { this.getFinancialReport(hack.eventName); event.stopPropagation() }}>
                                                <span class="glyphicon glyphicon-file">  </span> Financial Report
                                            </button>
                                            : null
                                    }
                                </div>
                            </div>
                        </div>
                    </div>
                );
            });
            return views;
        }
    }

    getFinancialReport(eventName) {
        WebService.getInstance().fetchFinancialReport(this.user.email, eventName, (response) => {
            console.log(response);
            if (response) {
                history.push('/financialReport', { details: response })
            }
            else {
                this.setState({ isAckPositive: false, ackMessage: response })
            }
        }, (error) => {
            console.log(error);
            this.setState({ isAckPositive: false, ackMessage: error })
        })
    }

    getLeaderBoard(eventName) {
        history.push({
            pathname: '/leaderBoard',
            search: '?eventName=' + eventName,
        });
    }

    onHackClick(eventName) {
        if (this.user.admin) {
            history.push('/createHackathon', { eventName: eventName })
        }
        else {
            history.push('/detail', { eventName: eventName, role: 'hacker' })
        }
    }

    openModal(eventName) {
        this.setState({ modalIsOpen: true, eventName });
    }

    closeModal() {
        this.setState({ modalIsOpen: false });
    }


    onChange = (event) => {
        this.setState({ [event.target.name]: event.target.value })
    }

    addExpenses(event) {
        event.preventDefault();
        let { eventName, title, description, amount } = this.state;
        let req = { eventName, title, description, amount };
        console.log(req);
        WebService.getInstance().addExpense(req, (response) => {
            if (response.success) {
                this.setState({ isAckPositive: true, ackMessage: response.message })
                this.closeModal();
            }
            else {
                this.setState({ isAckPositive: true, ackMessage: response.message })
                this.closeModal();
            }
        }, (error) => {
            console.log(error);
            this.setState({ isAckPositive: false, ackMessage: error })
            this.closeModal();
        });
    }

    renderCreateModal(eventName) {
        return (
            <Modal show={this.state.modalIsOpen}
                onHide={this.closeModal.bind(this)}
                aria-labelledby="ModalHeader">
                <form onSubmit={this.addExpenses.bind(this)}>
                    <Modal.Header closeButton>
                        <Modal.Title id='ModalHeader'>{"Add Expenses for " + this.state.expenseEventName}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <div class="form-group-md">
                            <label>Expense Title <span className="required"> *</span></label>
                            <input type="text" class="form-control form-control-md" onChange={this.onChange} name="title" value={this.state.name} placeholder="Title" required />
                        </div>
                        <div class="form-group-md">
                            <label>Expense Description<span className="required"> *</span></label>
                            <input type="text" class="form-control form-control-md" onChange={this.onChange} name="description" value={this.state.description} placeholder="Description" required />
                        </div>
                        <div class="form-group-md">
                            <label>Expense Amount $<span className="required"> *</span></label>
                            <input type="number" class="form-control form-control-md" onChange={this.onChange} name="amount" value={this.state.address} placeholder="Amount" required />
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <Modal.Dismiss className='btn btn-default'>Cancel</Modal.Dismiss>
                        <button type="submit" className='btn btn-primary'>Add</button>
                    </Modal.Footer>
                </form>
            </Modal>
        )
    }
}