import { useEffect, useState } from "react";
import {
  getReminder,
  saveReminder,
  deleteReminder
} from "../../services/ReminderService";

import { FaBell, FaTrash } from "react-icons/fa";
import { toast } from "react-toastify";
import "../../styles/clientReminder.css";

function ClientReminder({ client }) {

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [date, setDate] = useState("");
  const [reminderId, setReminderId] = useState(null);

  useEffect(() => {

    if (client) {

      getReminder(client.id)
        .then((res) => {

          if (res.data) {
            setReminderId(res.data.id);
            setTitle(res.data.title);
            setDescription(res.data.description);
            setDate(res.data.reminderDate);
          }
          else {
            clearFields();
          }

        })
        .catch(() => clearFields());

    }

  }, [client]);

  const clearFields = () => {

    setReminderId(null);
    setTitle("");
    setDescription("");
    setDate("");

  };

  const handleSave = () => {

    if (!title.trim()) {
      toast.error("Title is required");
      return;
    }

    saveReminder(client.id, {
      title,
      description,
      reminderDate: date
    })
      .then((res) => {

        toast.success("Reminder saved");

        setReminderId(res.data.id);

      })
      .catch(() => toast.error("Failed to save reminder"));

  };

  const handleDelete = () => {

    if (!reminderId) return;

    deleteReminder(reminderId)
      .then(() => {

        toast.success("Reminder deleted");

        clearFields();

      })
      .catch(() => toast.error("Delete failed"));

  };

  if (!client) return null;

  return (

    <div className="client-reminder">

      <h3>
        <FaBell />
        Reminder
      </h3>

      <input
        type="text"
        placeholder="Reminder title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />

      <textarea
        rows="3"
        placeholder="Reminder description"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />

      <input
        type="date"
        value={date}
        onChange={(e) => setDate(e.target.value)}
      />

      <div className="reminder-buttons">

        <button onClick={handleSave}>
          Save Reminder
        </button>

        {reminderId && (

          <button
            className="delete-reminder"
            onClick={handleDelete}
          >
            <FaTrash />
            Delete
          </button>

        )}

      </div>

    </div>

  );

}

export default ClientReminder;