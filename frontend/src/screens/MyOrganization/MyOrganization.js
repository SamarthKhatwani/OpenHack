import React, { Component, Fragment } from 'react';
import './MyOrganization.css';
import { Link, Redirect } from 'react-router-dom';
import AppConstants from "../../constants/AppConstants";
import Navbar from '../Navbar/Navbar';
import WebService from '../../services/WebService';
import Collapsible from 'react-collapsible';
var Modal = require('react-bootstrap-modal')

export default class MyOrganization extends Component {

    constructor(props) {
        super(props);
        this.state = {
            modalIsOpen: false,
            myOrganizations: [],
            isAckPositive: false,
            ackMessage: null
        }
    }

    componentDidMount() {
        this.getOrganizations();
    }

    getOrganizations() {
        WebService.getInstance().getOrganizationList((response) => {
            console.log(response);
            if (response.success) {
                this.setState({ myOrganizations: response.organization })
            }
        }, (error) => {
            this.setState({ isAckPositive: false, ackMessage: error })
            console.log(error);
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
            <div className="container myorganization">
                {redirectVar}
                <Navbar></Navbar>
                <div className="row create-row">
                    {this.renderAcknowledgement()}
                    <button type="button" class="btn btn-dark btn-lg" onClick={this.openModal.bind(this)}>
                        <span class="glyphicon glyphicon-plus"></span> Create Organization
                    </button>
                </div>
                <div className="row myorg">
                    {this.renderOrganizations()}
                </div>
                {this.renderCreateModal()}
            </div>
        );
    }

    renderOrganizations() {
        let views = []
        this.state.myOrganizations.map((org, index) => {
            views.push(
                <Collapsible trigger={org.name}>
                    {this.renderRequest(org.membershipRequest)}
                </Collapsible>
            );
        });
        return views;
    }

    renderRequest(requests) {
        let views = [];
        if (requests.length == 0) {
            return (
                <div className="request-row">
                    <h5>No Request Available</h5>
                </div>
            );
        }
        requests.map((request, index) => {
            let pReq = { email: request.email, isApproved: true };
            let nReq = { email: request.email, isApproved: false };
            views.push(
                <div className="request-row">
                    <h5>{request.name}</h5>
                    <h5>{request.email}</h5>
                    <button class="btn btn-success" onClick={this.respondRequest.bind(this, pReq)}>
                        <span class="glyphicon glyphicon-ok"></span> Accept
                    </button>
                    <button href="#" class="btn btn-danger" onClick={this.respondRequest.bind(this, nReq)}>
                        <span class="glyphicon glyphicon-remove"></span> Decline
                    </button>
                </div>
            );
        });
        return views;
    }

    renderCreateModal() {
        return (
            <Modal show={this.state.modalIsOpen}
                onHide={this.closeModal.bind(this)}
                aria-labelledby="ModalHeader">
                <form onSubmit={this.createOrg.bind(this)}>
                    <Modal.Header closeButton>
                        <Modal.Title id='ModalHeader'>Create Your Organization</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <div class="form-group-md">
                            <label>Organization Name <span className="required"> *</span></label>
                            <input type="text" class="form-control form-control-md" onChange={this.onChange} name="name" value={this.state.name} placeholder="Name" required />
                        </div>
                        <div class="form-group-md">
                            <label>Description<span className="required"> *</span></label>
                            <input type="text" class="form-control form-control-md" onChange={this.onChange} name="description" value={this.state.description} placeholder="Description" required />
                        </div>
                        <div class="form-group-md">
                            <label>Address<span className="required"> *</span></label>
                            <input type="text" class="form-control form-control-md" onChange={this.onChange} name="address" value={this.state.address} placeholder="Address" required />
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <Modal.Dismiss className='btn btn-default'>Cancel</Modal.Dismiss>
                        <button type="submit" className='btn btn-primary'>Create</button>
                    </Modal.Footer>
                </form>
            </Modal>
        )
    }

    onChange = (event) => {
        this.setState({ [event.target.name]: event.target.value })
    }

    openModal() {
        this.setState({ modalIsOpen: true });
    }

    closeModal() {
        this.setState({ modalIsOpen: false });
    }

    createOrg(event) {
        event.preventDefault();
        let { name, address, description } = this.state;
        let user = JSON.parse(localStorage.getItem(AppConstants.USER_DETAILS));
        let req = { name, address, description, email: user.email }
        WebService.getInstance().createOrganization(req, (response) => {
            console.log(response);
            if (response.success) {
                this.getOrganizations();
                this.closeModal();
                this.setState({ name: null, description: null, address: null });
            }
        }, (error) => {
            console.log(error);
        })
    }

    respondRequest(req) {
        WebService.getInstance().respondRequest(req, (response) => {
            if (response.success) {
                this.getOrganizations();
                this.setState({ isAckPositive: true, ackMessage: response.message })
            }
            else {
                this.setState({ isAckPositive: false, ackMessage: response.message })
            }
        }, (error) => {
            this.setState({ isAckPositive: false, ackMessage: error })
        })
    }

}