// AuthContext.js
import React, { createContext, useState } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isLoggedIn, setLoggedIn] = useState(false);
  const [userid, setUserid] = useState('');

  const handleLogin = (userid) => {
    setLoggedIn(true);
    setUserid(userid);
  };

  const handleLogout = () => {
    setLoggedIn(false);
    setUserid('');
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, userid, handleLogin, handleLogout }}>
      {children}
    </AuthContext.Provider>
  );
};
