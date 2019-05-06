import React, {Component} from 'react';
import './App.css';
import Main from './router/Main';
import { Router } from 'react-router-dom';
import { history } from "./router/history";

//App Component
class App extends Component {
  render() {
    return (
      //Use Router to route to different pages
      <Router history={history}>
          {/* App Component Has a Child Component called Main*/}
          <Main/>
      </Router>
    );
  }
}
//Export the App component so that it can be used in index.js
export default App;
