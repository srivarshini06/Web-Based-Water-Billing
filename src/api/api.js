import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080/api", // your Spring Boot backend
});

export const getSensors = () => API.get("/sensors");
export const getAlerts = () => API.get("/alerts");
export const loginUser = (data) => API.post("/auth/login", data);

export default API;