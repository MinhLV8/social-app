import { Emoji, Picker } from "emoji-mart";
import "emoji-mart/css/emoji-mart.css";
import React from 'react';
export default function EmojiPicker(onEmojiClick) {




    const onSelect = (emojiData) => {
        const emoji = <Emoji emoji={emojiData} />;
        console.log('emoji :>> ', emojiData);
    };

    return (
        <div>
            <Picker set="facebook" onSelect={onSelect} theme="dark" showSkinTones={false} showPreview={false}
            />
        </div>
    )
}
