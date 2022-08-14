import React from "react";
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
              <a href="https://www.facebook.com/">{username}</a>
              <span>{commentDetail.comment}</span>
              <em-emoji native=":)" set="facebook"></em-emoji>
            </div>
            <ul className="commentUserOptions">
              <li className="commentUserOption">
                <a href="https://www.facebook.com/">Thích</a>
              </li>
              <li className="commentUserOption">
                <a href="https://www.facebook.com/">Phản hồi</a>
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
