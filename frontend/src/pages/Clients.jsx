import { useEffect, useState } from "react";
import {
  getAllClients,
  addClient,
  updateClient,
  deleteClient
} from "../services/ClientService";

import ClientTable from "../components/clients/ClientTable";
import ClientForm from "../components/clients/ClientForm";
import { toast } from "react-toastify";
import ClientCardView from "../components/clients/ClientCardView";
import { FaTable, FaThLarge } from "react-icons/fa";
import ClientDetails from "../components/clients/ClientDetails";

function Clients() {
  const [clients, setClients] = useState([]);
  const [loading, setLoading] = useState(false);
  const [search, setSearch] = useState("");

  const [formData, setFormData] = useState({
    name: "",
    email: "",
    company: "",
    phoneNumber: ""
  });

  const [editingId, setEditingId] = useState(null);
  const [viewMode, setViewMode] = useState("table");
  const [showForm, setShowForm] = useState(false);
  const [selectedClient, setSelectedClient] = useState(null);
  
  // ---------------- FETCH CLIENTS ----------------
  const loadClients = () => {
    setLoading(true);

    getAllClients()
      .then((response) => {
        setClients(response.data.data);
      })
      .catch((error) => {
        console.error("Error fetching clients:", error);
        toast.error("Failed to load clients");
      })
      .finally(() => {
        setLoading(false);
      });
  };

  useEffect(() => {
    loadClients();
  }, []);

  // ---------------- HANDLE INPUT ----------------
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  // ---------------- SUBMIT (ADD / UPDATE) ----------------
  const handleSubmit = async (e) => {
  e.preventDefault();

  try {
    if (editingId) {
      await updateClient(editingId, formData);
      toast.success("Client updated successfully!");
    } else {
      await addClient(formData);
      toast.success("Client added successfully!");
    }

    setFormData({
      name: "",
      email: "",
      company: "",
      phoneNumber: ""
    });

    setEditingId(null);
    setShowForm(false);

    await loadClients(); // ensure refresh after DB write

  } catch (error) {
    console.error("Submit error:", error);
    toast.error(
      error?.response?.data?.message || "Operation failed"
    );
  }
};
  // ---------------- DELETE ----------------
  const handleDelete = (id) => {
    const confirmDelete = window.confirm("Delete this client?");
    if (!confirmDelete) return;

    deleteClient(id)
      .then(() => {
        loadClients();
        toast.success("Client deleted successfully!");
      })
      .catch((error) => {
        console.error("Error deleting client:", error);
        toast.error("Failed to delete client");
      });
  };

  // ---------------- EDIT ----------------
  const handleEdit = (client) => {
      setEditingId(client.id);
      setFormData({
        name: client.name,
        email: client.email,
        company: client.company,
        phoneNumber: client.phoneNumber
      });
      setShowForm(true);
    };
    const totalClients = clients.length;

    const uniqueCompanies = new Set(
      clients.map((c) => c.company)
    ).size;

    const recentClients = clients.slice(-3).length;

    const activeClients = clients.filter((c) =>
      c.email && c.phoneNumber
    ).length;
  // ---------------- UI ----------------
  return (
    <div className="clients-container">
      <div className="client-header">
          <h1>Clients</h1>
          <button
              className="add-client-btn"
              onClick={() => setShowForm(true)}
          >
              + Add Client
          </button>
      </div>
      <div className="search-container">
        <input
          type="text"
          className="search-input"
          placeholder="Search by name, email, company"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
      </div>
      <div className="view-toggle">
          <button
            className={viewMode === "table" ? "active-view" : ""}
            onClick={() => setViewMode("table")}
          >
            <FaTable />
            <span>Table View</span>
          </button>

          <button
            className={viewMode === "card" ? "active-view" : ""}
            onClick={() => setViewMode("card")}
          >
            <FaThLarge />
            <span>Card View</span>
          </button>
        </div>
      {showForm && (
        <ClientForm
            formData={formData}
            handleChange={handleChange}
            handleSubmit={handleSubmit}
            editingId={editingId}
            setEditingId={setEditingId}
            setFormData={setFormData}
            setShowForm={setShowForm}
        />
      )}

      {loading ? (
        <div className="loading-state">
          Loading clients...
        </div>
      ) : (
        viewMode === "table" ? (
          <ClientTable
            clients={clients}
            search={search}
            handleEdit={handleEdit}
            handleDelete={handleDelete}
            setSelectedClient={setSelectedClient}
          />
        ) : (
          <ClientCardView
            clients={clients}
            search={search}
            handleEdit={handleEdit}
            handleDelete={handleDelete}
            setSelectedClient={setSelectedClient}
          />
        )    
      )}
      <ClientDetails
        client={selectedClient}
        handleEdit={handleEdit}
      />
    </div>
  );
}

export default Clients;