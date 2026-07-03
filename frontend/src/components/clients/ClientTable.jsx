import { FaPen, FaTrash } from "react-icons/fa";

function ClientTable({
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
    <table className="client-table">

      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Email</th>
          <th>Company</th>
          <th>Phone</th>
          <th>Actions</th>
        </tr>
      </thead>

      <tbody>

        {filteredClients.length > 0 ? (

          filteredClients.map((client) => (

            <tr
              key={client.id}
              onClick={() => setSelectedClient(client)}
              style={{ cursor: "pointer" }}
            >
              <td>{client.id}</td>
              <td>{client.name}</td>
              <td>{client.email}</td>
              <td>{client.company}</td>
              <td>{client.phoneNumber}</td>

              <td>

                <button
                  className="edit-btn"
                  onClick={(e) => {
                    e.stopPropagation();
                    handleEdit(client);
                  }}
                >
                  <FaPen />
                </button>

                <button
                  className="delete-btn"
                  onClick={(e) => {
                    e.stopPropagation();
                    handleDelete(client.id);
                  }}
                >
                  <FaTrash />
                </button>

              </td>

            </tr>

          ))

        ) : (

          <tr>
            <td
              colSpan="6"
              style={{ textAlign: "center", padding: "15px" }}
            >
              No clients found
            </td>
          </tr>

        )}

      </tbody>

    </table>
  );
}

export default ClientTable;