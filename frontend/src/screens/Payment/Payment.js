import React, { Component, Fragment } from 'react';
import './Payment.css';
import { Link, Redirect } from 'react-router-dom';
import AppConstants from "../../constants/AppConstants";
import Navbar from '../Navbar/Navbar';
import WebService from '../../services/WebService';
import { history } from '../../router/history';
const queryString = require('query-string');

export default class Payment extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: null,
            eventName: null,
            originalPrice: '--',
            discount: '--',
            discountedPrice: '--',
            quotation: null
        };

        this.makePayment=this.makePayment.bind(this);
    }

    componentDidMount() {
        console.log((window.location.search));
        let query = queryString.parse(window.location.search);
        this.setState(query,()=>{
            WebService.getInstance().fetchEventPrice(this.state.email, this.state.eventName, (response)=>{
                if(response.success){
                    this.setState({
                        originalPrice: response.originalPrice,
                        discountPercent: response.discountPercent,
                        discount: response.discount,
                        discountedPrice: response.discountedPrice,
                        quotation:response
                    })
                }
                else{
                    alert(response.message);
                }
            },(error)=>{
                console.log(error);
            });
        });
    }

    makePayment() {
        WebService.getInstance().makePayment({...this.state.quotation, email: this.state.email},(response)=>{
            if(response.success){
                alert("Payment Successfull");
                history("/dashboard");
            }
            else{
                alert(response.message);
            }
        },(error)=>{
            console.log(error);
            alert(error);
        });
    }

    render() {
        let redirectVar = null;
        if (!localStorage.getItem(AppConstants.AUTH_TOKEN)) {
            redirectVar = <Redirect to="/" />
        }
        return (
            <div className="container payment">
                {redirectVar}
                <Navbar></Navbar>
                <div class="container rajat_container">
                    <div class="rajat_hacakathon_bill_container">
                        <div class="rajat_hackathon_bill">
                            <h2>Open Hack</h2>
                            <div class="rajat_hackathon_bill_details">
                                <div class="row">
                                    <div class="col-sm-6 bill-left">
                                        <b><span class="bill_element">{this.state.eventName}</span></b>
                                    </div>
                                    <div class="col-sm-6 bill-right">
                                        <span class="bill_element">$ {this.state.originalPrice} </span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6 bill-left">
                                        <span class="bill_element">Discount %</span>
                                    </div>
                                    <div class="col-sm-6 bill-right">
                                        <span class="bill_element">{this.state.discountPercent+'%'}</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6 bill-left">
                                        <span class="bill_element">Discount Amount</span>
                                    </div>
                                    <div class="col-sm-6 bill-right">
                                        <span class="bill_element">{this.state.discount}</span>
                                    </div>
                                </div>
                                <div class="row bill_total_row">
                                    <div class="col-sm-6 bill-left">
                                        <b><span class="bill_element">Total</span></b>
                                    </div>
                                    <div class="col-sm-6 bill-right">
                                        <b><span class="bill_element">${parseInt(this.state.discountedPrice)}</span></b>
                                    </div>
                                </div>
                            </div>
                            <button class="btn rajat_register" onClick={this.makePayment.bind(this)}>Pay</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
 }



