import axios from "axios";

const API_URL = "http://localhost:8080/clients";

/* -----------------------------
   AUTH HEADER (reusable)
------------------------------*/
const getAuthHeader = () => {
  const token = localStorage.getItem("token");

  return {
    headers: {
      Authorization: `Bearer ${token}`
    }
  };
};

/* -----------------------------
   CLIENT CRUD
------------------------------*/

// Get all clients
export const getAllClients = () => {
  return axios.get(API_URL, getAuthHeader());
};

// Get client by ID
export const getClientById = (id) => {
  return axios.get(`${API_URL}/${id}`, getAuthHeader());
};

// Add new client
export const addClient = (clientData) => {
  return axios.post(API_URL, clientData, getAuthHeader());
};

// Update client
export const updateClient = (id, clientData) => {
  return axios.put(`${API_URL}/${id}`, clientData, getAuthHeader());
};

// Delete client
export const deleteClient = (id) => {
  return axios.delete(`${API_URL}/${id}`, getAuthHeader());
};

/* -----------------------------
   SEARCH
------------------------------*/

// Exact name search
export const searchByName = (name) => {
  return axios.get(
    `${API_URL}/search/name?name=${name}`,
    getAuthHeader()
  );
};

// Exact company search
export const searchByCompany = (company) => {
  return axios.get(
    `${API_URL}/search/company?company=${company}`,
    getAuthHeader()
  );
};

// Partial name search
export const searchByNamePartial = (name) => {
  return axios.get(
    `${API_URL}/search/name/partial?name=${name}`,
    getAuthHeader()
  );
};

// Partial company search
export const searchByCompanyPartial = (company) => {
  return axios.get(
    `${API_URL}/search/company/partial?company=${company}`,
    getAuthHeader()
  );
};

/* -----------------------------
   PAGINATION
------------------------------*/

export const getPaginatedClients = (page, size, field, direction) => {
  return axios.get(
    `${API_URL}/page?page=${page}&size=${size}&field=${field}&direction=${direction}`,
    getAuthHeader()
  );
};