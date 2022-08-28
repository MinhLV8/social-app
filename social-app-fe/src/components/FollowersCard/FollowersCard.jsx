import React from "react";
import { RiUserAddLine } from "react-icons/ri";
import { Followers } from "../../Data/FollowersData";
import "./FollowersCard.css";

const FollowersCard = () => {
  return (
    <div className="FollowersCard">
      <h3>Những người theo dõi bạn</h3>
      {Followers.map((follower, id) => {
        return (
          <div key={id} className="follower">
            <div>
              <div className="follower__avt">
                <img src={follower.img} alt="" className="followerImage" />
                <div className="follower__indicator online"></div>
              </div>
              <div className="name">
                <span>{follower.name}</span>
                <span id={id}>@{follower.username}</span>
              </div>
            </div>
            <button className="button fc-button">
              <RiUserAddLine />
              Theo dõi
            </button>
          </div>
        );
      })}
    </div>
  );
};

export default FollowersCard;
