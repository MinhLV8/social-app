import React from 'react';
import "./postImage.css";
export default function PostImage({ images, onImgClick }) {
    const imagesLength = images.length;
    if (imagesLength >= 5) {
        return (<div className="post-imgs-5">
            {images.map((img, index) => (
                <div className="post-img-item">
                    <img key={index} src={img} alt="" onClick={onImgClick} />
                </div>
            ))}
        </div>)
    } else if (imagesLength === 4) {
        return (<div className="post-imgs-4">
            {images.map((img, index) => (
                <div className="post-img-item--4">
                    <img key={index} src={img} alt="" onClick={onImgClick} />
                </div>
            ))}
        </div>)
    } else if (imagesLength === 3) {
        return (<div className="post-imgs-3">
            {images.map((img, index) => (
                <div className="post-img-item--3">
                    <img key={index} src={img} alt="" onClick={onImgClick} />
                </div>
            ))}
        </div>)
    }



}
