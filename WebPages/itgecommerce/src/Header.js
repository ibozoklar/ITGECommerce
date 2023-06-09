import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import LogoMain from './logoMain';
import userLogin from './assets/styles/user.png';
import userInfo from './assets/styles/headerIcons/userinfo.png';
import changeRole from './assets/styles/headerIcons/changerole.png';
import logout from './assets/styles/headerIcons/logout.png';
import BasketPage from './BasketPage';
import MyProducts from './myProducts';



export default function Header() {
  const [isUserMenuOpen, setIsUserMenuOpen] = useState(false);
  const [isBasketOpen, setIsBasketOpen] = useState(false);
  const [isMyProductsOpen, setIsMyProductsOpen] = useState(false);

  const [userData, setUserData] = useState({});

  const history = useHistory();

  const toggleBasket = () => {
    setIsBasketOpen(!isBasketOpen);
  };

  const toggleMyProducts = () => {
    setIsMyProductsOpen(!isMyProductsOpen);
  };

  const handleUserMenuClick = () => {
    setIsUserMenuOpen(false);
  };

  const handleUserInfoClick = () => {
    // Perform actions when user info is clicked
    // You can navigate to the profile page or show a modal, etc.
  };

  const handleRoleChangeClick = () => {
    // Perform actions when role change is clicked
    // You can show a dropdown menu or navigate to a role selection page, etc.
  };

  const handleLogoutClick = () => {
    // Perform logout operations
    // Clear user session, remove tokens, etc.
    // Then navigate to the login page or any other desired page
    history.push('/login');
  };

  const handleMyProductsClick = () => {
    // Perform actions when My Products is clicked
    // You can navigate to the My Products page or show a modal, etc.
  };

  return (
    <div className="header">
      <header>
        <div>
          {isUserMenuOpen && (
            <div className="usermenu">
              <button className="menu-btn" onClick={handleUserInfoClick}>
                <img src={userInfo} alt="User Icon" className="user-icon" />
                Profile
              </button>
              <button className="menu-btn" onClick={toggleMyProducts}>
                <img
                  src={changeRole}
                  alt="change Role"
                  className="exchange-icon"
                />
                My Products
              </button>
              <button className="menu-btn" onClick={handleLogoutClick}>
                <img src={logout} alt="logout Icon" className="logout-icon" />
                Logout
              </button>
            </div>
          )}
        </div>

        <div className="div-logo">
          <LogoMain />
        </div>
        <div className="div-mainheading">
          <h1>Otomotiv Parça E-Ticaret</h1>
        </div>

        <div className="userlogin">
          <img
            src={userLogin}
            alt="iconlogin"
            onClick={() => setIsUserMenuOpen(!isUserMenuOpen)}
          />
        </div>
        <div className="basket" onClick={toggleBasket}>
          Basket
          <i className="fa-solid fa-cart-shopping fa-2xl"></i>
        </div>
      </header>

      {isBasketOpen && <BasketPage />}
      {isMyProductsOpen && <MyProducts />}
      


    </div>
  );
}
