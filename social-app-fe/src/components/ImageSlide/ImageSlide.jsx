import React, { useState } from "react";
import { BsChevronLeft, BsChevronRight } from "react-icons/bs";
import { RiCloseFill } from "react-icons/ri";
import "./ImageSlide.css";
export default function ImageSlide({ images, selectedImage, onClosePopup }) {
  const [state, setState] = useState({
    currentIndex: selectedImage,
    isTransitioning: false,
    goingLeft: false,
  });

  const showPrevSet = () => {
    const currentIndex =
      (state.currentIndex - 1 + images.length) % images.length;
    setState({ ...state, currentIndex });
  };

  const showNextSet = () => {
    const currentIndex = (state.currentIndex + 1) % images.length;
    setState({ ...state, currentIndex });
  };
  console.log('images', images)
  return (
    <div className="popup">
      <div className="popup_inner">
        <div className="popup_img">
          {images.map((img, index) => {
            let className = "carousel__image";
            if (index === state.currentIndex) className += " active";
            return (
              <img
                src={img.url}
                className={className}
                key={`img-${index}`}
                alt=""
              />
            );
          })}
          {images.length !== 1 && (
            <div className="carousel__controls">
              <button className="carousel__button left" onClick={showPrevSet}>
                <BsChevronLeft size={20} />
              </button>
              <button className="carousel__button right" onClick={showNextSet}>
                <BsChevronRight size={20} />
              </button>
            </div>
          )}
        </div>
        <div className="popup_comments">Bình luận</div>
      </div>
      <button className="btn-close" onClick={onClosePopup}>
        <RiCloseFill size={40} />
      </button>
    </div>
  );
}
