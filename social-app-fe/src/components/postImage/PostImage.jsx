import React, { createContext, useState } from "react";
import "./postImage.css";
export const ImageSelected = createContext();
export default function PostImage({ images, onImgClick }) {
  const imagesLength = images.length;
  const [selectedImage, setSelectedImage] = useState(0);

  // const handelImgOnClick = (index) => {
  //   onImgClick;
  //   setSelectedImage(index)
  // } 
  //yêu là chết ở trong lòng một chút vì mấy khi yêu mà đã được yêu, cho thì nhiều nhận lại chẳng bao nhiêu 

  if (imagesLength >= 5) {
    return (
      <div className="post-imgs-5">
        {images.map((img, index) => (
          <div key={`img-item-${index}`} className="post-img-item">
            <img key={index} src={img} alt="" onClick={onImgClick} />
          </div>
        ))}
      </div>
    );
  } else if (imagesLength === 4) {
    return (
      <div className="post-imgs-4">
        {images.map((img, index) => (
          <div key={index} className="post-img-item--4">
            <img key={index} src={img} alt="" onClick={onImgClick} />
          </div>
        ))}
      </div>
    );
  } else if (imagesLength === 3) {
    return (
      <div className="post-imgs-3">
        {images.map((img, index) => (
          <div key={index} className="post-img-item--3">
            <img key={index} src={img} alt="" onClick={onImgClick} />
          </div>
        ))}
      </div>
    );
  }
}
