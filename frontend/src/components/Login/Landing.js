import React,{Component} from 'react';
import Button from '@material-ui/core/Button';
import {Link} from 'react-router-dom';

class Landing extends Component{
    constructor(){
        super();
    }

    render(){
        return(
            <div>
                <h1> Open Hack</h1>
                <p>Participate in the world's biggest hackathons and hack your way to glory..</p>
                <Link to="/dashboard">
                <Button variant="contained" color="primary">
                    Login
                </Button>
                </Link>
            </div>
                
        );
    }
}

export default Landing;