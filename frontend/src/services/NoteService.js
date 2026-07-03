import axios from "axios";

const BASE_URL = "http://localhost:8080/api";

// Get all notes of a client
export const getNotes = (clientId) => {
    return axios.get(`${BASE_URL}/clients/${clientId}/notes`);
};

// Add note
export const addNote = (clientId, note) => {
    return axios.post(`${BASE_URL}/clients/${clientId}/notes`, {
        note
    });
};

// Update note
export const updateNote = (noteId, note) => {
    return axios.put(`${BASE_URL}/notes/${noteId}`, {
        note
    });
};

// Delete note
export const deleteNote = (noteId) => {
    return axios.delete(`${BASE_URL}/notes/${noteId}`);
};

// Highlight / Unhighlight
export const toggleHighlight = (noteId) => {
    return axios.patch(`${BASE_URL}/notes/${noteId}/highlight`);
};