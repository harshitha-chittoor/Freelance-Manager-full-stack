import axios from "axios";

const BASE_URL = "http://localhost:8080/api";

// Get all tags of a client
export const getTags = (clientId) => {
    return axios.get(`${BASE_URL}/clients/${clientId}/tags`);
};

// Add tag
export const addTag = (clientId, name) => {
    return axios.post(`${BASE_URL}/clients/${clientId}/tags`, {
        name
    });
};

// Delete tag
export const deleteTag = (tagId) => {
    return axios.delete(`${BASE_URL}/tags/${tagId}`);
};