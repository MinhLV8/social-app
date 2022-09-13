export default function postReducer(
  state = {
    posts: [],
    loading: false,
    error: false,
    uploading: false,
  },
  action
) {
  switch (action.type) {
    case "UPLOAD_START":
      return { ...state, uploading: true, loading: true, error: false };
    case "UPLOAD_SUCCESS":
      return {
        ...state,
        posts: [action.data, ...state.posts],
        uploading: false,
        loading: false,
        error: false,
      };
    case "UPLOAD_FAIL":
      return { ...state, uploading: false, loading: false, error: true };
    default:
      return state;
  }
}
