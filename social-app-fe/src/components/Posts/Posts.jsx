import React from "react";
import { PostComments, PostsData } from "../../Data/PostsData";
import Post from "../Post/Post";
import "./Posts.css";

const Posts = () => {
  let postComments = [];
  return (
    <div className="Posts">
      {PostsData.map((post, id) => {
        postComments = PostComments.filter((u) => u.postId === post.id);
        return (
          <Post key={id} post={post} postComments={postComments} id={id} />
        );
      })}
    </div>
  );
};

export default Posts;
