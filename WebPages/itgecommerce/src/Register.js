import React, { useState } from 'react';
import axios from 'axios';
import { Link, useHistory } from 'react-router-dom';
import LanguageSwitcher from './LanguageSwitcher';
import { useTranslation } from 'react-i18next'; // Import the useTranslation hook



const Register = () => {
  const [email, setEmail] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const history = useHistory();
  const { t } = useTranslation(); // Access the translation function


  const handleRegisterFormSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:9092/auth/register', {
        email,
        firstName,
        lastName,
      });
      console.log(response.data); // Assuming the server returns a success message
      // Perform any other actions upon successful registration
      history.push('/'); // Redirect to the login page
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="register-container">
      <div className="register-form">
        <h3>{t('register.title')}</h3> {/* Use the translated string */}
        <form onSubmit={handleRegisterFormSubmit}>
          <div className="form-group">
            <label htmlFor="register-email">{t('register.emailLabel')}</label> {/* Use the translated string */}
            <input
              type="email"
              id="register-email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="register-firstName">{t('register.firstNameLabel')}</label> {/* Use the translated string */}
            <input
              type="text"
              id="register-firstName"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="register-lastName">{t('register.lastNameLabel')}</label> {/* Use the translated string */}
            <input
              type="text"
              id="register-lastName"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
            />
          </div>
          <button type="submit">{t('register.registerButton')}</button> {/* Use the translated string */}
        </form>
        <Link to="/">{t('register.loginLink')}</Link> {/* Use the translated string */}
      </div>
      <LanguageSwitcher />
    </div>
  );
};

export default Register;