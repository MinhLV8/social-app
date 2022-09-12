import { Picker } from "emoji-mart";
import "emoji-mart/css/emoji-mart.css";
import React, { useState } from "react";

export default function InputComment() {

    const [comment, setComment] = useState({
        text: "",
        showEmojis: false
    });

    const showEmojis = () => {
        setComment(() => ({
            ...comment,
            showEmojis: !comment.showEmojis,
        }));

    };


    const handleChange = (e) => {
        setComment({
            ...comment,
            text: e.target.value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setComment({
            text: "",
            showEmojis: false
        })

    };

    const addEmoji = (e) => {
        let emoji = e.native;
        setComment({
            ...comment,
            text: comment.text + emoji
        });
    };

    return (
        <div className="newMessageForm">
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={comment.text}
                    onChange={handleChange}
                    placeholder="Nhập bình luận của bạn..."
                />
            </form>
            {comment.showEmojis ? (
                <span>
                    <Picker
                        onSelect={addEmoji} theme="dark" showSkinTones={false} showPreview={false}
                    />
                </span>
            ) : (
                <p onClick={showEmojis}>
                    {String.fromCodePoint(0x1f60a)}
                </p>
            )}
        </div>
    );

}