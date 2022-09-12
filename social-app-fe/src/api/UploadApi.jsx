import axios from "axios";
const API = axios.create({ baseURL: "http://localhost:9090/api/" });
export const uploadImage = (formData) => API.post("/image/", formData);
