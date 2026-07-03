import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getRecentInvoices } from "../../services/invoiceService";
import "../../styles/recentInvoices.css";

function RecentInvoices() {

  const [invoices, setInvoices] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
      loadRecentInvoices();
    }, []);

    const loadRecentInvoices = async () => {
      try {
        const data = await getRecentInvoices();
        setInvoices(data);
      } catch (error) {
        console.error("Error loading recent invoices:", error);
      }
    };

  return (

    <div className="recent-invoices">

      <div className="recent-header">

        <h2>Recent Invoices</h2>

        <button
          className="view-all-btn"
          onClick={() => navigate("/invoices")}
        >
          View All →
        </button>

      </div>

      <div className="invoice-list">

  {invoices.length === 0 ? (
    <p style={{ textAlign: "center", padding: "20px" }}>
      No recent invoices.
    </p>
  ) : (
    invoices.map((invoice) => (

    <div className="invoice-item" key={invoice.id}>

      <div className="invoice-left">

        <div className="invoice-id">
          {invoice.invoiceNumber}
        </div>

        <div className="invoice-client">
          Client #{invoice.clientId}
        </div>

      </div>

      <div className="invoice-middle">

        <div className="invoice-amount">
          ₹{invoice.amount}
        </div>

        <div className="invoice-date">
          Due {new Date(invoice.dueDate).toLocaleDateString()}
        </div>

      </div>

      <span className={`invoice-status ${invoice.status.toLowerCase()}`}>
        {invoice.status}
      </span>

    </div>

  ))
)}

</div>

    </div>

  );

}

export default RecentInvoices;