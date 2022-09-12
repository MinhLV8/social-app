import { Emoji, Picker } from "emoji-mart";
import "emoji-mart/css/emoji-mart.css";
import React from 'react';
export default function EmojiPicker(onEmojiClick) {
    const onSelect = (emojiData) => {
        console.log('emoji :>> ', <Emoji emoji={emojiData} />);
    };

    return (
        <div>
            <Picker set="facebook" onSelect={onSelect} theme="dark" showSkinTones={false} showPreview={false}
            />
        </div>
    )
}
