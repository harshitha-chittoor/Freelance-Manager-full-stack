import { useNavigate } from "react-router-dom";
import {
  FaUserPlus,
  FaFileAlt,
  FaFileInvoiceDollar
} from "react-icons/fa";

import "../../styles/quickActions.css";

function QuickActions() {

  const navigate = useNavigate();

  return (
    <div className="quick-actions-container">

      <h3 className="quick-actions-title">
        Quick Actions
      </h3>

      <div className="quick-actions-grid">

        <button
          className="action-card"
          onClick={() => navigate("/clients")}
        >
          <FaUserPlus className="action-icon" />
          <span className="action-text">
            Add Client
          </span>
        </button>

        <button
          className="action-card"
          onClick={() => navigate("/proposals")}
        >
          <FaFileAlt className="action-icon" />
          <span className="action-text">
            Create Proposal
          </span>
        </button>

        <button
          className="action-card"
          onClick={() => navigate("/invoices")}
        >
          <FaFileInvoiceDollar className="action-icon" />
          <span className="action-text">
            Create Invoice
          </span>
        </button>

      </div>

    </div>
  );
}

export default QuickActions;