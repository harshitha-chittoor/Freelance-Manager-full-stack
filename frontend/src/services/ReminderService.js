import axios from "axios";

const BASE_URL = "http://localhost:8080/clients";

export const getReminder = (clientId) => {
    return axios.get(`${BASE_URL}/${clientId}/reminder`);
};

export const saveReminder = (clientId, reminder) => {
    return axios.post(
        `${BASE_URL}/${clientId}/reminder`,
        reminder
    );
};

export const deleteReminder = (id) => {
    return axios.delete(
        `${BASE_URL}/reminders/${id}`
    );
};