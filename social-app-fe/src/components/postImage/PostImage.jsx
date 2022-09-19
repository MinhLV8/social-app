import React from "react";
import "./postImage.css";
export default function PostImage({ images, onImgClick }) {
  const imagesLength = images.length;

  if (imagesLength >= 5) {
    return (
      <div className="post-imgs-5">
        {images.map((img, index) => (
          <div key={`img-item-${index}`} className="post-img-item">
            <img key={index} src={img.url} alt="" onClick={() => onImgClick(index)} loading="lazy" />
          </div>
        ))}
      </div>
    );
  } else if (imagesLength === 4) {
    return (
      <div className="post-imgs-4">
        {images.map((img, index) => (
          <div key={index} className="post-img-item--4">
            <img key={index} src={img.url} alt="" onClick={() => onImgClick(index)} loading="lazy" />
          </div>
        ))}
      </div>
    );
  } else if (imagesLength === 3) {
    return (
      <div className="post-imgs-3">
        {images.map((img, index) => (
          <div key={index} className="post-img-item--3">
            <img key={index} src={img.url} alt="" onClick={() => onImgClick(index)} loading="lazy" />
          </div>
        ))}
      </div>
    );
  } else if (imagesLength === 2) {
    return (
      <div className="post-imgs-2">
        {images.map((img, index) => (
          <div key={index} className="post-img-item--2">
            <img key={index} src={img.url} alt="" onClick={() => onImgClick(index)} loading="lazy" />
          </div>
        ))}
      </div>
    );
  } else {
    return (
      <>
        {images.map((img, index) => (
          <img
            style={{
              maxHeight: "40rem",
              objectFit: "cover",
              borderRadius: "5px",
              cursor: "pointer"
            }}
            key={index}
            src={img.url}
            alt=""
            onClick={() => onImgClick(index)}
            loading="lazy"
          />
        ))}
      </>
    );
  }
}
