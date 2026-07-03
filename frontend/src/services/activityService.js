import axios from "axios";

const API_URL = "http://localhost:8080/activity";

const getAuthHeader = () => {
  return {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`
    }
  };
};

export const getRecentActivities = () => {
  return axios.get(`${API_URL}/recent`, getAuthHeader());
};