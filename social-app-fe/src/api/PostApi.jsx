import axios from "axios";
import authHeader from "./authHeader";
const API = axios.create({ baseURL: "http://localhost:9090/api/" });

export const getTimelinePosts = () => API.get("/post/list", authHeader())