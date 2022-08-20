import Picker from "@emoji-mart/react";
import React, { useRef, useState } from "react";
import { IconContext } from "react-icons";
import { BiWorld } from "react-icons/bi";
import { BsDot } from "react-icons/bs";
import { FaTimes, FaUserFriends } from "react-icons/fa";
import {
  MdMoreHoriz,
  MdOutlineImage,
  MdSentimentVerySatisfied
} from "react-icons/md";
import doneTick from "../../assets/icons/1618816460_tich_xanh_facebook.png";
import comment from "../../assets/icons/comment.png";
import like from "../../assets/icons/like.png";
import notlike from "../../assets/icons/notlike.png";
import share from "../../assets/icons/share.png";
import { Users } from "../../Data/PostsData";
import { timeDiff } from "../../utils/Utils";
import Comments from "../comments/Comments";
import ImageSlide from "../ImageSlide/ImageSlide";
import PopupOptions from "../popupOptions/PopupOptions";
import PopupShare from "../popupShares/PopupShare";
import PostImage from "../postImage/PostImage";
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
  const handleBtnShareClick = () => {
    let updateValue = { postShares: !isActive.postShares };
    setActive(() => ({
      ...isActive,
      ...updateValue,
    }));
  };
  const username = Users.filter((u) => u.id === data.userId)[0].username;
  const userAvatar = Users.filter((u) => u.id === data.userId)[0].userAvatar;
  const [isLiked, setIsLiked] = useState(false);
  const [likeAmount, setLikeAmount] = useState(20);
  const [commentAmount, setCommentAmount] = useState(10);
  const commentsButton = useRef();
  const [image, setImage] = useState(null);
  const imageRef = useRef();
  const [inputValue, setInputValue] = useState("");
  const [commentPost, setComments] = useState([...postComments]);
  const [imageSlide, setImageSlide] = useState(false);

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
  const [chosenEmoji, setChosenEmoji] = useState({
    emojiOn: false,
    icons: "",
  });
  const handleIconsClick = () => {
    setChosenEmoji(() => ({
      ...chosenEmoji,
      emojiOn: !chosenEmoji.emojiOn,
    }));
  };
  const onEmojiClick = (event) => {
    // setChosenEmoji(() => ({
    //   ...chosenEmoji,
    //   icons: chosenEmoji.icons.concat(event.native),
    // }));
    console.log("event", event);
    setInputValue(inputValue.concat(event.native));
  };
  const handleIconLike = () => {
    setLikeAmount(isLiked ? likeAmount - 1 : likeAmount + 1);
    setIsLiked(!isLiked);
  };
  const handleCommentKeyDown = (event) => {
    if (event.key === "Enter") {
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
    }
  };
  const handelFocusCommentInput = () => {
    if (chosenEmoji.emojiOn === true) {
      setChosenEmoji(() => ({
        ...chosenEmoji,
        emojiOn: !chosenEmoji.emojiOn,
      }));
    }
  };

  const handelPostImageClick = () => {
    setImageSlide(true);
  };

  const handelclosePopup = () => {
    setImageSlide(false);
  };
  return (
    <div className="Post">
      <div className="Post-user">
        <div>
          <img src={userAvatar} alt="" />
          <div className="Post-user-detail">
            <span>
              <b>{username}</b> <img src={doneTick} alt="" />{" "}
            </span>
            <span>
              {timeDiff(new Date().getTime(), data.times)}
              <BsDot />
              {(() => {
                switch (data.privacy) {
                  case 1:
                    return <FaUserFriends size={15} />;
                  case 2:
                    return <BiWorld size={15} />;
                  default:
                    throw new Error("Invalid private post");
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
        {isActive.postOptions && <PopupOptions username={data.name} />}
      </div>
      <div className="detail">
        <span>
          <b>{data.name}</b>
        </span>
        <span> {data.desc}</span>
      </div>
      {data.img.length === 1 && (
        <img
          style={{
            maxHeight: "40rem",
            objectFit: "cover",
            borderRadius: "5px",
          }}
          src={data.img}
          alt=""
        />
      )}
      <PostImage
        key={data.id}
        images={data.img}
        onImgClick={handelPostImageClick}
      />
      {imageSlide && (
        <ImageSlide images={data.img} onClosePopup={handelclosePopup} />
      )}
      <div className="postReact">
        <img src={userAvatar} alt="" className="postReact-user" />
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
              <div className="Emoji">
                <Picker
                  set="facebook"
                  skinTonePosition="none"
                  previewPosition="none"
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
        <img src={isLiked ? like : notlike} onClick={handleIconLike} alt="" />{" "}
        {likeAmount}
        <img src={comment} alt="" onClick={handleBtnCommentClick} />{" "}
        {commentAmount}
        <img
          src={share}
          alt=""
          onClick={handleBtnShareClick}
          style={{ position: "relative" }}
        />{" "}
        {data.shares}
        {isActive.postShares && <PopupShare />}
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
        )}
        {commentPost.length > 0 && (
          <div
            className="moreComments"
            style={{ display: "flex", justifyContent: "space-between" }}
          >
            <a href="/">
              <span>Xem thêm bình luận</span>
            </a>
            <span>1/{commentPost.length}</span>
          </div>
        )}
      </div>
    </div>
  );
};

export default Post;
