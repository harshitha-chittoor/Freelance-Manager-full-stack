import {
  FaSearch,
  FaBell,
  FaUserCircle
} from "react-icons/fa";

import { useNavigate } from "react-router-dom";
import { useState } from "react";

import "../../styles/dashboardHeader.css";

function DashboardHeader() {

  const navigate = useNavigate();

  const [search, setSearch] = useState("");

  const email = localStorage.getItem("userEmail");

  const hour = new Date().getHours();

  let greeting = "Good Evening";

  if (hour < 12) {
    greeting = "Good Morning";
  } else if (hour < 17) {
    greeting = "Good Afternoon";
  }

  return (

    <header className="dashboard-header">

      <div className="header-left">

        <h2>{greeting} </h2>

        <p>Have a productive day.</p>

      </div>

      <div className="header-right">

        <div className="search-box">

          <FaSearch />

          <input
          type="text"
          placeholder="Search clients, invoices..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          onKeyDown={(e) => {
            if (e.key === "Enter") {
              const value = search.toLowerCase().trim();

              if (value.startsWith("inv")) {
                navigate("/invoices");
              } else if (value.startsWith("proj")) {
                navigate("/projects");
              } else if (value.startsWith("pro")) {
                navigate("/proposals");
              } else {
                navigate("/clients");
              }
            }
          }}
        />
        </div>

        <button className="notification-btn">

          <FaBell />

        </button>

        <div className="profile-box">

          <FaUserCircle className="profile-icon"/>

          <div>

            <h4>{email}</h4>

            <p>Administrator</p>

          </div>

        </div>

      </div>

    </header>

  );

}

export default DashboardHeader;