import {
  FaPhone,
  FaWhatsapp,
  FaEnvelope,
  FaCopy,
  FaMapMarkerAlt
} from "react-icons/fa";

import { toast } from "react-toastify";
import "../../styles/quickActions.css";

function QuickActions({ client }) {
  if (!client) return null;

  // 🔍 SAFE PHONE EXTRACTOR (FIX FOR YOUR BUG)
  const getPhone = () => {
    return (
      client?.phone ||
      client?.phoneNumber ||
      client?.contact?.phone ||
      client?.mobile ||
      ""
    );
  };

  // 📋 Copy Details
  const handleCopy = () => {
    const text = `
Name: ${client?.name || "N/A"}
Email: ${client?.email || "N/A"}
Phone: ${getPhone() || "N/A"}
Address: ${client?.address || "N/A"}
    `;

    navigator.clipboard.writeText(text.trim());
    toast.success("Client details copied");
  };

  // 📞 Call
  const handleCall = () => {
    const phone = getPhone();

    if (!phone) return toast.error("No phone number found");

    const clean = phone.replace(/\D/g, "");

    if (!clean) return toast.error("Invalid phone number");

    window.open(`tel:${clean}`);
  };

  // 📧 Email
  const handleEmail = () => {
    const email = client?.email;

    if (!email) return toast.error("No email found");

    window.open(`mailto:${email}`);
  };

  // 💬 WhatsApp
  const handleWhatsApp = () => {
    const phone = getPhone();

    if (!phone) return toast.error("No phone number found");

    let clean = phone.replace(/\D/g, "");

    // 🇮🇳 auto-fix for India numbers
    if (clean.length === 10) {
      clean = `91${clean}`;
    }

    if (clean.length < 10) {
      return toast.error("Invalid phone number");
    }

    const message = encodeURIComponent(
      `Hello ${client?.name || "there"}, I would like to connect with you.`
    );

    const url = `https://wa.me/${clean}?text=${message}`;

    window.open(url, "_blank", "noopener,noreferrer");
  };

  // 🗺️ Map
  const handleMap = () => {
    const query = client?.address || client?.name;

    if (!query) return toast.error("No location available");

    const url = `https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(
      query
    )}`;

    window.open(url, "_blank", "noopener,noreferrer");
  };

  return (
    <div className="quick-actions">
      <h3>⚡ Quick Actions</h3>

      <div className="action-buttons">

        <button onClick={handleCall}>
          <FaPhone /> Call
        </button>

        <button onClick={handleWhatsApp}>
          <FaWhatsapp /> WhatsApp
        </button>

        <button onClick={handleEmail}>
          <FaEnvelope /> Email
        </button>

        <button onClick={handleCopy}>
          <FaCopy /> Copy
        </button>

        <button onClick={handleMap}>
          <FaMapMarkerAlt /> Map
        </button>

      </div>
    </div>
  );
}

export default QuickActions;