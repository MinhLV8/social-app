import axios from "axios";
import authHeader from "./authHeader";
const API = axios.create({ baseURL: "http://localhost:9090/api/" });
export const uploadImage = (formData) => API.post("/image/", formData, authHeader()).then(function (response) {
    console.log(response.data);
    return response.data;
});
export const uploadPost = (formData) => API.post("/post/", formData, authHeader())