import React,{Component} from 'react';
import {Route} from 'react-router-dom';
import Landing from './Login/Landing.js';
import Album from './Dashboard/Album.js';
import Pricing from './Dashboard/Pricing.js';
import SignUpPage from './SignUpPage/index.js';
import * as ROUTES from '../constants/routes';
class Main extends Component{
    constructor(){
        super();
    }

    render(){
        return(
            <div>
                { /*Render Different Component based on Route*/ }            
                <Route path="/home" component={Landing}/> 
                <Route path="/dashboard" component={Pricing}/>
                <Route path={ROUTES.SIGN_UP} component={SignUpPage}/>
            
            </div>
        )
    }
}

export default Main;