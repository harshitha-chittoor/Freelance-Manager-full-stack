import { useEffect } from "react";
import { FaTimes } from "react-icons/fa";
import "../../styles/clientForm.css";

function ClientForm({
  formData,
  handleChange,
  handleSubmit,
  editingId,
  setEditingId,
  setFormData,
  setShowForm
}) {

  const handleCancel = () => {
    setEditingId(null);

    setFormData({
      name: "",
      email: "",
      company: "",
      phoneNumber: ""
    });

    setShowForm(false);
  };

  useEffect(() => {
    const handleEsc = (e) => {
      if (e.key === "Escape") {
        handleCancel();
      }
    };

    document.addEventListener("keydown", handleEsc);

    return () => {
      document.removeEventListener("keydown", handleEsc);
    };
  }, []);

  return (
    <div
      className="modal-overlay"
      onClick={handleCancel}
    >
      <div
        className="client-modal"
        onClick={(e) => e.stopPropagation()}
      >
        <div className="modal-header">

          <h2>
            {editingId ? "Edit Client" : "Add New Client"}
          </h2>

          <button
            type="button"
            className="close-btn"
            onClick={handleCancel}
          >
            <FaTimes />
          </button>

        </div>

        <form className="client-form" onSubmit={handleSubmit}>

          <input
            type="text"
            name="name"
            placeholder="Client Name"
            value={formData.name}
            onChange={handleChange}
            required
          />

          <input
            type="email"
            name="email"
            placeholder="Email Address"
            value={formData.email}
            onChange={handleChange}
            required
          />

          <input
            type="text"
            name="company"
            placeholder="Company Name"
            value={formData.company}
            onChange={handleChange}
            required
          />

          <input
            type="text"
            name="phoneNumber"
            placeholder="Phone Number"
            value={formData.phoneNumber}
            onChange={handleChange}
            required
          />

          <div className="form-actions">

            <button
              type="button"
              className="cancel-btn"
              onClick={handleCancel}
            >
              Cancel
            </button>

            <button
              type="submit"
              className="submit-btn"
            >
              {editingId ? "Update Client" : "Add Client"}
            </button>

          </div>

        </form>

      </div>
    </div>
  );
}

export default ClientForm;