import * as PostApi from "../api/PostApi";
export const getTimelinePosts = () => async (dispatch) => {
    dispatch({ type: "RETREIVING_START" })
    try {
        const { data } = await PostApi.getTimelinePosts()
        dispatch({ type: "RETREIVING_SUCCESS", data: data.data })
    } catch (error) {
        console.log(error)
        dispatch({ type: "RETREIVING_FAIL" })
    }
}
