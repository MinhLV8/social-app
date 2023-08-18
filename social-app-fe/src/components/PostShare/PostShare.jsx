import React, { useRef, useState } from "react";
import { BsFillCollectionPlayFill } from "react-icons/bs";
import { FaTimes } from "react-icons/fa";
import {
  MdAddLocationAlt,
  MdOutlineAddPhotoAlternate,
  MdOutlineSentimentVerySatisfied
} from "react-icons/md";
import { RiShareForwardLine } from "react-icons/ri";
import { useDispatch, useSelector } from "react-redux";
import Loading from "../Loading/Loading";
import { uploadPost } from "./../../actions/UploadAction";
import "./PostShare.css";
const PostShare = () => {
  const dispatch = useDispatch();
  const [images, setImages] = useState([]);
  const imageRef = useRef();
  const loading = useSelector((state) => state.postReducer.uploading);
  const captionRef = useRef();
  const user = useSelector((state) => state.authReducer.authData);
  const onImageChange = (event) => {
    if (event.target.files) {
      let imgs = event.target.files;
      setImages(imgs);
    }
  };
  const resetPost = () => {
    setImages([]);
    captionRef.current.value = ""
  }
  const handelSubmit = (e) => {
    e.preventDefault();
    const newPost = {
      caption: captionRef.current.value,
      privacy: 1,
    };
    const data = new FormData();
    try {
      if (images) {
        Array.from(images).map((image) => data.append("images", image));
        data.append("post", JSON.stringify(newPost));
        dispatch(uploadPost(data));
        // dispatch(uploadImage(data)).then((response) => {
        //   newPost.images = response.data;
        // });
        resetPost()
      }
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <div className="PostShare">
      <img src={`data:${user.info?.userAvatarContentType};base64, ${user.info?.userAvatar}`} alt="avatar" />
      <div>
        <input
          type="text"
          name="caption"
          ref={captionRef}
          placeholder={`${user.info?.firstname} ơi, bạn đang nghĩ gì thế?`}
          required
        />
        <div className="postOptions">
          <div
            className="option"
            style={{ color: "var(--photo)" }}
            onClick={() => imageRef.current.click()}
          >
            <MdOutlineAddPhotoAlternate size={24} />
            Ảnh
          </div>
          <div className="option" style={{ color: "var(--video)" }}>
            <BsFillCollectionPlayFill size={24} />
            Video
          </div>{" "}
          <div className="option" style={{ color: "var(--location)" }}>
            <MdAddLocationAlt size={24} />
            Điểm đến
          </div>
          <div className="option" style={{ color: "var(--shedule)" }}>
            <MdOutlineSentimentVerySatisfied size={24} />
            Cảm xúc/Hoạt động
          </div>
          {(images.length !== 0) && (
            <button
              className="button ps-button"
              onClick={handelSubmit}
              disabled={loading}
            >
              {loading ? <Loading /> : "Chia sẻ"}
              <RiShareForwardLine size={17} />
            </button>
          )}

          <div style={{ display: "none" }}>
            <input
              type="file"
              name="myImage"
              multiple
              ref={imageRef}
              onChange={onImageChange}
              accept="application/jpg, image/png, image/JPEG"
            />
          </div>
        </div>
        {images.length !== 0 && (
          <div className="previewImage">
            <FaTimes onClick={() => setImages([])} />
            {Array.from(images).map((image, index) => (
              <img key={index} src={URL.createObjectURL(image)} alt="" />
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default PostShare;
