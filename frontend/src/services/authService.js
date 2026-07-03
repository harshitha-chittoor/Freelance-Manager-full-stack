import axios from "axios";

const BASE_URL = "http://localhost:8080/auth";

export const loginUser = async (loginData) => {
  return axios.post(`${BASE_URL}/login`, loginData);
};

export const registerUser = async (registerData) => {
  return axios.post(`${BASE_URL}/register`, registerData);
};