import { useEffect, useState } from "react";
import {
  getNotes,
  addNote,
  updateNote,
  deleteNote,
  toggleHighlight
} from "../../services/NoteService";

import {
  FaStar,
  FaRegStar,
  FaTrash,
  FaPen,
  FaStickyNote,
  FaClock
} from "react-icons/fa";

import { toast } from "react-toastify";

import "../../styles/clientNotes.css";

function ClientNotes({ client }) {

  const [notes, setNotes] = useState([]);
  const [noteText, setNoteText] = useState("");
  const [editingId, setEditingId] = useState(null);

  useEffect(() => {

    if (client) {
      loadNotes();
    }

  }, [client]);

  const loadNotes = () => {

    getNotes(client.id)
      .then((response) => {
        setNotes(response.data);
      })
      .catch(() => {
        toast.error("Couldn't load notes");
      });

  };

  const handleSave = () => {

    if (!noteText.trim()) return;

    if (editingId) {

      updateNote(editingId, noteText)
        .then(() => {
          toast.success("Note updated");
          loadNotes();
          setEditingId(null);
          setNoteText("");
        });

    }

    else {

      addNote(client.id, noteText)
        .then(() => {
          toast.success("Note added");
          loadNotes();
          setNoteText("");
        });

    }

  };

  const handleDelete = (id) => {

    if (!window.confirm("Delete this note?")) return;

    deleteNote(id)
      .then(() => {
        toast.success("Note deleted");
        loadNotes();
      });

  };

  const handleHighlight = (id) => {

    toggleHighlight(id)
      .then(() => {
        loadNotes();
      });

  };

  const handleEdit = (note) => {

    setEditingId(note.id);
    setNoteText(note.note);

  };

  const formatDate = (date) => {

    const today = new Date();

    const noteDate = new Date(date);

    const diff = Math.floor(
      (today - noteDate) / (1000 * 60 * 60 * 24)
    );

    if (diff === 0) return "Today";

    if (diff === 1) return "Yesterday";

    if (diff < 7) return diff + " days ago";

    return noteDate.toLocaleDateString();

  };

  if (!client) return null;

  return (

    <div className="client-notes">

      <div className="notes-title">

        <FaStickyNote />

        <h3>Client Notes</h3>

      </div>

      <div className="note-input">

        <textarea
          rows="4"
          placeholder="Write important information about this client..."
          value={noteText}
          onChange={(e)=>setNoteText(e.target.value)}
        />

        <button onClick={handleSave}>

          {editingId ? "Update Note" : "Add Note"}

        </button>

      </div>

      {notes.length===0 && (

        <div className="empty-notes">

            <FaStickyNote size={40}/>

            <h4>No Notes Yet</h4>

            <p>Add the first note for this client.</p>

        </div>

      )}

      <div className="notes-list">

      {

      notes.map((note)=>(

        <div
        key={note.id}
        className={`note-card ${
            note.highlighted ? "highlighted" : ""
        }`}
        >

            <div className="note-header">

                <button
                className="star-btn"
                onClick={()=>handleHighlight(note.id)}
                >

                {

                    note.highlighted ?

                    <FaStar/>

                    :

                    <FaRegStar/>

                }

                </button>

                <span>

                    <FaClock/>

                    {formatDate(note.createdAt)}

                </span>

            </div>

            <p>

                {note.note}

            </p>

            <div className="note-actions">

                <button
                onClick={()=>handleEdit(note)}
                >

                <FaPen/>

                Edit

                </button>

                <button
                onClick={()=>handleDelete(note.id)}
                >

                <FaTrash/>

                Delete

                </button>

            </div>

        </div>

      ))

      }

      </div>

    </div>

  );

}

export default ClientNotes;