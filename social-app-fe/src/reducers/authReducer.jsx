export default function authReducer(
  state = { authData: null, loading: false, error: false },
  action
) {
  switch (action.type) {
    case "AUTH_START":
      return { ...state, loading: true, error: false };
    case "AUTH_SUCCESS":
      localStorage.setItem("jwt", JSON.stringify({ ...action?.data.data }));
      return {
        ...state,
        authData: action.data.data,
        loading: false,
        error: false,
      };
    case "AUTH_INFO":
      localStorage.setItem(
        "profile",
        JSON.stringify({ ...action?.data.data })
      );
      return {
        ...state,
        authData: action.data.data,
        loading: false,
        error: false,
      };
    case "AUTH_FAIL":
      return { ...state, loading: false, error: true };
    default:
      return state;
  }
}
