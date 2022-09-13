import * as UploadAPI from "../api/UploadApi";

export const uploadImage = (data) => async (dispatch) => {
  try {
    const newImgPost = await UploadAPI.uploadImage(data);
    return newImgPost;
  } catch (error) {
    console.log(error);
  }
};
export const uploadPost = (data) => async (dispatch) => {
  dispatch({ type: "UPLOAD_START" })
  try {
    const newPost = await UploadAPI.uploadPost(data)
    dispatch({ type: "UPLOAD_SUCCESS" })
  } catch (error) {
    console.log(error)
    dispatch({ type: "UPLOAD_FAIL" })
  }
}