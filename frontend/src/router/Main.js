import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import Home from '../screens/Home/Home';
import Dashboard from '../screens/Dashboard/Dashboard';
import MyProfile from '../screens/MyProfile/MyProfile';
import MyOrganization from '../screens/MyOrganization/MyOrganization';
import CreateHackathon from '../screens/CreateHackathon/CreateHackathon';
import Payment from '../screens/Payment/Payment';
import HackathonDetail from '../screens/HackathonDetail/HackathonDetail';
import MyJudgement from '../screens/MyJudgement/MyJudgement';
import LeaderBoard from '../screens/LeaderBoard/LeaderBoard';
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
                <Route path="/createHackathon" component={CreateHackathon}/>        
                <Route path="/payment" component={Payment}/>       
                <Route path="/detail" component={HackathonDetail}/> 
                <Route path="/myJudgement" component={MyJudgement}/> 
                <Route path="/leaderBoard" component={LeaderBoard}/>                
            </div>
        )
    }
}

//Export The Main Component
export default Main;