import {UilSetting} from "@iconscout/react-unicons";
import React, {useState} from "react";
import ShareModal from "../ShareModal/ShareModal";
import TrendCard from "../TrendCard/TrendCard";
import "./RightSide.css";

const RightSide = () => {
    const [modalOpened, setModalOpened] = useState(false);
    return (
        <div className="RightSide">
            <div className="navIcons">
                <img src="../../assets/icons/home.png" alt=""/>
                <UilSetting/>
                <img src="../../assets/icons/noti.png" alt=""/>
                <img src="../../assets/icons/comment.png" alt=""/>
            </div>
            <TrendCard/>
            <button className="button r-button" onClick={() => setModalOpened(true)}>
                Chia sẻ
            </button>
            <ShareModal modalOpened={modalOpened} setModalOpened={setModalOpened}/>
        </div>
    );
};

export default RightSide;
