import React, { Component, Fragment } from 'react';
import './Home.css';
import { Link } from 'react-router-dom';
import AppConstants from "../../constants/AppConstants";
import Navbar from '../Navbar/Navbar';
import Firebase from '../../services/FirebaseService';
import { history } from '../../router/history';
import WebService from '../../services/WebService';

export default class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            password: '',
            username: '',
            isLoginMode: true,
            loginFailed: false,
            errorMessage: null
        };
        this.handleLoginSubmit = this.handleLoginSubmit.bind(this);
        this.handleSignupSubmit = this.handleSignupSubmit.bind(this);
        this.handleGoogleLogin = this.handleGoogleLogin.bind(this);
    }

    onChange = (event) => {
        if (this.state.loginFailed) {
            this.setState({ [event.target.name]: event.target.value, loginFailed: false });
        }
        else {
            this.setState({ [event.target.name]: event.target.value });
        }
    }

    renderFailedLogin() {
        if (this.state.loginFailed) {
            return (
                <div class="alert alert-danger" role="alert">
                    {this.state.errorMessage}
                </div>
            );
        }
    }

    // samsetional74@SpeechGrammarList.com
    // SamSham46

    render() {
        return (
            <div className="container login">
                <Navbar></Navbar>
                <div className="row login-main">
                    {this.renderFailedLogin()}
                    <div className="loginContainer">
                        <h1>Open Hack</h1>
                        <h4 onClick={() => { this.setState({ isLoginMode: !this.state.isLoginMode }) }}>{this.state.isLoginMode ? 'Need a account?' : 'Already have an account?'} <a>{this.state.isLoginMode ? 'SignUp' : 'Login'}</a></h4>
                        <div className="formContainer">
                            <div class="formHeader">
                                <span className="formHeading">{this.state.isLoginMode ? 'Account Login' : 'Signup'}</span>
                            </div>
                            {this.state.isLoginMode ? this.renderLogin() : this.renderSignup()}
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    renderLogin() {
        return (
            <Fragment>
                <form onSubmit={this.handleLoginSubmit}>
                    <div class="form-group">
                        <input type="email" class="form-control form-control-lg" onChange={this.onChange} name="username" placeholder="Email Address" required />
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control form-control-lg" onChange={this.onChange} name="password" placeholder="Password" minLength="5" required />
                    </div>
                    <div class="form-group"><a href="#">Forget Password ?</a></div>
                    <div class="form-check form-check-inli1ne">
                        <input class="form-check-input" type="checkbox" id="keepSignIn" value="" />   Keep me signed in
                    </div>
                    <br />
                    <button type="submit" class="btn btn-success btn-lg btn-block">Login</button>
                </form>
                <button type="button" class="google-button" onClick={this.handleGoogleLogin}>
                    <span class="google-button__icon">
                        <svg viewBox="0 0 366 372" xmlns="http://www.w3.org/2000/svg"><path d="M125.9 10.2c40.2-13.9 85.3-13.6 125.3 1.1 22.2 8.2 42.5 21 59.9 37.1-5.8 6.3-12.1 12.2-18.1 18.3l-34.2 34.2c-11.3-10.8-25.1-19-40.1-23.6-17.6-5.3-36.6-6.1-54.6-2.2-21 4.5-40.5 15.5-55.6 30.9-12.2 12.3-21.4 27.5-27 43.9-20.3-15.8-40.6-31.5-61-47.3 21.5-43 60.1-76.9 105.4-92.4z" id="Shape" fill="#EA4335" /><path d="M20.6 102.4c20.3 15.8 40.6 31.5 61 47.3-8 23.3-8 49.2 0 72.4-20.3 15.8-40.6 31.6-60.9 47.3C1.9 232.7-3.8 189.6 4.4 149.2c3.3-16.2 8.7-32 16.2-46.8z" id="Shape" fill="#FBBC05" /><path d="M361.7 151.1c5.8 32.7 4.5 66.8-4.7 98.8-8.5 29.3-24.6 56.5-47.1 77.2l-59.1-45.9c19.5-13.1 33.3-34.3 37.2-57.5H186.6c.1-24.2.1-48.4.1-72.6h175z" id="Shape" fill="#4285F4" /><path d="M81.4 222.2c7.8 22.9 22.8 43.2 42.6 57.1 12.4 8.7 26.6 14.9 41.4 17.9 14.6 3 29.7 2.6 44.4.1 14.6-2.6 28.7-7.9 41-16.2l59.1 45.9c-21.3 19.7-48 33.1-76.2 39.6-31.2 7.1-64.2 7.3-95.2-1-24.6-6.5-47.7-18.2-67.6-34.1-20.9-16.6-38.3-38-50.4-62 20.3-15.7 40.6-31.5 60.9-47.3z" fill="#34A853" /></svg>
                    </span>
                    <span class="google-text">Sign in with Google</span>
                </button>
            </Fragment>
        );
    }

    renderSignup() {
        return (
            <form onSubmit={this.handleSignupSubmit}>
                <div class="form-group">
                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="name" placeholder="Name" required />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control form-control-lg" onChange={this.onChange} name="screenName" placeholder="Screen Name" required />
                </div>
                <div class="form-group">
                    <input type="email" class="form-control form-control-lg" onChange={this.onChange} name="email" placeholder="Email address" required />
                </div>
                <div class="form-group">
                    <input type="password" class="form-control form-control-lg" onChange={this.onChange} name="password" placeholder="Password" required minLength="6" />
                </div>
                <button type="submit" class="btn btn-success btn-lg btn-block">Sign Me Up</button>
            </form>
        );

    }

    handleGoogleLogin() {
        Firebase.getInstance().signInWithGoogle().then(socialAuthUser => {
            console.log(socialAuthUser);
            if (socialAuthUser.additionalUserInfo.isNewUser) {
                socialAuthUser.user.sendEmailVerification();
                // Server Signup
                WebService.getInstance().signUp({email:socialAuthUser.user.email, screenName: socialAuthUser.user.email, name:socialAuthUser.user.displayName}, (response) => {
                    console.log(response);
                    if(response.success){
                        alert('Sign Up Successfully. Please verify email and then login.');
                    }
                }, (error) => {
                    console.log(error);
                    this.setState({ errorMessage: error.message, loginFailed: true })
                })
            }
            else if (!socialAuthUser.user.emailVerified) {
                this.setState({ errorMessage: 'Please Verify Email First!', loginFailed: true });
            }
            else {
                alert('Login');
                // localStorage.setItem(AppConstants.AUTH_TOKEN, socialAuthUser.credential.idToken);
                // history.push('/dashboard') 
            }
        }).catch(error => {
            console.log(error);
            this.setState({ errorMessage: error.message, loginFailed: true })
        });
    }

    handleSignupSubmit(event) {
        event.preventDefault();
        const { email, password, screenName, name } = this.state;
        WebService.getInstance().screenNameLookup(screenName, (response) => {
            if (!response.success) {
                Firebase.getInstance().signUp(email, password).then(authUser => {
                    console.log(authUser);
                    if (authUser.additionalUserInfo.isNewUser) {
                        authUser.user.sendEmailVerification();
                        WebService.getInstance().signUp({email, screenName, name}, (response) => {
                            console.log(response);
                            if(response.success){
                                alert('Sign Up Successfully. Please verify email and then login.');
                            }
                        }, (error) => {
                            console.log(error);
                            this.setState({ errorMessage: error.message, loginFailed: true })
                        })
                    }
                }).catch(error => {
                    console.log(error);
                    this.setState({ errorMessage: error.message, loginFailed: true })
                });
            }
            else {
                this.setState({ errorMessage: response.message, loginFailed: true })
            }
        }, (error) => {
            console.log(error);
        })
    }

    handleLoginSubmit(event) {
        event.preventDefault();
        const { username, password } = this.state;
        Firebase.getInstance().login(username, password).then(authUser => {
            console.log('Login');
            if (!authUser.user.emailVerified) {
                this.setState({ errorMessage: 'Please Verify Email First!', loginFailed: true });
            }
            else {
                Firebase.getInstance().auth.currentUser.getIdToken().then((token) => {
                    localStorage.setItem(AppConstants.AUTH_TOKEN, token);
                    // history.push('/dashboard') 
                });
            }
        }).catch(error => {
            console.log(error);
            this.setState({ errorMessage: error.message, loginFailed: true });
        });
    }
}
