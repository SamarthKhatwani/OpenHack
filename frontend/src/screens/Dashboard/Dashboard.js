import React, { Component, Fragment } from 'react';
import './Dashboard.css';
import { Link } from 'react-router-dom';
import AppConstants from "../../constants/AppConstants";
import Navbar from '../Navbar/Navbar';

export default class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
          
        };
    }

    render(){
        return(
            <div className="container dashboard">
                <Navbar></Navbar>
            </div>
        );
    }
}