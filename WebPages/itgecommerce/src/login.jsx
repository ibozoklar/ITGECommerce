import React, { useState } from 'react';
import Header from "./components/header";
import LoginContent from "./components/loginContent";
import { Fragment } from "react";

export default function Login({ onLogin }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleLogin = () => {
    // E-posta ve şifre doğrulaması yapılacak

    if (username === "correctEmail" && password === "correctPassword") {
      // E-posta ve şifre doğruysa onLogin fonksiyonunu çağırarak loggedIn değerini true olarak ayarlayın
      onLogin();
    } else {
      setErrorMessage("E-posta veya şifre yanlış");
    }
  };

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
    setErrorMessage("");
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
    setErrorMessage("");
  };


  return (
    <Fragment>
      <Header />
      <LoginContent />
    </Fragment>
  );
}