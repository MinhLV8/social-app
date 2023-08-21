
import React, { useEffect, useRef, useState } from "react";
import { IconContext } from "react-icons";
import { BiWorld } from "react-icons/bi";
import {
  BsChatDots,
  BsDot,
  BsHeart,
  BsHeartFill,
  BsShare
} from "react-icons/bs";
import { FaTimes, FaUserFriends } from "react-icons/fa";
import {
  MdMoreHoriz,
  MdOutlineImage,
  MdSentimentVerySatisfied
} from "react-icons/md";
import { useSelector } from "react-redux";
import doneTick from "../../assets/icons/1618816460_tich_xanh_facebook.png";
import { nFormatter, timeDiff } from "../../utils/Utils";
import ImageSlide from "../ImageSlide/ImageSlide";
import Comments from "../comments/Comments";
import EmojiPicker from "../emoji/EmojiPicker";
import PopupOptions from "../popupOptions/PopupOptions";
import PopupShare from "../popupShares/PopupShare";
import PostImage from "../postImage/PostImage";
import "./Post.css";

const Post = ({ post, postComments }) => {

  const user = useSelector((state) => state.authReducer.authData);
  //const username = Users.filter((u) => u.id === post.userId)[0].username;
  //const userAvatar = Users.filter((u) => u.id === post.userId)[0].userAvatar;
  const [isLiked, setIsLiked] = useState(false);
  const [likeAmount, setLikeAmount] = useState(post?.likes);
  const [commentAmount, setCommentAmount] = useState(post?.comments);
  const commentsButton = useRef();
  const [image, setImage] = useState();
  const imageRef = useRef();
  const [inputValue, setInputValue] = useState("");
  const [commentPost, setComments] = useState([...postComments]);
  const [imageSlide, setImageSlide] = useState(false);
  const [selectedImage, setSelectedImage] = useState(0);

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
    setInputValue(inputValue.concat(event.native));
    return event;
  };
  const handleIconLike = () => {
    setLikeAmount(isLiked ? likeAmount - 1 : likeAmount + 1);
    setIsLiked(!isLiked);
  };
  const handleCommentKeyDown = (event) => {
    inputValue.trim();
    if (event.key === "Enter" && inputValue.length !== 0) {
      setInputValue(event.target.value);
      let commentPosts = {
        id: Math.floor(Math.random() * (999999 - 10 + 1)) + 10,
        postId: post.id,
        userId: Math.floor(Math.random() * (5 - 1 + 1)) + 1,
        times: new Date().getTime(),
        comment: inputValue,
      };
      setComments([...commentPost, commentPosts]);
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

  const handelPostImageClick = (index) => {
    setImageSlide(true);
    setSelectedImage(index);
    return index;
  };

  const handelClosePopup = () => {
    setImageSlide(false);
  };

  useEffect(() => {
    return () => {
      image && URL.revokeObjectURL(image.image)
    };
  }, [image]);
  return (
    <div className="Post">
      <div className="Post-user">
        <div>
          <img src={`data:${user.info.userAvatarContentType};base64, ${user.info.userAvatar}`} alt="" />
          <div className="Post-user-detail">
            <span>
              <b>{user.info.fullname}</b> <img src={doneTick} alt="" />{" "}
            </span>
            <span>
              {timeDiff(new Date().getTime(), post?.times)}
              <BsDot />
              {(() => {
                switch (post?.privacy) {
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
          color={"#dadada"}
          onClick={handlePostOptionsClick}
        />
        {isActive.postOptions && <PopupOptions username={post.name} />}
      </div>
      <div className="detail">
        <span> {post.caption}</span>
      </div>
      <PostImage
        key={post.id}
        images={post.images}
        onImgClick={handelPostImageClick}
      />
      {imageSlide && (
        <ImageSlide
          images={post.images}
          selectedImage={selectedImage}
          onClosePopup={handelClosePopup}
        />
      )}
      <div className="postReact">
        <img src={`data:${user.info.userAvatarContentType};base64, ${user.info.userAvatar}`} alt="" className="postReact-user" />
        {/* <InputComment /> */}
        <div className="postReact-comment">
          <input
            ref={commentsButton}
            type="text"
            placeholder="Viết bình luận..."
            value={inputValue}
            onChange={(e) => setInputValue(e.target.value)}
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
                <EmojiPicker onEmojiClick={onEmojiClick} />
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
        {/* <img src={isLiked ? like : notlike} onClick={handleIconLike} alt="" />{" "} */}
        {isLiked ? (
          <BsHeartFill onClick={handleIconLike} size={25} color={"#7200a1"} />
        ) : (
          <BsHeart size={25} onClick={handleIconLike} />
        )}
        {nFormatter(likeAmount)}
        {/* <img src={comment} alt="" onClick={handleBtnCommentClick} />{" "} */}
        <BsChatDots size={25} onClick={handleBtnCommentClick} />
        {nFormatter(commentAmount)}
        {/* <img
          src={share}
          alt=""
          onClick={handleBtnShareClick}
          style={{ position: "relative" }}
        />{" "} */}
        <BsShare size={23} onClick={handleBtnShareClick} />
        {nFormatter(post.shares)}
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
            <p>Chưa có bình luận nào.</p>
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
