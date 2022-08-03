import {UilPen} from "@iconscout/react-unicons";
import React, {useState} from "react";
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
            <div className="bio">
                <span>The world is dull, but it has you 🍃</span>
            </div>
            <div className="info">
                <img src="./assets/icons/icons8-briefcase-24.png" alt=""/>
                <span>
          <b>Làm việc tại</b>
        </span>
                <span> Unitech@</span>
            </div>
            <div className="info">
                <img src="./assets/icons/icons8-location-24.png" alt=""/>
                <span>
          <b>Sống tại </b>
        </span>
                <span> Đà Nẵng</span>
            </div>
            <div className="info">
                <img src="./assets/icons/icons8-home-24.png" alt=""/>
                <span>
          <b>Đến từ </b>
        </span>
                <span> Quảng Ngãi</span>
            </div>
            <div className="info">
                <img src="./assets/icons/icons8-love-24.png" alt=""/>
                <span>
          <b> Muốn một mình nhưng sợ cô đơn</b>
        </span>
            </div>
            <button className="button logout-button">Đăng xuất</button>
        </div>
    );
};

export default InfoCard;
