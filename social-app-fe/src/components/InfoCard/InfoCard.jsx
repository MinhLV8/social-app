import React, { useCallback, useState } from "react";
import { FiEdit } from "react-icons/fi";
import { useNavigate } from 'react-router-dom';
import briefcase from "../../assets/icons/icons8-briefcase-24.png";
import home from "../../assets/icons/icons8-home-24.png";
import location from "../../assets/icons/icons8-location-24.png";
import love from "../../assets/icons/icons8-love-24.png";
import ProfileModal from "../ProfileModal.jsx/ProfileModal";
import "./InfoCard.css";

const InfoCard = () => {
  const [modalOpened, setModalOpened] = useState(false);
  const navigate = useNavigate();
  const handleOnClick = useCallback(() => navigate('/auth', { replace: true }), [navigate]);
  return (
    <div className="InfoCard">
      <div className="infoHead">
        <h4>Tiểu sử</h4>
        <div>
          <FiEdit size={18}
            onClick={() => setModalOpened(true)}
          />
          <ProfileModal
            modalOpened={modalOpened}
            setModalOpened={setModalOpened}
          />
        </div>
      </div>
      <div className="bio">
        <span>The world is dull, but it has you 🍃</span>
      </div>
      <div className="info">
        <img src={briefcase} alt="work_at-icon" />
        <span>
          <b>Làm việc tại</b>
        </span>
        <span> Unitech@</span>
      </div>
      <div className="info">
        <img src={location} alt="location-icon" />
        <span>
          <b>Sống tại </b>
        </span>
        <span> Đà Nẵng</span>
      </div>
      <div className="info">
        <img src={home} alt="hometown-icon" />
        <span>
          <b>Đến từ </b>
        </span>
        <span> Quảng Ngãi</span>
      </div>
      <div className="info">
        <img src={love} alt="relationship-icon" />
        <span>
          <b> Muốn một mình nhưng sợ cô đơn.</b>
        </span>
      </div>
      <button className="button logout-button" onClick={handleOnClick}>Đăng xuất</button>
    </div>
  );
};

export default InfoCard;
