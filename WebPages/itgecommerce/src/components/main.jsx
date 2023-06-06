import React, { useState } from "react";
import Content from "./content";


export default function Main() {
    const [selectedSubMenu, setSelectedSubMenu] = useState("");
    const handleSubMenuClick = (submenu) => {
            setSelectedSubMenu(submenu);
    }
    
    return (
      <main>
        <Content selectedSubMenu={selectedSubMenu} />
      </main>
    );
  }