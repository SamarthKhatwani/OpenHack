import React, { Component } from 'react';
import './Navbar.css';
import { Link } from 'react-router-dom';
import AppConstants from '../../constants/AppConstants';
import Firebase from '../../services/FirebaseService';
import { history } from '../../router/history';
//create the Navbar Component
class Navbar extends Component {

    render() {
        return (
            <div>
                <nav className="navbar-expand-sm bg-color navbar-dark">
                    <div className="container-fluid">
                        <div className="navbar-header">
                            <Link to="/dashboard"> <span className="navbar-brand header-white">Open Hack</span></Link>
                        </div>
                        <div className="navbar-fixed-right">
                            {this.renderMenu()}
                        </div>
                    </div>
                </nav>
            </div>
        )
    }
    renderMenu() {
        if (localStorage.getItem(AppConstants.AUTH_TOKEN)) {
            return (
                <div class="nav-item dropdown pull-right">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <span class="glyphicon glyphicon-user"></span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <Link to="/myProfile"><span class="dropdown-item">My Profile</span></Link>
                    <br /><br />
                        <span class="dropdown-item" onClick={this.logout.bind(this)}>Logout</span>
                    </div>
                </div>
            );
        }
    }

    logout(){
        Firebase.getInstance().signOut().then(()=>{
            localStorage.clear();
            history.push('/');
        });
    }
}


// <br /><br /><Link to="/viewProfile"><span class="dropdown-item">My Profile</span></Link><br />
// <br /><Link to="/inbox"><span class="dropdown-item">Inbox</span></Link><br /><br />

export default Navbar;