import React, { useContext, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { AuthContext } from './AuthContext';
import axios from 'axios';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const history = useHistory();
  const { handleLogin } = useContext(AuthContext);

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
      <div className="content">
        <div className="loginContent">
          <div className="ctop">
            <div className="user-menu">
              <span className="username">Kullanıcı Adı:</span> <br />
              <input
                className="placeholder-input"
                type="email"
                id="email"
                placeholder="Kullanıcı adınızı giriniz."
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              <br />
              <br />
              <span className="password">Şifre:</span> <br />
              <input
                className="placeholder-input"
                type="password"
                id="password"
                placeholder="Şifrenizi giriniz."
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              <br />
              <br />
              <div className="cbottom">
                <button onClick={handleLoginFormSubmit}>GİRİŞ</button>
                <button onClick={handleRegisterButtonClick}>KAYIT OL</button> {/* Added register button */}
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  );
};

export default Login;
