import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import Home from '../screens/Home/Home';
import Dashboard from '../screens/Dashboard/Dashboard';
import MyProfile from '../screens/MyProfile/MyProfile';
import MyOrganization from '../screens/MyOrganization/MyOrganization';
//Create a Main Component
class Main extends Component {
    render() {
        return (
            <div>
                {/*Render Different Component based on Route*/}
                <Route exact path="/" component={Home} />
                <Route path="/dashboard" component={Dashboard}/>   
                <Route path="/myProfile" component={MyProfile}/>  
                <Route path="/myOrganization" component={MyOrganization}/>                
            </div>
        )
    }
}

//Export The Main Component
export default Main;