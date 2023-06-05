import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import './assets/styles/index.css';
import './assets/styles/login.css';
import App from './app';
import Login from './login';

const rootEl = document.getElementById('root');

const Root = () => {
  const [loggedIn, setLoggedIn] = useState(false);

  const handleLogin = () => {
    // E-posta ve şifre doğruysa loggedIn değerini true olarak güncelleyin
    setLoggedIn(true);
  };

  return loggedIn ? <App /> : <Login onLogin={handleLogin} />;
};

ReactDOM.createRoot(rootEl).render(<Root />);
