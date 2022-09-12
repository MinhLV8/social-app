import * as UploadAPI from "../api/UploadApi";

export const uploadImage = (data) => async (dispatch) => {
  try {
    await UploadAPI.uploadImage(data);
  } catch (error) {
    console.log(error);
  }
};
