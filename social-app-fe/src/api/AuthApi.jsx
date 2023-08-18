import axios from "axios";
import authHeader from "./authHeader";
const API = axios.create({ baseURL: "http://localhost:8080/api/" })
export const signIn = (formData) => API.post('/auth/signin', formData)
export const getInFoUser = () => API.get('/user/info',authHeader())
export const signUp = (formData) => API.post('/auth/signup', formData) 