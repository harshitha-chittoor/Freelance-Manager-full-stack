import axios from "axios";

const API_URL = "http://localhost:8080/dashboard";

const getAuthHeader = () => {
  return {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`
    }
  };
};

export const getDashboardData = () => {
  return axios.get(API_URL, getAuthHeader());
};
export const getRevenueData = () => {

  const token = localStorage.getItem("token");

  return axios.get("http://localhost:8080/dashboard/revenue", {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
};