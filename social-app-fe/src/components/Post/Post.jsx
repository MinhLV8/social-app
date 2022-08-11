import React, { useRef, useState } from "react";
import { IconContext } from "react-icons";
import { BiWorld } from "react-icons/bi";
import { BsDot } from "react-icons/bs";
import { FaTimes, FaUserFriends } from "react-icons/fa";
import {
  MdChevronRight,
  MdMoreHoriz,
  MdOutlineImage,
  MdSentimentVerySatisfied
} from "react-icons/md";
import doneTick from "../../assets/icons/1618816460_tich_xanh_facebook.png";
import comment from "../../assets/icons/comment.png";
import like from "../../assets/icons/like.png";
import notlike from "../../assets/icons/notlike.png";
import share from "../../assets/icons/share.png";

import "./Post.css";
const Post = ({ data }) => {
  const [isActive, setActive] = useState({
    postOptions: false,
    postShares: false,
  });
  const handlePostOptionsClick = () => {
    let updateValue = { postOptions: !isActive.postOptions };
    setActive(() => ({
      ...isActive,
      ...updateValue,
    }));
  };
  const commentsButton = useRef();
  const [image, setImage] = useState(null);
  const imageRef = useRef();
  const onImageChange = (event) => {
    if (event.target.files && event.target.files[0]) {
      let img = event.target.files[0];
      setImage({
        image: URL.createObjectURL(img),
      });
    }
  };
  const handleBtnCommentClick = () => {
    commentsButton.current.focus();
  };

  return (
    <div className="Post">
      <div className="Post-user">
        <div>
          <img src={data.img} alt="" />
          <div className="Post-user-detail">
            <span>
              <b>{data.name}</b> <img src={doneTick} alt="" />{" "}
            </span>
            <span>
              3 phút trước
              <BsDot />
              {(() => {
                switch (data.private) {
                  case 1:
                    return <FaUserFriends size={15} />;
                  case 2:
                    return <BiWorld size={15} />;
                  default:
                    throw new Error("Invalid peivate post");
                }
              })()}
            </span>
          </div>
        </div>
        <MdMoreHoriz
          size={30}
          style={{ position: "relative", cursor: "pointer" }}
          onClick={handlePostOptionsClick}
        />
        <div
          className={`postShare ${isActive.postOption ? "postTopRightSettingsHeight" : ""
            }`}
        >
          <div className="settingsTooltip">
            <div className="settings-post-options">
              <ul className="settings-post-items">
                <li className="settings-post-item">
                  <MdChevronRight />
                  <div>
                    <a href="/"> Lưu bài viết</a>
                    <span>Thêm vào danh sách mục đã lưu.</span>
                  </div>
                </li>
                <hr />
                <li className="settings-post-item">
                  <MdChevronRight />
                  <a href="/">Bật thông báo về bài viết này</a>
                </li>
                <li className="settings-post-item">
                  <MdChevronRight />
                  <a href="/"> Nhúng</a>
                </li>
                <hr />
                <li className="settings-post-item">
                  <MdChevronRight />
                  <div>
                    <a href="/"> Ẩn bài viết </a>
                    <span>Ẩn bớt các bài viết tương tự.</span>
                  </div>
                </li>
                <li className="settings-post-item">
                  <MdChevronRight />
                  <div>
                    <a href="/">
                      Tạm ẩn {data.name.split(" ")[0]} trong 30 ngày
                    </a>
                    <span>Tạm dừng xem bài viết.</span>
                  </div>
                </li>
                <li className="settings-post-item">
                  <MdChevronRight />
                  <div>
                    <a href="/">Bỏ theo dõi {data.name.split(" ")[0]}</a>
                    <span>Dừng xem bài viết nhưng vẫn bạn bè.</span>
                  </div>
                </li>
                <li className="settings-post-item">
                  <MdChevronRight />
                  <div>
                    <a href="/">Báo cáo bài viết</a>
                    <span>Tôi lo ngại về bài viết này.</span>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div className="detail">
        <span>
          <b>{data.name}</b>
        </span>
        <span> {data.desc}</span>
      </div>
      <img src={data.img} alt="" />
      <div className="postReact">
        <img src={data.img} alt="" className="postReact-user" />
        <div className="postReact-comment">
          <input
            ref={commentsButton}
            type="text"
            placeholder="Viết bình luận..."
          />
          <IconContext.Provider value={{ style: { cursor: "pointer" } }}>
            <MdSentimentVerySatisfied size={30} />
            <MdOutlineImage
              size={30}
              onClick={() => imageRef.current.click()}
            />
            <div style={{ display: "none" }}>
              <input
                type="file"
                name="commentImage"
                ref={imageRef}
                onChange={onImageChange}
              />
            </div>
          </IconContext.Provider>
        </div>
        <img src={data.liked ? like : notlike} alt="" /> 20
        <img src={comment} alt="" onClick={handleBtnCommentClick} /> 30
        <img src={share} alt="" /> 56
      </div>
      {image && (
        <div className="previewImageComment">
          <FaTimes onClick={() => setImage(null)} />
          <img src={image.image} alt="" />
        </div>
      )}
      {/* <span style={{ color: "var(--gray)", fontSize: '12px' }}>{data.likes} thích</span> */}
    </div>
  );
};

export default Post;
