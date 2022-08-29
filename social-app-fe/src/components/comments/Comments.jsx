import React from "react";
import noti from "../../assets/person/avt-1.jpg";
import { Users } from "../../Data/PostsData";
import { timeDiff } from "../../utils/Utils";
import "./comments.css";
export default function Comments({ commentDetail }) {
  const username = Users.filter((u) => u.id === commentDetail.userId)[0]
    .username;
  const userAvatar = Users.filter((u) => u.id === commentDetail.userId)[0]
    .userAvatar;
  return (
    <>
      <ul className="comments">
        <li className="comment">
          <img className="commentImg" src={userAvatar} alt="" />
          <div className="commentUsers">
            <div className="commentUser">
              <a href="/">{username}</a>
              <span>
                {commentDetail.comment}{" "}
                <em-emoji native="🤗" unified="1f619" set="facebook"></em-emoji>
              </span>
            </div>
            <div className="commentsElement">
              <img src={noti} alt="" />
            </div>
            <ul className="commentUserOptions">
              <li className="commentUserOption">
                <a href="/">Thích</a>
              </li>
              <li className="commentUserOption">
                <a href="/">Phản hồi</a>
              </li>
              <li className="commentUserTimes">
                {timeDiff(new Date().getTime(), commentDetail.times)}
              </li>
            </ul>
          </div>
        </li>
      </ul>
    </>
  );
}
