import * as AuthApi from "../api/AuthApi"

export const signIn = (formData) => async (dispatch) => {
    try {
        dispatch({ type: "AUTH_START" })
        const data = await AuthApi.signIn(formData)
        dispatch({ type: "AUTH_SUCCESS", data: data })
    } catch (error) {
        console.log(error)
        dispatch({ type: "AUTH_FAIL" })
    }
}

export const signUp = (formData) => async (dispatch) => {
    try {
        dispatch({ type: "AUTH_START" })
        const data = await AuthApi.signIn(formData)
        dispatch({ type: "AUTH_SUCCESS", data: data })
    } catch (error) {
        console.log(error)
        dispatch({ type: "AUTH_FAIL" })
    }
}