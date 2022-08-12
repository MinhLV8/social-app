import Picker from '@emoji-mart/react';
import React, { useRef, useState } from "react";
import { IconContext } from "react-icons";
import { BiWorld } from "react-icons/bi";
import { BsBell, BsBookmarkPlus, BsClock, BsCodeSlash, BsDot, BsJournalX, BsPersonX, BsShieldExclamation } from "react-icons/bs";
import { FaTimes, FaUserFriends } from "react-icons/fa";
import {
  MdMoreHoriz, MdOutlineImage, MdSentimentVerySatisfied
} from "react-icons/md";
import doneTick from "../../assets/icons/1618816460_tich_xanh_facebook.png";
import comment from "../../assets/icons/comment.png";
import like from "../../assets/icons/like.png";
import notlike from "../../assets/icons/notlike.png";
import share from "../../assets/icons/share.png";
import Comments from "../comments/Comments";
import "./Post.css";
const Post = ({ data, postComments }) => {
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
  const [isLiked, setIsLiked] = useState(false);
  const [likeAmount, setLikeAmount] = useState(20);
  const [commentAmount, setCommentAmount] = useState(10);
  const commentsButton = useRef();
  const [image, setImage] = useState(null);
  const imageRef = useRef();
  const [inputValue, setInputValue] = useState("");
  const [commentPost, setComments] = useState([...postComments]);
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
  const porstOptionsHide = {
    visibility: "hidden",
    opacity: 0,
  };
  const porstOptions = {
    visibility: "visible",
    opacity: 1,
  };
  const [chosenEmoji, setChosenEmoji] = useState({
    emojiOn: false,
    icons: "",
  });
  const handleIconsClick = () => {
    setChosenEmoji(() => ({
      ...chosenEmoji, emojiOn: !chosenEmoji.emojiOn,
    }));
  };
  const onEmojiClick = (event) => {
    // setChosenEmoji(() => ({
    //   ...chosenEmoji,
    //   icons: chosenEmoji.icons.concat(event.native),
    // }));
    setInputValue(
      inputValue.concat(event.native)
    );
  };
  const handleIconLike = () => {
    setLikeAmount(isLiked ? likeAmount - 1 : likeAmount + 1);
    setIsLiked(!isLiked);
  };
  const handleCommentKeyDown = (event) => {
    if (event.key === "Enter") {
      // console.log('commentPost :>> ', commentPost);
      setInputValue(event.target.value);
      let commentPostaaa = {
        id: 5,
        postId: data.id,
        userId: 1,
        times: new Date().getTime(),
        comment: inputValue,
      };
      setComments([...commentPost, commentPostaaa]);
      setInputValue("");
      setCommentAmount(commentAmount + 1);
      console.log('inputValue', inputValue)
      console.log('comment :>> ', comment);
    }
  };
  const handelFocusCommentInput = () => {
    if (chosenEmoji.emojiOn === true) {
      setChosenEmoji(() => ({
        ...chosenEmoji, emojiOn: !chosenEmoji.emojiOn,
      }))
    }
  }
  const emojis = ["🤣", "😅", "🤣", "🥰", "🤩", "🥰", "🤩", "😇"];
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
                switch (data.privacy) {
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
          className={`postShare ${isActive.postOptions ? "postTopRightSettingsHeight" : ""
            }`}
          style={isActive.postOptions ? porstOptions : porstOptionsHide}
        >
          {/* <div className="settingsTooltip"> */}
          {/* <div className="settings-post-options"> */}
          <ul className="settings-post-items">
            <li className="settings-post-item">
              <BsBookmarkPlus size={20} />
              <div>
                <a href="/"> Lưu bài viết</a>
                <span>Thêm vào danh sách mục đã lưu.</span>
              </div>
            </li>
            <hr />
            <li className="settings-post-item">
              <BsBell size={20} />
              <a href="/">Bật thông báo về bài viết này</a>
            </li>
            <li className="settings-post-item">
              <BsCodeSlash size={20} />
              <a href="/"> Nhúng</a>
            </li>
            <hr />
            <li className="settings-post-item">
              <BsJournalX size={20} />
              <div>
                <a href="/"> Ẩn bài viết </a>
                <span>Ẩn bớt các bài viết tương tự.</span>
              </div>
            </li>
            <li className="settings-post-item">
              <BsClock size={20} />
              <div>
                <a href="/">Tạm ẩn {data.name.split(" ")[0]} trong 30 ngày</a>
                <span>Tạm dừng xem bài viết.</span>
              </div>
            </li>
            <li className="settings-post-item">
              <BsPersonX size={20} />
              <div>
                <a href="/">Bỏ theo dõi {data.name.split(" ")[0]}</a>
                <span>Dừng xem bài viết nhưng vẫn bạn bè.</span>
              </div>
            </li>
            <li className="settings-post-item">
              <BsShieldExclamation size={20} />
              <div>
                <a href="/">Báo cáo bài viết</a>
                <span>Tôi lo ngại về bài viết này.</span>
              </div>
            </li>
          </ul>
          {/* </div> */}
          {/* </div> */}
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
            value={inputValue}
            onChange={(event) => setInputValue(event.target.value)}
            onFocus={handelFocusCommentInput}
            onKeyDown={handleCommentKeyDown}
          />

          <IconContext.Provider value={{ style: { cursor: "pointer" } }}>
            <MdSentimentVerySatisfied size={30} onClick={handleIconsClick} />
            <MdOutlineImage
              size={30}
              onClick={() => imageRef.current.click()}
            />
            {chosenEmoji.emojiOn && (
              <div className='Emoji'>
                <Picker set='facebook' skinTonePosition='none'
                  previewPosition='none'
                  onEmojiSelect={onEmojiClick}
                //onClickOutside={handelFocusCommentInput}
                />
              </div>
            )}
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
        <img src={isLiked ? like : notlike} onClick={handleIconLike} alt="" /> {likeAmount}
        <img src={comment} alt="" onClick={handleBtnCommentClick}
        /> {commentAmount}
        <img src={share} alt="" /> 56
      </div>
      {image && (
        <div className="previewImageComment">
          <FaTimes onClick={() => setImage(null)} />
          <img src={image.image} alt="" />
        </div>
      )}
      <div>
        {commentPost.map((p) =>
          commentPost.length === 0 ? (
            <strong>Chưa có bình luận nào.</strong>
          ) : (
            <Comments key={p.id} commentDetail={p} />
          )
        )
        }
        {commentPost.length > 0 && (
          <div className="moreComments" style={{ display: "flex", justifyContent: "space-between" }}>
            <a href="/">
              <span>Xem thêm bình luận</span>
            </a>
            <span>1/{commentPost.length}</span>
          </div>
        )}
      </div>
      {/* {
        emojis.map((emoji) => {
          return <em-emoji native={emoji} set='facebook'></em-emoji>
        })
      } */}
      {/* <span style={{ color: "var(--gray)", fontSize: '12px' }}>{data.likes} thích</span> */}
    </div>
  );
};

export default Post;
