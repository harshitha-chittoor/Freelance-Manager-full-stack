import { useEffect, useState } from "react";
import { getRecentActivities } from "../../services/activityService";
import "../../styles/recentActivity.css";
import {
  FaCheckCircle,
  FaUserPlus,
  FaFileInvoice,
  FaFileAlt
} from "react-icons/fa";

function formatTimeAgo(dateString) {

  const now = new Date();
  const activityDate = new Date(dateString);

  const seconds = Math.floor((now - activityDate) / 1000);

  if (seconds < 60) return "Just now";

  const minutes = Math.floor(seconds / 60);

  if (minutes < 60)
    return `${minutes} minute${minutes > 1 ? "s" : ""} ago`;

  const hours = Math.floor(minutes / 60);

  if (hours < 24)
    return `${hours} hour${hours > 1 ? "s" : ""} ago`;

  const days = Math.floor(hours / 24);

  if (days === 1) return "Yesterday";

  return `${days} days ago`;
}

function RecentActivity() {

  const [activities, setActivities] = useState([]);

  useEffect(() => {
    getRecentActivities()
      .then((response) => {
        setActivities(response.data);
      })
      .catch((error) => {
        console.error("Error fetching activities:", error);
      });
  }, []);
  return (

    <div className="activity-card">

      <div className="activity-header">

  <div>

    <h2>Recent Activity</h2>

            <p className="activity-subtitle">
            Latest updates from your workspace
            </p>

        </div>

        <div className="live-status">

            <span className="live-dot"></span>

            Live

        </div>

        </div>

      <div className="activity-list">

        {activities.map((item) => {

            let icon = <FaCheckCircle />;
            let color = "green";

            switch (item.type) {
              case "CLIENT":
                icon = <FaUserPlus />;
                color = "blue";
                break;

              case "PROPOSAL":
                icon = <FaFileAlt />;
                color = "purple";
                break;

              case "INVOICE":
                icon = <FaFileInvoice />;
                color = "orange";
                break;

              default:
                icon = <FaCheckCircle />;
                color = "green";
            }

            return (
              <div className="activity-item" key={item.id}>

                <div className={`activity-icon ${color}`}>
                  {icon}
                </div>

                <div>
                  <h4>{item.message}</h4>
                  <span>{formatTimeAgo(item.createdAt)}</span>
                </div>

              </div>
            );

          })}

      </div>

    </div>

  );
}

export default RecentActivity;