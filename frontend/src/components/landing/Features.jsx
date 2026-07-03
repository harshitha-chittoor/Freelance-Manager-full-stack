import {
  FaUsers,
  FaFileSignature,
  FaFileInvoiceDollar,
  FaChartLine,
} from "react-icons/fa";
import "../../styles/features.css";
function Features() {
  return (
    <section id="features" className="features">

      <h2>Why Choose Freelance Manager?</h2>

      <p className="features-subtitle">
        Everything you need to manage your freelance business in one place.
      </p>

      <div className="feature-grid">

        <div className="feature-card">
          <FaUsers className="feature-icon" />
          <h3>Client Management</h3>
          <p>
            Store and manage all your clients with ease.
          </p>
        </div>

        <div className="feature-card">
          <FaFileSignature className="feature-icon" />
          <h3>Proposal Tracking</h3>
          <p>
            Create, edit and monitor proposals effortlessly.
          </p>
        </div>

        <div className="feature-card">
          <FaFileInvoiceDollar className="feature-icon" />
          <h3>Invoice Management</h3>
          <p>
            Generate invoices and keep track of payments.
          </p>
        </div>

        <div className="feature-card">
          <FaChartLine className="feature-icon" />
          <h3>Business Dashboard</h3>
          <p>
            View revenue, pending payments and business growth.
          </p>
        </div>

      </div>

    </section>
  );
}

export default Features;