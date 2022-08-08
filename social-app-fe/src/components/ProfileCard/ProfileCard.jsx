import React from "react";
import "./ProfileCard.css";

const ProfileCard = () => {
    const ProfilePage = true;
    return (
        <div className="ProfileCard">
            <div className="ProfileImages">
                <img src="../../assets/person/DSC05373.jpg" alt="" />
                <img src="../../assets/person/avt-10.jpg" alt="" />
            </div>

            <div className="ProfileName">
                <span>MinhLV</span>
                <span>Chuyên gia tư vấn tình cảm lứa đôi</span>
            </div>

            <div className="followStatus">
                <hr />
                <div>
                    <div className="follow">
                        <span>6,890</span>
                        <span>Followings</span>
                    </div>
                    <div className="vl"></div>
                    <div className="follow">
                        <span>1</span>
                        <span>Followers</span>
                    </div>

                    {ProfilePage && (
                        <>
                            <div className="vl"></div>
                            <div className="follow">
                                <span>3</span>
                                <span>Bài viết</span>
                            </div>
                        </>
                    )}
                </div>
                <hr />
            </div>
            {ProfilePage ? "" : <span>Trang cá nhân</span>}
        </div>
    );
};

export default ProfileCard;
