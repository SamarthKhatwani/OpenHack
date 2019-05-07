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
            myOrganizations: [1, 2, 3, 4, 5]
        }
    }

    componentDidMount() {

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
                    <a class="btn btn-info btn-lg" onClick={this.openModal.bind(this)}>
                        <span class="glyphicon glyphicon-plus"></span> Create Organization
                    </a>
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
                <Collapsible trigger="Start here">
                    <p>This is the collapsible content. It can be any element or React component you like.</p>
                    <p>It can even be another Collapsible component. Check out the next section!</p>
                </Collapsible>
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
                this.closeModal()
            }
        }, (error) => {
            console.log(error);
        })
    }

}