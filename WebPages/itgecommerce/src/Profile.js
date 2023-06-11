import React, { useContext,useState } from 'react';
import axios from 'axios';
import { AuthContext } from './AuthContext';
import { useTranslation } from 'react-i18next'; // Import the useTranslation hook

const Profile = () => {
  
  const [currentPassword, setCurrentPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const { userid } = useContext(AuthContext);
  const { t } = useTranslation();
  console.log(userid);

  const handleFormSubmit = (e) => {
    e.preventDefault();

    if (newPassword !== confirmPassword) {
      setErrorMessage('Passwords do not match');
      return;
    }

    axios
    .put('http://localhost:9092/user/updatePassword', {
      userid,
      currentPassword,
      newPassword,
    })
    .then((response) => {
      // Handle success
      console.log('Password updated successfully');
      setCurrentPassword('');
      setNewPassword('');
      setConfirmPassword('');
      setErrorMessage('');
    })
    .catch((error) => {
      // Handle error
      console.error('Failed to update password:', error);
      setErrorMessage('Failed to update password. Please try again.');
    });
  
  };

  return (
    <div>
      <h1>{t('usermenu.profile')}</h1>
      <form onSubmit={handleFormSubmit}>
        <div>
          <label htmlFor="currentPassword">{t('profile.currentpassword')}</label>
          <input
            type="password"
            id="currentPassword"
            value={currentPassword}
            onChange={(e) => setCurrentPassword(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="newPassword">{t('profile.newpassword')}</label>
          <input
            type="password"
            id="newPassword"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="confirmPassword">{t('profile.confirmpassword')}</label>
          <input
            type="password"
            id="confirmPassword"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            required
          />
        </div>
        {errorMessage && <p className="error-message">{errorMessage}</p>}
        <button type="submit">{t('profile.update')}</button>
      </form>
    </div>
  );
};

export default Profile;
