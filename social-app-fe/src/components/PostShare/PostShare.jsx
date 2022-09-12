import React, { useRef, useState } from "react";
import { BsFillCollectionPlayFill } from "react-icons/bs";
import { FaTimes } from "react-icons/fa";
import {
  MdAddLocationAlt,
  MdOutlineAddPhotoAlternate,
  MdOutlineSentimentVerySatisfied,
} from "react-icons/md";
import { RiShareForwardLine } from "react-icons/ri";
import { useDispatch, useSelector } from "react-redux";
import { uploadImage } from "../../actions/UploadAction";
import avt from "../../assets/person/avt-10.jpg";
import "./PostShare.css";
const PostShare = () => {
  const dispatch = useDispatch();
  const [image, setImage] = useState(null);
  const imageRef = useRef();

  const captionRef = useRef();
  const user = useSelector((state) => state.authReducer.authData);
  const onImageChange = (event) => {
    if (event.target.files && event.target.files[0]) {
      let img = event.target.files[0];
      setImage(img);
    }
  };
  const handelSubmit = (e) => {
    e.preventDefault();
    const newPost = {
      caption: captionRef.current.value,
      images: [
        {
          fileName: "",
          id: 0,
          image: "",
          pathFile: "",
          sizeFile: 0,
          typeFile: "",
        },
      ],
      privacy: 1,
    };
    if (image) {
      const data = new FormData();
      const filename = Date.now() + "_" + image.name;
      data.append("name", filename);
      newPost.images = filename;
      try {
        dispatch(uploadImage(data));
      } catch (error) {
        console.log("error :>> ", error);
      }
    }
  };
  return (
    <div className="PostShare">
      <img src={avt} alt="" />
      <div>
        <input
          type="text"
          name="caption"
          ref={captionRef}
          placeholder={`${user.data.info.firstName} ơi, bạn đang nghĩ gì thế?`}
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
          <button className="button ps-button" onClick={handelSubmit}>
            Chia sẻ
            <RiShareForwardLine size={17} />
          </button>
          <div style={{ display: "none" }}>
            <input
              type="file"
              name="myImage"
              ref={imageRef}
              onChange={onImageChange}
            />
          </div>
        </div>
        {image && (
          <div className="previewImage">
            <FaTimes onClick={() => setImage(null)} />
            <img src={URL.createObjectURL(image)} alt="" />
          </div>
        )}
      </div>
    </div>
  );
};

export default PostShare;
