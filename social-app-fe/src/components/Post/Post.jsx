import React from 'react'
import './Post.css'


const Post = ({data}) => {
    return (
        <div className="Post">
            <img src={data.img} alt=""/>


            <div className="postReact">
                <img src={data.liked ? "../../assets/icons/like.png" : "../../assets/icons/notlike.png"} alt=""/>
                <img src="../../assets/icons/comment.png" alt=""/>
                <img src="../../assets/icons/share.png" alt=""/>
            </div>


            <span style={{color: "var(--gray)", fontSize: '12px'}}>{data.likes} thích</span>

            <div className="detail">
                <span><b>{data.name}</b></span>
                <span> {data.desc}</span>
            </div>
        </div>
    )
}

export default Post