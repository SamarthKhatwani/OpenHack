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
            ackMessage: null
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
                                <div class="col-md-3" style={{textAlign: "center"}}>
                                    <button type="button" class="btn btn-info" onClick={(event)=> {this.getLeaderBoard(hack.eventName); event.stopPropagation()}}>
                                        <span class="glyphicon glyphicon-th-list">  </span> LeaderBoard
                                    </button>
                                    <br />
                                    <br />
                                    {
                                        this.user.admin ?
                                            <button type="button" class="btn btn-info">
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

    getLeaderBoard(eventName){
        WebService.getInstance().fetchLeaderBoard(eventName, (response)=>{  
            console.log(response);
            if(response.success){

            }
            else{
                this.setState({ isAckPositive: false, ackMessage: response.message })
            }
        },(error)=>{
            console.log(error);
            this.setState({ isAckPositive: false, ackMessage: error })
        })
    }

    onHackClick(eventName) {
        if (this.user.admin) {
            history.push('/createHackathon', { eventName: eventName })
        }
        else {
            history.push('/detail', { eventName: eventName, role: 'hacker' })
        }
    }

    openModal() {
        this.setState({ modalIsOpen: true });
    }

    closeModal() {
        this.setState({ modalIsOpen: false });
    }

    renderCreateModal() {
        return (
            <Modal show={this.state.modalIsOpen}
                onHide={this.closeModal.bind(this)}
                aria-labelledby="ModalHeader">
                <form>
                    <Modal.Header closeButton>
                        <Modal.Title id='ModalHeader'>Create Your Organization</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>

                    </Modal.Body>
                    <Modal.Footer>
                        <Modal.Dismiss className='btn btn-default'>Cancel</Modal.Dismiss>
                        <button type="submit" className='btn btn-primary'>Create</button>
                    </Modal.Footer>
                </form>
            </Modal>
        )
    }
}