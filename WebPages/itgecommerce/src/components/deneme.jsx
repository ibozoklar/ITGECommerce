import React, { useState } from "react";
import LogoMain from "./logoMain";
import userLogin from "../assets/styles/user.png";
import userInfo from "../assets/styles/headerIcons/userinfo.png";
import changeRole from "../assets/styles/headerIcons/changerole.png";
import logout from "../assets/styles/headerIcons/logout.png";
import BasketComponent from "./BasketComponent"; // Replace with the actual component you want to render

export default function Header() {
  const [isUserMenuOpen, setIsUserMenuOpen] = useState(false);
  const [isBasketOpen, setIsBasketOpen] = useState(false);

  const [userData, setUserData] = useState({});

  const handleUserMenuClick = () => {
    setIsUserMenuOpen(false);
  };

  const handleUserInfoClick = () => {
    // Update userData with the desired data
    setUserData({});
  };

  const handleRoleChangeClick = () => {
    // Add role change functionality
  };

  const handleLogoutClick = () => {
    // Perform logout operations
  };

  const handleBasketClick = () => {
    setIsBasketOpen(true);
  };

  return (
    <div className="header">
      <header>
        <div>
          {isUserMenuOpen && (
            <div className="usermenu">
              <button className="menu-btn" onClick={handleUserInfoClick}>
                <img src={userInfo} alt="User Icon" className="user-icon" />
                Profil Bilgileri
              </button>
              <button className="menu-btn" onClick={handleRoleChangeClick}>
                <img
                  src={changeRole}
                  alt="change Role"
                  className="exchange-icon"
                />
                Rol Değiştir
              </button>
              <button className="menu-btn" onClick={handleLogoutClick}>
                <img src={logout} alt="logout Icon" className="logout-icon" />
                Çıkış
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
        <div className="basket" onClick={handleBasketClick}>
          <i className="fa-solid fa-cart-shopping fa-2xl"></i>
        </div>
      </header>

      {isBasketOpen && <BasketComponent />}
    </div>
  );
}
