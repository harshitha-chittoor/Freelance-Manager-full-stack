import axios from "axios";

const API_URL = "http://localhost:8080/invoices";

const authHeader = () => ({
  headers: {
    Authorization: `Bearer ${localStorage.getItem("token")}`
  }
});

export const getAllInvoices = () => {
  return axios.get(API_URL, authHeader());
};

export const addInvoice = (invoiceData) => {
  return axios.post(API_URL, invoiceData, authHeader());
};

export const deleteInvoice = (id) => {
  return axios.delete(`${API_URL}/${id}`, authHeader());
};
export const updateInvoice = (id, data) => {
  return axios.put(`${API_URL}/${id}`, data, authHeader());
};
export const updateInvoiceStatus = (id, status) => {
  return axios.put(
  `${API_URL}/${id}/status?status=${status}`,
  null,
  authHeader()
);
};
export const downloadInvoicePdf = async (id) => {
  return axios.get(
    `http://localhost:8080/invoices/${id}/pdf`,
    {
      responseType: "blob",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    }
  );
};
export const getRecentInvoices = async () => {
  const response = await axios.get(API_URL, authHeader());

  return response.data
    .sort((a, b) => b.id - a.id) // newest first
    .slice(0, 5);                // latest 5 invoices
};