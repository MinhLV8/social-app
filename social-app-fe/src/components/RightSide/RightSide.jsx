import React, { useState } from "react";
import { IoMdSettings } from 'react-icons/io';
import comment from "../../assets/icons/comment.png";
import home from "../../assets/icons/home.png";
import noti from "../../assets/icons/noti.png";
import ShareModal from "../ShareModal/ShareModal";
import TrendCard from "../TrendCard/TrendCard";
import "./RightSide.css";

const RightSide = () => {
    const [modalOpened, setModalOpened] = useState(false);
    return (
        <div className="RightSide">
            <div className="navIcons">
                <img src={home} alt="" />
                {/* <UilSetting /> */}
                <IoMdSettings size={24} />
                <img src={noti} alt="" />
                <img src={comment} alt="" />
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
