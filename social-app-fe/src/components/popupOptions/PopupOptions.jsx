import React from "react";
import {
  BsBell,
  BsBookmarkPlus,
  BsClock,
  BsCodeSlash,
  BsJournalX,
  BsPersonX,
  BsShieldExclamation,
} from "react-icons/bs";
import "./PopupOptions.css";
export default function PopupOptions({ username }) {
  const alphaBg = "#dadada";
  return (
    <div className="postOptions1">
      <ul className="settings-post-items">
        <li className="settings-post-item">
          <BsBookmarkPlus size={20} color={alphaBg} />
          <div className="settings-post-text">
            <a href="/"> Lưu bài viết</a>
            <span>Thêm vào danh sách mục đã lưu.</span>
          </div>
        </li>
        <hr />
        <li className="settings-post-item">
          <BsBell size={20} color={alphaBg} />
          <div className="settings-post-text">
            <a href="/">Bật thông báo về bài viết này</a>
          </div>
        </li>
        <li className="settings-post-item">
          <BsCodeSlash size={20} color={alphaBg} />
          <div className="settings-post-text">
            <a href="/"> Nhúng</a>
          </div>
        </li>
        <hr />
        <li className="settings-post-item">
          <BsJournalX size={20} color={alphaBg} />
          <div className="settings-post-text">
            <a href="/"> Ẩn bài viết </a>
            <span>Ẩn bớt các bài viết tương tự.</span>
          </div>
        </li>
        <li className="settings-post-item">
          <BsClock size={20} color={alphaBg} />
          <div className="settings-post-text">
            <a href="/">Tạm ẩn {username.split(" ")[0]} trong 30 ngày</a>
            <span>Tạm dừng xem bài viết.</span>
          </div>
        </li>
        <li className="settings-post-item">
          <BsPersonX size={20} color={alphaBg} />
          <div className="settings-post-text">
            <a href="/">Bỏ theo dõi {username.split(" ")[0]}</a>
            <span>Dừng xem bài viết nhưng vẫn bạn bè.</span>
          </div>
        </li>
        <li className="settings-post-item">
          <BsShieldExclamation size={20} color={alphaBg} />
          <div className="settings-post-text">
            <a href="/">Báo cáo bài viết</a>
            <span>Tôi lo ngại về bài viết này.</span>
          </div>
        </li>
      </ul>
    </div>
  );
}
