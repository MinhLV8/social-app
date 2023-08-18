import * as AuthApi from "../api/AuthApi"

export const signIn = (formData) => async (dispatch) => {
    dispatch({ type: "AUTH_START" })
    try {
        const data = await AuthApi.signIn(formData)
        dispatch({ type: "AUTH_SUCCESS", data: data.data })

        const info = await AuthApi.getInFoUser()
        dispatch({ type: "AUTH_INFO", data: info.data })
    } catch (error) {
        console.log(error)
        dispatch({ type: "AUTH_FAIL" })
    }
}

export const signUp = (formData) => async (dispatch) => {
    dispatch({ type: "AUTH_START" })
    try {
        const data = await AuthApi.signUp(formData)
        dispatch({ type: "AUTH_SUCCESS", data: data.data })
    } catch (error) {
        console.log(error)
        dispatch({ type: "AUTH_FAIL" })
    }
}