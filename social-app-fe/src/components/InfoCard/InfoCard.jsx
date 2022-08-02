import { UilPen } from "@iconscout/react-unicons";
import React, { useState } from "react";
import ProfileModal from "../ProfileModal.jsx/ProfileModal";
import "./InfoCard.css";

const InfoCard = () => {
  const [modalOpened, setModalOpened] = useState(false);
  return (
    <div className="InfoCard">
      <div className="infoHead">
        <h4>Tiểu sử</h4>
        <div>
          <UilPen
            width="2rem"
            height="1.2rem"
            onClick={() => setModalOpened(true)}
          />
          <ProfileModal
            modalOpened={modalOpened}
            setModalOpened={setModalOpened}
          />
        </div>
      </div>
      <div className="info">
        <span>
          <b>Làm việc tại</b>
        </span>
        <span> Unitech@</span>
      </div>

      <div className="info">
        <span>
          <b> </b>
        </span>
        <span> Độc thân</span>
      </div>

      <div className="info">
        <span>
          <b>Sống tại </b>
        </span>
        <span> Đà Nẵng</span>
      </div>
      <div className="info">
        <span>
          <b>Đến từ </b>
        </span>
        <span> Quảng Ngãi</span>
      </div>


      <button className="button logout-button">
        Đăng xuất</button>
    </div>
  );
};

export default InfoCard;
