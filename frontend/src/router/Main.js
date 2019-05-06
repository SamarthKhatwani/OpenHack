import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import Home from '../screens/Home/Home';
import Dashboard from '../screens/Dashboard/Dashboard';
// import ViewProfile from '../views/ViewProfile/ViewProfile';
// import EditProfile from '../views/EditProfile/EditProfile';
// import OwnerLogin from '../views/OwnerLogin/OwnerLogin';
// import OwnerSignUp from '../views/OwnerSignUp/OwnerSignUp';
// import PostProperty from '../views/PostProperty/PostProperty';
// import PropertyListing from '../views/PropertyListing/PropertyListing';
// import ViewProperty from '../views/ViewProperty/ViewProperty';
// import Inbox from '../views/Inbox/Inbox';
// import Conversation from '../views/Conversation/Conversation';
//Create a Main Component
class Main extends Component {
    render() {
        return (
            <div>
                {/*Render Different Component based on Route*/}
                <Route exact path="/" component={Home} />
                <Route path="/dashboard" component={Dashboard}/>                
            </div>
        )
    }
}
// <Route path="/login" component={Login}/>
// <Route path="/ownerLogin" component={OwnerLogin}/>
// <Route path="/signUp" component={SignUp}/>
// <Route path="/ownerSignUp" component={OwnerSignUp}/>
// <Route path="/viewProfile" component={ViewProfile}/>
// <Route path="/editProfile" component={EditProfile}/>
// <Route path="/postProperty" component={PostProperty}/>
// <Route path="/searchProperty" component={PropertyListing}/>
// <Route path="/viewProperty" component={ViewProperty}/>
// <Route path="/inbox" component={Inbox}/>
// <Route path="/conversation" component={Conversation}/>
//Export The Main Component
export default Main;