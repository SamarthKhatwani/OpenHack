import React, { Component, Fragment } from 'react';
import './MyProfile.css';
import { Link, Redirect } from 'react-router-dom';
import AppConstants from "../../constants/AppConstants";
import Navbar from '../Navbar/Navbar';
import WebService from '../../services/WebService';

export default class MyProfile extends Component {
    constructor(props) {
        super(props);
        this.state = {
            ackMessage: null,
            isAckPositive: null,
            isFormDirty: false,
            userProfile: {
                user_aboutme: '',
                user_city: '',
                user_company: '',
                user_first_name: "",
                user_hometown: '',
                user_languages: '',
                user_last_name: "",
                user_school: '',
                user_gender: "Male",
                user_phone_number: "",
                profilePictureUrl: null
            },
        };
    }

    componentDidMount() {
        WebService.getInstance().getProfile((response) => {
            console.log(response);
        }, (error) => {
            console.log(error);
        });
    }

    render() {
        let redirectVar = null;
        if (!localStorage.getItem(AppConstants.AUTH_TOKEN)) {
            redirectVar = <Redirect to="/" />
        }
        return (
            <div className="container myprofile">
                {redirectVar}
                <Navbar></Navbar>
                <div class="profile-container">
                    {this.renderAcknowledgement()}
                    <div class="row profile-pic">
                        {/*this.renderProfilePicture()*/}
                        {/* <img class="rounded-circle" height="100" width="100" src={profile}/> */}
                        <h1>{this.state.userProfile.user_first_name + " " + this.state.userProfile.user_last_name}</h1>
                    </div>
                    <div class="row profile-information">
                        <div class="col-9-lg">
                            <h2>Profile Information</h2><br />
                            <form onSubmit={this.handleSubmit.bind(this)}>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="user_first_name" value={this.state.userProfile.user_first_name} placeholder="First Name" required />
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="user_last_name" value={this.state.userProfile.user_last_name} placeholder="Last Name" required />
                                </div>
                                <div class="form-group">
                                    <textarea class="form-control form-control-lg" rows="4" onChange={this.onChange} name="user_aboutme" value={this.state.userProfile.user_aboutme} placeholder="About me"></textarea>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="user_city" value={this.state.userProfile.user_city} placeholder="City" />
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="user_company" value={this.state.userProfile.user_company} placeholder="Company" />
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="user_school" value={this.state.userProfile.user_school} placeholder="School" />
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="user_hometown" value={this.state.userProfile.user_hometown} placeholder="Hometown" />
                                </div>
                                <div class="form-group">
                                    <select class="form-control" name="user_gender" value={this.state.userProfile.user_gender} onChange={this.onChange}>
                                        <option value="Male">Male</option>
                                        <option value="Female">Female</option>
                                        <option value="Other">Other</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="user_languages" value={this.state.userProfile.user_languages} placeholder="Languages" />
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" pattern="^\d{3}-\d{3}-\d{4}$" onChange={this.onChange} name="user_phone_number" value={this.state.userProfile.user_phone_number} placeholder="Phone Number (xxx-xxx-xxx)" />
                                </div>
                                <div class="form-group save-button">
                                    <button type="submit" class="btn btn-primary btn-lg" disabled={!this.state.isFormDirty}>Save Changes</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    onChange = (event) => {
        // if (event.target.name == 'profilePicture') {
        //     this.uploadProfilePicture(event.target.files[0]);
        // }
        // else {
        //     let userProfile = Object.assign(this.state.userProfile, { [event.target.name]: event.target.value });
        //     this.setState({ userProfile, isFormDirty: true });
        // }
    }

    handleSubmit(event) {
        event.preventDefault();

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


    renderProfilePicture() {
        // let profilePicture = this.state.userProfile.user_pic_url ? this.state.userProfile.user_pic_url : profile;
        // return (
        //     <div class="avatar-upload">
        //         <div class="avatar-preview">
        //             <div id="imagePreview">
        //                 <img src={profilePicture} style={{ 'height': "inherit", 'width': "inherit", 'border-radius': '100%' }} />
        //             </div>
        //         </div>
        //     </div>
        // );
    }
}