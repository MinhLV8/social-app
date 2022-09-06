import React from "react";
import { Link } from "react-router-dom";
import avtimg from "../../assets/person/avt-10.jpg";
import coveimg from "../../assets/person/DSC05373.jpg";
import "./ProfileCard.css";

const ProfileCard = () => {
  const ProfilePage = false;
  return (
    <div className="ProfileCard">
      <div className="ProfileImages">
        <img src={coveimg} alt="coveimg" />
        {/* <img src={avtimg} alt="" className={`${!ProfilePage && "avt-img"}`} /> */}
        <img src={avtimg} alt="avtimg" className={`${!ProfilePage && "avt-img"}`} />
      </div>

      <div className="ProfileName">
        <span>MinhLV</span>
        <span>The world is dull, but it has you 🍃</span>
      </div>

      <div className="followStatus">
        {/* <hr /> */}
        <div>
          <div className="follow">
            <span>1</span>
            <span>Followings</span>
          </div>
          {/* <div className="vl"></div> */}
          <div className="follow">
            <span>6,890</span>
            <span>Followers</span>
          </div>

          {ProfilePage && (
            <>
              {/* <div className="vl"></div> */}
              <div className="follow">
                <span>3</span>
                <span>Bài viết</span>
              </div>
            </>
          )}
        </div>
        {/* <hr /> */}
      </div>
      {ProfilePage ? "" : <Link to="/profile"><span>Trang cá nhân</span></Link>}
    </div>
  );
};

export default ProfileCard;
