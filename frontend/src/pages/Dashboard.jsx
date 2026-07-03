import { useEffect, useState } from "react";
import { getDashboardData } from "../services/dashboardService";
import WelcomeBanner from "../components/dashboard/WelcomeBanner";
import DashboardCard from "../components/dashboard/DashboardCard";
import {
  FaUsers,
  FaFileInvoiceDollar,
  FaMoneyBillWave,
  FaExclamationTriangle,
  FaFileAlt
} from "react-icons/fa";
import RevenueChart from "../components/dashboard/RevenueChart";
import RecentActivity from "../components/dashboard/RecentActivity";
import RecentInvoices from "../components/dashboard/RecentInvoices";
import QuickActions from "../components/dashboard/QuickActions";


function Dashboard() {

  const [dashboard, setDashboard] = useState({
    totalRevenue: 0,
    pendingRevenue: 0,
    totalClients: 0,
    totalInvoices: 0,
    overdueInvoices: 0
  });

  useEffect(() => {

    getDashboardData()
        .then((response) => {
            console.log(response.data);
            setDashboard(response.data);
        })
      .catch((error) => {
        console.error("Error fetching dashboard data:", error);
      });

  }, []);

  return (
    <div>
      <WelcomeBanner />
      <div className="dashboard-heading">
        <h1 className="dashboard-title">
          Business Overview
        </h1>
        <p className="dashboard-subtitle">
          Track your freelance performance at a glance.
        </p>
      </div>
      <div className="card-container">

        <DashboardCard
          icon={<FaMoneyBillWave />}
          title="Total Revenue"
          value={`₹${dashboard.totalRevenue}`}
          subtitle="Revenue generated"
          iconClass="blue"
        />

        <DashboardCard
          icon={<FaFileInvoiceDollar />}
          title="Pending Revenue"
          value={`₹${dashboard.pendingRevenue}`}
          subtitle="Awaiting payment"
          iconClass="orange"
        />

        <DashboardCard
          icon={<FaExclamationTriangle />}
          title="Overdue Invoices"
          value={dashboard.overdueInvoices}
          subtitle="Need attention"
          iconClass="red"
        />

        <DashboardCard
          icon={<FaUsers />}
          title="Total Clients"
          value={dashboard.totalClients}
          subtitle="Active clients"
          iconClass="green"
        />

        <DashboardCard
          icon={<FaFileAlt />}
          title="Total Invoices"
          value={dashboard.totalInvoices}
          subtitle="Invoices created"
          iconClass="purple"
        />

      </div>
      <div className="analytics-section">
          <RevenueChart />
          <RecentActivity />
      </div>
      <div className="right-column">
        <RecentInvoices />
        <QuickActions />
      </div>
    </div>
  );
}

export default Dashboard;