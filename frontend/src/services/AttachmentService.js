import axios from "axios";

const BASE_URL = "http://localhost:8080/clients";

export const uploadAttachment = (clientId, file) => {
    const formData = new FormData();
    formData.append("file", file);

    return axios.post(
        `${BASE_URL}/${clientId}/attachments`,
        formData,
        {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        }
    );
};

export const getAttachments = (clientId) => {
    return axios.get(`${BASE_URL}/${clientId}/attachments`);
};

export const deleteAttachment = (attachmentId) => {
    return axios.delete(`${BASE_URL}/attachments/${attachmentId}`);
};