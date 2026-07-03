import axios from "axios";

const API_URL = "http://localhost:8080/proposals";

// 🔥 reusable auth header (BEST PRACTICE)
const authHeader = () => {
  const token = localStorage.getItem("token");

  return {
    headers: {
      Authorization: token ? `Bearer ${token}` : ""
    }
  };
};

// ================= GET ALL =================
export const getAllProposals = () => {
  return axios.get(API_URL, authHeader());
};

// ================= ADD =================
export const addProposal = (proposalData) => {
  return axios.post(API_URL, proposalData, authHeader());
};

// ================= UPDATE =================
export const updateProposal = (id, proposalData) => {
  return axios.put(`${API_URL}/${id}`, proposalData, authHeader());
};

// ================= DELETE =================
export const deleteProposal = (id) => {
  return axios.delete(`${API_URL}/${id}`, authHeader());
};
// ================= GENERATE INVOICE =================
export const generateInvoice = (proposalId, dueDate) => {
  return axios.post(
    `http://localhost:8080/invoices/generate/${proposalId}`,
    {
      dueDate
    },
    authHeader()
  );
};