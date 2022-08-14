import React from "react";
import { BsBell, BsBookmarkPlus, BsCodeSlash } from "react-icons/bs";
import "./PopupShare.css";
export default function PopupShare() {
  return (
    <div className="postShare">
      <ul className="settings-post-items">
        <li className="settings-post-item">
          <BsBookmarkPlus size={20} />
          <div>
            <a href="/"> Chia sẻ lên trang cá nhân</a>
            {/* <span>Thêm vào danh sách mục đã lưu.</span> */}
          </div>
        </li>
        {/* <hr /> */}
        <li className="settings-post-item">
          <BsBell size={20} />
          <a href="/">Chia sẻ tới bạn bè</a>
        </li>
        <li className="settings-post-item">
          <BsCodeSlash size={20} />
          <a href="/"> Chia sẻ trong tin nhắn</a>
        </li>
        {/* <hr /> */}
        {/* <li className="settings-post-item">
          <BsJournalX size={20} />
          <div>
            <a href="/"> Ẩn bài viết </a>
            <span>Ẩn bớt các bài viết tương tự.</span>
          </div>
        </li>
        <li className="settings-post-item">
          <BsClock size={20} />
          <div>
            <a href="/">Tạm ẩn {username.split(" ")[0]} trong 30 ngày</a>
            <span>Tạm dừng xem bài viết.</span>
          </div>
        </li>
        <li className="settings-post-item">
          <BsPersonX size={20} />
          <div>
            <a href="/">Bỏ theo dõi {username.split(" ")[0]}</a>
            <span>Dừng xem bài viết nhưng vẫn bạn bè.</span>
          </div>
        </li>
        <li className="settings-post-item">
          <BsShieldExclamation size={20} />
          <div>
            <a href="/">Báo cáo bài viết</a>
            <span>Tôi lo ngại về bài viết này.</span>
          </div>
        </li> */}
      </ul>
    </div>
  );
}
