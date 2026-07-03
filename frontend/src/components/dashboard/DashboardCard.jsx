import "../../styles/dashboardCard.css";

function DashboardCard({ icon, title, value, subtitle, iconClass }) {
  return (
    <div className="dashboard-card">

      <div className={`card-icon ${iconClass}`}>
        {icon}
      </div>

      <div className="card-content">

        <h3>{title}</h3>

        <h2>{value}</h2>

        <p>{subtitle}</p>

      </div>

    </div>
  );
}

export default DashboardCard;