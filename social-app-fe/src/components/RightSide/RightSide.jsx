import React, { useState } from "react";
import { BsBell, BsChatDots } from "react-icons/bs";
import { IoSettingsOutline } from 'react-icons/io5';
import { SiHomebridge } from "react-icons/si";
// import noti from "../../assets/icons/noti.png";
import ShareModal from "../ShareModal/ShareModal";
import TrendCard from "../TrendCard/TrendCard";
import "./RightSide.css";

const RightSide = () => {
    const [modalOpened, setModalOpened] = useState(false);
    return (
        <div className="RightSide">
            <div className="navIcons">
                {/* <img src={home} alt="" /> */}
                <SiHomebridge size={20} color="#7200a1" />
                {/* <UilSetting /> */}
                <IoSettingsOutline size={20} />
                {/* <img src={noti} alt="" /> */}
                <BsBell size={20} />
                {/* <img src={comment} alt="" /> */}
                <BsChatDots size={20} />
            </div>
            <TrendCard />
            <button className="button r-button" onClick={() => setModalOpened(true)}>
                Chia sẻ
            </button>
            <ShareModal modalOpened={modalOpened} setModalOpened={setModalOpened} />
        </div>
    );
};

export default RightSide;
