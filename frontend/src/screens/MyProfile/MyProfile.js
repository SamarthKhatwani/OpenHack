import React, { Component, Fragment } from 'react';
import './MyProfile.css';
import { Link, Redirect } from 'react-router-dom';
import AppConstants from "../../constants/AppConstants";
import Navbar from '../Navbar/Navbar';
import WebService from '../../services/WebService';
import Autocomplete from 'react-autocomplete';
export default class MyProfile extends Component {
    constructor(props) {
        super(props);
        this.state = {
            ackMessage: null,
            isAckPositive: null,
            isFormDirty: false,
            organizationList: [],
            displayOrganization: '',
            userProfile: {
                name: '',
                address: '',
                email: '',
                screenName: "",
                businessTitle: '',
                aboutMe: '',
                organization: "",
                portraitUrl: null
            },
        };
    }

    componentDidMount() {
        WebService.getInstance().getProfile((response) => {
            console.log(response);
            if (response.success) {
                this.setState({
                    userProfile: response,
                    displayOrganization: response.organization ? response.organization : ''
                });
            }
        }, (error) => {
            console.log(error);
        });
        WebService.getInstance().searchOrganization((response) => {
            console.log(response);
            if (response.success) {
                this.setState({ organizationList: response.organizationNames });
            }
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
                        <h1>{this.state.userProfile.name}</h1>
                    </div>
                    <div class="row profile-information">
                        <div class="col-9-lg">
                            <h2>Profile Information</h2><br />
                            <form onSubmit={this.handleSubmit.bind(this)}>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="name" value={this.state.userProfile.name} placeholder="Name" required />
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="screenName" value={this.state.userProfile.screenName} placeholder="Screen Name" required disabled />
                                </div>
                                <div class="form-group">
                                    <textarea class="form-control form-control-lg" rows="4" onChange={this.onChange} name="aboutMe" value={this.state.userProfile.aboutMe} placeholder="About me"></textarea>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="email" value={this.state.userProfile.email} placeholder="Email" disabled />
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="businessTitle" value={this.state.userProfile.businessTitle} placeholder="Business Title" />
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="address" value={this.state.userProfile.address} placeholder="Address" />
                                </div>
                                <div class="form-group">
                                    <Autocomplete
                                        wrapperStyle={{ width: '100%' }}
                                        inputProps={{ className: "autoinput", name: "organization", placeholder: "Organization" }}
                                        getItemValue={(item) => item}
                                        items={this.state.organizationList}
                                        renderItem={(item, isHighlighted) =>
                                            <div style={{ background: isHighlighted ? 'lightgray' : 'white', padding: '5px' }}>
                                                {item}
                                            </div>
                                        }
                                        renderInput={(props) =>
                                            <div className="form-control form-control-lg">
                                                <input {...props} />
                                                {this.state.userProfile.organization ?
                                                    <span className={this.state.userProfile.organizationApprovalStatus ? "glyphicon glyphicon-ok" : "glyphicon glyphicon-time"}></span>
                                                    : null}
                                            </div>
                                        }
                                        value={this.state.userProfile.organization ? this.state.userProfile.organization : ''}
                                        onSelect={(item) => {
                                            let userProfile = Object.assign(this.state.userProfile, { organization: item });
                                            this.setState({ userProfile, isFormDirty: true, ackMessage: null });
                                        }}
                                        onChange={this.onChange}
                                    />
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="portraitUrl" value={this.state.userProfile.portraitUrl} placeholder="Portrait URL" />
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
        console.log(event.target.name);
        let userProfile = null;
        if (event.target.name == 'organization') {
            userProfile = Object.assign(this.state.userProfile, { organization: event.target.value.length > 0 ? event.target.value : null });
        }
        else {
            userProfile = Object.assign(this.state.userProfile, { [event.target.name]: event.target.value });
        }
        this.setState({ userProfile, isFormDirty: true, ackMessage: null });
    }

    handleSubmit(event) {
        event.preventDefault();
        WebService.getInstance().updateProfile(this.state.userProfile, (response) => {
            console.log(response);
            if (response.success) {
                this.setState({ userProfile: response, ackMessage: response.message, isAckPositive: true })
            }
            else {
                this.setState({ ackMessage: response.message, isAckPositive: false })
            }
        }, (error) => {
            console.log(error);
            this.setState({ ackMessage: error, isAckPositive: false })
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