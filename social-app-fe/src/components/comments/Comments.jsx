import { Emoji } from "emoji-mart";
import React from "react";
import { Users } from "../../Data/PostsData";
import { timeDiff } from "../../utils/Utils";
import "./comments.css";
export default function Comments({ commentDetail }) {
  const username = Users.filter((u) => u.id === commentDetail.userId)[0]
    .username;
  const userAvatar = Users.filter((u) => u.id === commentDetail.userId)[0]
    .userAvatar;
  const emoji = {
    "id": "innocent",
    "name": "Smiling Face with Halo",
    "short_names": [
      "innocent"
    ],
    "colons": ":innocent:",
    "emoticons": [],
    "unified": "1f607",
    "skin": null,
    "native": "😇"
  }
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
                <Emoji emoji={emoji} set="facebook" size={15} />
              </span>
            </div>
            {/* <div className="commentsElement">
              <img src={noti} alt="" />
            </div> */}
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
