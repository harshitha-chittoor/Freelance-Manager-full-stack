import Sidebar from "../components/dashboard/Sidebar";
import DashboardHeader from "../components/dashboard/DashboardHeader";
import { Outlet } from "react-router-dom";

function DashboardLayout() {
  return (
    <div className="layout">

      <Sidebar />

      <div className="content">

        <DashboardHeader />

        <Outlet />

      </div>

    </div>
  );
}

export default DashboardLayout;