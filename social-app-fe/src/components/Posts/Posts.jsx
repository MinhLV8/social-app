import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getTimelinePosts } from "../../actions/PostAction";
import Loading from "../Loading/Loading";
import Post from "../Post/Post";
import "./Posts.css";
const Posts = () => {

  const dispatch = useDispatch()
  // const user = useSelector((state) => state.authReducer.authData);
  const { posts, loading } = useSelector((state) => state.postReducer);
  let postComments = [];

  useEffect(() => {
    dispatch(getTimelinePosts())
  }, []);
  if (!posts || posts.length === 0) return 'Chưa có bài viết nào.';
  return (
    <div className="Posts">
      {loading ? <Loading /> :
        posts.map((post, id) => {
          //postComments = PostComments.filter((u) => u.postId === post.id);
          return (
            <Post key={post.id} post={post} postComments={postComments} id={post.id} />
          );
        })}

    </div>
  );
};

export default Posts;
