import { useEffect, useState } from "react";
import { getTags, addTag, deleteTag } from "../../services/TagService";
import { FaPlus, FaTimes, FaTags } from "react-icons/fa";
import { toast } from "react-toastify";
import "../../styles/clientTags.css";

function ClientTags({ client }) {

  const [tags, setTags] = useState([]);
  const [tagName, setTagName] = useState("");

  useEffect(() => {
    if (client) {
      loadTags();
    }
  }, [client]);

  const loadTags = () => {
    getTags(client.id)
      .then((res) => setTags(res.data))
      .catch(() => toast.error("Failed to load tags"));
  };

  const handleAdd = () => {

    if (!tagName.trim()) return;

    addTag(client.id, tagName)
      .then(() => {
        toast.success("Tag added");
        setTagName("");
        loadTags();
      })
      .catch(() => toast.error("Failed to add tag"));

  };

  const handleDelete = (id) => {

    deleteTag(id)
      .then(() => {
        toast.success("Tag deleted");
        loadTags();
      })
      .catch(() => toast.error("Failed to delete tag"));

  };

  if (!client) return null;

  return (

    <div className="client-tags">

      <div className="tags-header">
        <FaTags />
        <h3>Tags</h3>
      </div>

      <div className="tag-input">

        <input
          type="text"
          placeholder="Add a tag..."
          value={tagName}
          onChange={(e) => setTagName(e.target.value)}
        />

        <button onClick={handleAdd}>
          <FaPlus />
        </button>

      </div>

      <div className="tags-list">

        {tags.length === 0 ? (
          <p className="no-tags">No tags added.</p>
        ) : (
          tags.map((tag) => (
            <div className="tag-chip" key={tag.id}>

              <span>{tag.name}</span>

              <button
                onClick={() => handleDelete(tag.id)}
              >
                <FaTimes />
              </button>

            </div>
          ))
        )}

      </div>

    </div>

  );

}

export default ClientTags;