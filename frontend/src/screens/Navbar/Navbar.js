import React, { Component, Fragment } from 'react';
import './Navbar.css';
import { Link } from 'react-router-dom';
import AppConstants from '../../constants/AppConstants';
import Firebase from '../../services/FirebaseService';
import { history } from '../../router/history';
//create the Navbar Component
class Navbar extends Component {

    constructor(props) {
        super(props);
        this.user = JSON.parse(localStorage.getItem(AppConstants.USER_DETAILS));
    }

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
                <Fragment>
                   { !this.user.admin ?
                       <div class="nav-item item-link">
                            <Link to="/myJudgement"><a class="nav-link">My Judgements</a></Link>
                        </div>
                        : null
                    }
                    <div class="nav-item item-link">
                        <Link to="/myOrganization"><a class="nav-link">My Organizations</a></Link>
                    </div>
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
                </Fragment>
            );
        }
    }

    logout() {
        Firebase.getInstance().signOut().then(() => {
            localStorage.clear();
            history.push('/');
        });
    }
}


// <br /><br /><Link to="/viewProfile"><span class="dropdown-item">My Profile</span></Link><br />
// <br /><Link to="/inbox"><span class="dropdown-item">Inbox</span></Link><br /><br />

export default Navbar;