import React, { Component, Fragment } from 'react';
import './Dashboard.css';
import { Link, Redirect } from 'react-router-dom';
import AppConstants from "../../constants/AppConstants";
import Navbar from '../Navbar/Navbar';

export default class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
          
        };
    }

    render(){
        let redirectVar = null;
        if(!localStorage.getItem(AppConstants.AUTH_TOKEN)){
            redirectVar = <Redirect to= "/"/>
        }
        return(
            <div className="container dashboard">
                {redirectVar}
                <Navbar></Navbar>
            </div>
        );
    }
}