import React, { useContext, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { AuthContext } from './AuthContext';
import axios from 'axios';
import { useTranslation } from 'react-i18next'; // Import the useTranslation hook
import LanguageSwitcher from './LanguageSwitcher';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const history = useHistory();
  const { handleLogin } = useContext(AuthContext);
  const { t } = useTranslation(); // Access the translation function

  const handleLoginFormSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:9092/auth/login', {
        email,
        password,
      });
      console.log(response.data); // Assuming the server returns a success message with user data
      // Perform any other actions upon successful login
      if (response.data) {
        handleLogin(response.data); // Invoke handleLogin with the firstName
        history.push('/home'); // Redirect to the homepage
      }
    } catch (error) {
      console.error(error);
    }
  };

  const handleRegisterButtonClick = () => {
    history.push('/Register'); // Redirect to the register page
  };

  return (
    <main>
      <div className="language-switcher">
        <LanguageSwitcher />
      </div>
      <div className="content">
        <div className="loginContent">
          <div className="ctop">
            <div className="user-menu">
              <span className="username">{t('login.username')}:</span> <br /> {/* Translate the text */}
              <input
                className="placeholder-input"
                type="email"
                id="email"
                placeholder={t('login.emailPlaceholder')} // Translate the text
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              <br />
              <br />
              <span className="password">{t('login.password')}:</span> <br /> {/* Translate the text */}
              <input
                className="placeholder-input"
                type="password"
                id="password"
                placeholder={t('login.passwordPlaceholder')} // Translate the text
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              <br />
              <br />
              <div className="cbottom">
                <button onClick={handleLoginFormSubmit}>{t('login.loginButton')}</button> {/* Translate the text */}
                <button onClick={handleRegisterButtonClick}>{t('login.registerButton')}</button> {/* Translate the text */}
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  );
};

export default Login;
