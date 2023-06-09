import React from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import { AuthProvider } from './AuthContext';
import Login from './Login';
import Home from './Home';

const App = () => {
  return (
    <AuthProvider>
      <Router>
          <Switch>
            <Route exact path="/">
              <Login />
            </Route>
            <Route path="/home">
              <Home />
            </Route>
            <Redirect to="/" />
          </Switch>
      </Router>
    </AuthProvider>
  );
};

export default App;


