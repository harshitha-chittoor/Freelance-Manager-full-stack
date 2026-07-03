import { FaUsers, FaFileInvoiceDollar, FaFileSignature, FaRupeeSign } from "react-icons/fa";
import "../../styles/stats.css";

function Stats() {
  const stats = [
    { icon: <FaUsers />, number: "500+", label: "Freelancers" },
    { icon: <FaFileSignature />, number: "1200+", label: "Proposals Sent" },
    { icon: <FaFileInvoiceDollar />, number: "3500+", label: "Invoices Generated" },
    { icon: <FaRupeeSign />, number: "₹2M+", label: "Revenue Tracked" }
  ];

  return (
    <section className="stats">
      <h2>Trusted by Freelancers</h2>

      <div className="stats-grid">
        {stats.map((stat, index) => (
          <div className="stat-card" key={index}>
            <div className="stat-icon">{stat.icon}</div>
            <h3>{stat.number}</h3>
            <p>{stat.label}</p>
          </div>
        ))}
      </div>
    </section>
  );
}

export default Stats;