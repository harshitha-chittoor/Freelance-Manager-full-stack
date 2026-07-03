import { useEffect, useState } from "react";
import {
  uploadAttachment,
  getAttachments,
  deleteAttachment
} from "../../services/AttachmentService";

import { FaTrash, FaUpload } from "react-icons/fa";
import { toast } from "react-toastify";
import "../../styles/clientAttachments.css";

function ClientAttachments({ client }) {

  const [file, setFile] = useState(null);
  const [attachments, setAttachments] = useState([]);

  useEffect(() => {
    if (client) {
      loadAttachments();
    }
  }, [client]);

  const loadAttachments = () => {
    getAttachments(client.id)
      .then((res) => setAttachments(res.data))
      .catch(() => setAttachments([]));
  };

  const handleUpload = () => {
    if (!file) {
      toast.error("Select a file first");
      return;
    }

    uploadAttachment(client.id, file)
      .then(() => {
        toast.success("Uploaded successfully");
        setFile(null);
        loadAttachments();
      })
      .catch(() => toast.error("Upload failed"));
  };

  const handleDelete = (id) => {
    deleteAttachment(id)
      .then(() => {
        toast.success("Deleted");
        loadAttachments();
      })
      .catch(() => toast.error("Delete failed"));
  };

  if (!client) return null;

  return (
    <div className="attachments-box">

      <h3>📎 Attachments</h3>

      <input
        type="file"
        onChange={(e) => setFile(e.target.files[0])}
      />

      <button onClick={handleUpload}>
        <FaUpload /> Upload
      </button>

      <div className="attachment-list">

        {attachments.map((att) => (
          <div key={att.id} className="attachment-item">

            <span>{att.fileName}</span>

            <button onClick={() => handleDelete(att.id)}>
              <FaTrash />
            </button>

          </div>
        ))}

      </div>

    </div>
  );
}

export default ClientAttachments;