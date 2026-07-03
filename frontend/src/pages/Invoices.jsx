import { useEffect, useState } from "react";
import {
  getAllInvoices,
  addInvoice,
  deleteInvoice,
  updateInvoiceStatus,
  updateInvoice,
  downloadInvoicePdf
} from "../services/invoiceService";
import "../styles/invoices.css";

function Invoices() {
  const [invoices, setInvoices] = useState([]);
  const [loading, setLoading] = useState(false);
  const [search, setSearch] = useState("");
  const [editId, setEditId] = useState(null);
  const [formData, setFormData] = useState({
    clientId: "",
    proposalId: "",
    amount: "",
    issueDate: "",
    dueDate: ""
  });
  const [stats, setStats] = useState({
  total: 0,
  paid: 0,
  pending: 0,
  overdue: 0,
  revenue: 0
});
  useEffect(() => {
    fetchInvoices();
  }, []);

  const fetchInvoices = () => {
  setLoading(true);

  getAllInvoices()
    .then((response) => {
      const data = response.data;

      setInvoices(data);

      // 🔥 CALCULATE STATS HERE
      let total = data.length;
      let paid = 0;
      let pending = 0;
      let overdue = 0;
      let revenue = 0;

      data.forEach((inv) => {
        if (inv.status === "PAID") paid++;
        else if (inv.status === "PENDING") pending++;
        else if (inv.status === "OVERDUE") overdue++;

        if (inv.status === "PAID") {
          revenue += Number(inv.amount);
        }
      });

      setStats({ total, paid, pending, overdue, revenue });
    })
    .catch((error) => {
      console.error("Error fetching invoices:", error);
    })
    .finally(() => {
      setLoading(false);
    });
};

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
  e.preventDefault();

  const payload = {
    clientId: Number(formData.clientId),
    proposalId: Number(formData.proposalId),
    amount: Number(formData.amount),
    issueDate: formData.issueDate,
    dueDate: formData.dueDate
  };

  if (editId) {
    // UPDATE MODE
    updateInvoice(editId, payload)
      .then(() => {
        fetchInvoices();
        resetForm();
      })
      .catch((err) => console.error("Error updating invoice:", err));
  } else {
    // CREATE MODE
    addInvoice(payload)
      .then(() => {
        fetchInvoices();
        resetForm();
      })
      .catch((err) => console.error("Error adding invoice:", err));
  }
};
  const resetForm = () => {
    setFormData({
      clientId: "",
      proposalId: "",
      amount: "",
      issueDate: "",
      dueDate: ""
    });
    setEditId(null);
  };
  const handleEdit = (invoice) => {
    setEditId(invoice.id);

    setFormData({
      clientId: invoice.clientId,
      proposalId: invoice.proposalId,
      amount: invoice.amount,
      issueDate: invoice.issueDate,
      dueDate: invoice.dueDate
    });
  };
  const handleDelete = (id) => {
    if (!window.confirm("Delete this invoice?")) return;

    deleteInvoice(id)
      .then(() => {
        fetchInvoices();
      })
      .catch((error) => {
        console.error("Error deleting invoice:", error);
      });
  };

  const handleStatusChange = (id, status) => {
    updateInvoiceStatus(id, status)
      .then(() => {
        fetchInvoices();
      })
      .catch((error) => {
        console.error("Error updating status:", error);
      });
  };
  const handleDownloadPdf = async (id, invoiceNumber) => {
  try {
    const response = await downloadInvoicePdf(id);

    const url = window.URL.createObjectURL(new Blob([response.data]));

    const link = document.createElement("a");
    link.href = url;
    link.download = `${invoiceNumber}.pdf`;

    document.body.appendChild(link);
    link.click();

    link.remove();
    window.URL.revokeObjectURL(url);

  } catch (error) {
    console.error("Error downloading PDF:", error);
    alert("Unable to download invoice PDF.");
  }
};
  // ✅ SAFE FILTER (prevents crashes)
  const filteredInvoices = invoices.filter((invoice) => {
    return (
      (invoice.invoiceNumber ?? "")
        .toLowerCase()
        .includes(search.toLowerCase()) ||
      (invoice.status ?? "")
        .toLowerCase()
        .includes(search.toLowerCase())
    );
  });

  return (
    <div className="clients-container">
      <h1>Invoices</h1>

      <input
        className="search-input"
        type="text"
        placeholder="🔍 Search by invoice number, status"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      {/* FORM */}
      <form className="client-form" onSubmit={handleSubmit}>
        <input
          type="number"
          name="clientId"
          placeholder="Client ID"
          value={formData.clientId}
          onChange={handleChange}
        />

        <input
          type="number"
          name="proposalId"
          placeholder="Proposal ID"
          value={formData.proposalId}
          onChange={handleChange}
        />

        <input
          type="number"
          name="amount"
          placeholder="Amount"
          value={formData.amount}
          onChange={handleChange}
        />

        <div className="date-field">
          <label>Issue Date</label>
          <input
            type="date"
            name="issueDate"
            value={formData.issueDate}
            onChange={handleChange}
          />
        </div>

        <div className="date-field">
          <label>Due Date</label>
          <input
            type="date"
            name="dueDate"
            value={formData.dueDate}
            onChange={handleChange}
          />
        </div>

        <button type="submit">Add Invoice</button>
      </form>

      {/* TABLE */}
      {loading ? (
        <p>Loading invoices...</p>
      ) : (
        <table className="client-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Invoice Number</th>
              <th>Client ID</th>
              <th>Amount</th>
              <th>Status</th>
              <th>Due Date</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {filteredInvoices.map((invoice) => (
              <tr key={invoice.id}>
                <td>{invoice.id}</td>
                <td>{invoice.invoiceNumber}</td>

                <td>{invoice.clientId}</td>

                <td>₹{Number(invoice.amount).toLocaleString("en-IN")}</td>

                <td>
                  <span className={`status-badge ${invoice.status?.toLowerCase()}`}>
                    {invoice.status}
                  </span>

                  <select
                    value={invoice.status}
                    onChange={(e) =>
                      handleStatusChange(invoice.id, e.target.value)
                    }
                  >
                    <option value="PENDING">PENDING</option>
                    <option value="PAID">PAID</option>
                    <option value="OVERDUE">OVERDUE</option>
                  </select>
                </td>

                <td>{invoice.dueDate}</td>
                <td>
                  <button
                  className="edit-btn"
                  onClick={() => handleEdit(invoice)}
                >
                  Edit
                </button>
                  <button
                    className="delete-btn"
                    onClick={() => handleDelete(invoice.id)}
                  >
                    Delete
                  </button>
                  <button
                    className="pdf-btn"
                    onClick={() =>
                      handleDownloadPdf(invoice.id, invoice.invoiceNumber)
                    }
                  >
                    PDF
                  </button>
                </td>
              </tr>
            ))}

            {filteredInvoices.length === 0 && !loading && (
              <tr>
                <td colSpan="7">No invoices found</td>
              </tr>
            )}
          </tbody>
        </table>
      )}
      <div className="invoice-stats">

  <div className="stat-card">
    <h3>Total</h3>
    <p>{stats.total}</p>
  </div>

  <div className="stat-card">
    <h3>Paid</h3>
    <p>{stats.paid}</p>
  </div>

  <div className="stat-card">
    <h3>Pending</h3>
    <p>{stats.pending}</p>
  </div>

  <div className="stat-card">
    <h3>Overdue</h3>
    <p>{stats.overdue}</p>
  </div>

  <div className="stat-card highlight">
    <h3>Revenue</h3>
    <p>₹{stats.revenue.toLocaleString("en-IN")}</p>
  </div>

</div>
    </div>
  );
}

export default Invoices;