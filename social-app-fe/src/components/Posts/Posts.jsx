import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getTimelinePosts } from "../../actions/PostAction";
import { PostComments } from "../../Data/PostsData";
import Loading from "../Loading/Loading";
import Post from "../Post/Post";
import "./Posts.css";
const Posts = () => {

  const dispatch = useDispatch()
  const user = useSelector((state) => state.authReducer.authData);
  const { posts, loading } = useSelector((state) => state.postReducer);
  let postComments = [];

  useEffect(() => {
    dispatch(getTimelinePosts())
  }, []);
  if (!posts) return 'No Posts';
  return (
    <div className="Posts">
      {loading ? <Loading /> :
        posts.map((post, id) => {
          postComments = PostComments.filter((u) => u.postId === post.id);
          return (
            <Post key={id} post={post} postComments={postComments} id={id} />
          );
        })}

    </div>
  );
};

export default Posts;
