import axios from "axios";
const API = axios.create({ baseURL: "http://localhost:9090/api/" })
export const signIn = (formData) => API.post('/auth/signin', formData)
export const signUp = (formData) => API.post('/auth/signup', formData) 