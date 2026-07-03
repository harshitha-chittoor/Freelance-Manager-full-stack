import axios from "axios";

const API_URL = "http://localhost:8080/projects";

const authHeader = () => ({
  headers: {
    Authorization: `Bearer ${localStorage.getItem("token")}`
  }
});

// ================= GET ALL =================

export const getAllProjects = () => {
  return axios.get(API_URL, authHeader());
};

// ================= CREATE =================

export const addProject = (projectData) => {
  return axios.post(API_URL, projectData, authHeader());
};

// ================= UPDATE =================

export const updateProject = (id, projectData) => {
  return axios.put(`${API_URL}/${id}`, projectData, authHeader());
};

// ================= DELETE =================

export const deleteProject = (id) => {
  return axios.delete(`${API_URL}/${id}`, authHeader());
};

// ================= UPDATE STATUS =================

export const updateProjectStatus = (id, status) => {
  return axios.put(
    `${API_URL}/${id}/status?status=${status}`,
    null,
    authHeader()
  );
};

// ================= UPDATE PROGRESS =================

export const updateProjectProgress = (id, progress) => {
  return axios.put(
    `${API_URL}/${id}/progress?progress=${progress}`,
    null,
    authHeader()
  );
};

// ================= CREATE FROM PROPOSAL =================

export const createProjectFromProposal = (payload) => {
  console.log("Sending payload to backend:", payload);

  return axios.post(API_URL, payload, authHeader());
};