import React, { useRef, useState } from "react";
import { BsFillCollectionPlayFill } from "react-icons/bs";
import { FaTimes } from "react-icons/fa";
import {
  MdAddLocationAlt,
  MdOutlineAddPhotoAlternate,
  MdOutlineSentimentVerySatisfied
} from "react-icons/md";
import avt from "../../assets/person/avt-10.jpg";
import "./PostShare.css";
const PostShare = () => {
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
  return (
    <div className="PostShare">
      <img src={avt} alt="" />
      <div>
        <input type="text" placeholder="Minh ơi, bạn đang nghĩ gì thế?" />
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
          <button className="button ps-button">Chia sẻ</button>
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
            <img src={image.image} alt="" />
          </div>
        )}
      </div>
    </div>
  );
};

export default PostShare;
