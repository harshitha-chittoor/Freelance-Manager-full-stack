import "../../styles/clientCardView.css";
import {
  FaEnvelope,
  FaPhone,
  FaPen,
  FaTrash
} from "react-icons/fa";

function ClientCardView({
  clients,
  search,
  handleEdit,
  handleDelete,
  setSelectedClient
}) {

  const filteredClients = clients.filter(
    (client) =>
      client.name.toLowerCase().includes(search.toLowerCase()) ||
      client.email.toLowerCase().includes(search.toLowerCase()) ||
      client.company.toLowerCase().includes(search.toLowerCase())
  );

  return (

    <div className="client-card-grid">

      {filteredClients.map((client) => (

        <div
          className="client-card"
          key={client.id}
          onClick={() => setSelectedClient(client)}
          style={{ cursor: "pointer" }}
        >

          <div className="client-avatar">
            {client.name
              .split(" ")
              .map(word => word[0])
              .join("")
              .toUpperCase()}
          </div>

          <h3>{client.name}</h3>

          <p className="company">{client.company}</p>

          <div className="client-info">

            <p>
              <FaEnvelope /> {client.email}
            </p>

            <p>
              <FaPhone /> {client.phoneNumber}
            </p>

          </div>

          <div className="client-actions">

            <button
              className="edit-btn"
              onClick={(e) => {
                e.stopPropagation();
                handleEdit(client);
              }}
            >
              <FaPen /> Edit
            </button>

            <button
              className="delete-btn"
              onClick={(e) => {
                e.stopPropagation();
                handleDelete(client.id);
              }}
            >
              <FaTrash /> Delete
            </button>

          </div>

        </div>

      ))}

    </div>

  );
}

export default ClientCardView;