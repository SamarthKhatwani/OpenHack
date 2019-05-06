import React, { Component } from 'react';
import './Navbar.css';
import {Link} from 'react-router-dom';
//create the Navbar Component
class Navbar extends Component {

    render() {
        return (
            <div>
                <nav className="navbar-expand-sm bg-color navbar-dark">
                    <div className="container-fluid">
                        <div className="navbar-header">
                        <Link to="/"> <span className="navbar-brand header-white">Open Hack</span></Link>
                        </div>
                        <div className="navbar-fixed-right">
                            <div class="nav-item dropdown pull-right">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <span class="glyphicon glyphicon-user"></span>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                    <span class="dropdown-item">My Profile</span><br/><br/>
                                    <span class="dropdown-item">Logout</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        )
    }
}

// <br /><br /><Link to="/viewProfile"><span class="dropdown-item">My Profile</span></Link><br />
// <br /><Link to="/inbox"><span class="dropdown-item">Inbox</span></Link><br /><br />

export default Navbar;