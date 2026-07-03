import {
  FaHome,
  FaUsers,
  FaFileAlt,
  FaFileInvoiceDollar,
  FaCog,
  FaSignOutAlt
} from "react-icons/fa";

import { NavLink, useNavigate } from "react-router-dom";

import "../../styles/sidebar.css";

import { FaTasks } from "react-icons/fa";

function Sidebar() {

  const navigate = useNavigate();

  const handleLogout = () => {

    localStorage.removeItem("isLoggedIn");
    localStorage.removeItem("userEmail");

    navigate("/");
  };

  return (

    <aside className="sidebar">

      <div className="sidebar-top">

        <h2>
          💼 Freelance
          <span> Manager</span>
        </h2>

      </div>

      <nav>

        <NavLink to="/dashboard">
          <FaHome />
          Dashboard
        </NavLink>

        <NavLink to="/clients">
          <FaUsers />
          Clients
        </NavLink>

        <NavLink to="/proposals">
          <FaFileAlt />
          Proposals
        </NavLink>

        <NavLink to="/invoices">
          <FaFileInvoiceDollar />
          Invoices
        </NavLink>
        <NavLink to="/projects">
            <FaTasks />
            Projects
        </NavLink>
      </nav>

      <div className="sidebar-bottom">

        <button
            className="settings-btn"
            onClick={() => navigate("/settings")}
        >

          <FaCog />

          Settings

        </button>

        <button
          className="logout-btn"
          onClick={handleLogout}
        >

          <FaSignOutAlt />

          Logout

        </button>

      </div>

    </aside>

  );

}

export default Sidebar;