import React from "react";
import { BiSearch } from 'react-icons/bi';
import logo from "../../assets/icons/care-2387662-1991058.png";
import "./LogoSearch.css";
const LogoSearch = () => {
  return (
    <div className="LogoSearch">
      <img src={logo} alt="" onClick={() => window.location.href = '/'} />
      <div className="Search">
        <input type="text" placeholder="Tìm kiếm ở đây..." />
        <div className="s-icon">
          <BiSearch size={24} />
        </div>
      </div>
    </div>
  );
};

export default LogoSearch;
