import "../../styles/clientDetails.css";
import {
  FaEnvelope,
  FaPhone,
  FaBuilding
} from "react-icons/fa";

import { useNavigate } from "react-router-dom";

import ClientNotes from "./ClientNotes";
import ClientTags from "./ClientTags";
import ClientReminder from "./ClientReminder";
import ClientAttachments from "./ClientAttachments";
import QuickActions from "./QuickActions";

function ClientDetails({ client, handleEdit }) {
  const navigate = useNavigate();

  if (!client) {
    return (
      <div className="client-details">
        <h2>Client Details</h2>

        <div className="empty-details">
          Select a client to view details.
        </div>
      </div>
    );
  }

  // 🔥 ACTIONS
  const onEditClick = () => {
  console.log("ClientDetails client:", client);
  handleEdit(client);
};

    const handleProposal = () => {
      navigate("/proposals", {
        state: {
          client: client
        }
      });
    };

   const handleInvoice = () => {
      console.log("Invoice button clicked");
      console.log("Navigating to /invoices");

      navigate("/invoices", {
        state: {
          client: client,
        },
      });
    };

  // 🔥 SAFE PHONE (fix mismatch issue)
  const getPhone = () => {
    return client?.phone || client?.phoneNumber || client?.contact?.phone;
  };

  return (
    <div className="client-details">

      <h2>Client Details</h2>

      {/* HEADER */}
      <div className="details-header">

        <div className="details-avatar">
          {client.name
            ?.split(" ")
            .map(word => word[0])
            .join("")
            .toUpperCase()}
        </div>

        <div>
          <h3>{client.name}</h3>
          <p>{client.company}</p>
        </div>

      </div>

      {/* INFO */}
      <div className="details-info">

        <p>
          <FaEnvelope />
          {client.email}
        </p>

        <p>
          <FaPhone />
          {getPhone()}
        </p>

        <p>
          <FaBuilding />
          {client.company}
        </p>

      </div>

      {/* STATUS */}
      <div className="details-status">
        <span className="status active">Active</span>
      </div>

      {/* ACTION BUTTONS (FIXED) */}
      <div className="quick-actions">

        <button onClick={onEditClick}>
          Edit Client
        </button>

        <button onClick={handleProposal}>
          Create Proposal
        </button>

        <button onClick={handleInvoice}>
          Create Invoice
        </button>

      </div>

      {/* COMPONENTS */}
      <QuickActions client={client} />
      <ClientTags client={client} />
      <ClientReminder client={client} />
      <ClientAttachments client={client} />
      <ClientNotes client={client} />

    </div>
  );
}

export default ClientDetails;