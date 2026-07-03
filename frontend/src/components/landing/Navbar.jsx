import { Link } from "react-router-dom";
import { FaBriefcase } from "react-icons/fa";
import { useState } from "react";
import AuthModal from "../auth/AuthModal"; // we will create inside landing/auth or components/auth

function Navbar() {
  const [modal, setModal] = useState(null);
  return (
    <nav className="landing-navbar">

      <div className="logo">
        <FaBriefcase className="logo-icon" />
        <span>Freelance Manager</span>
      </div>

      <ul className="nav-links">
        <li><a href="#features">Features</a></li>
        <li><a href="#about">About</a></li>
        <li><a href="#contact">Contact</a></li>
      </ul>

      <div className="nav-buttons">

  <button
    className="login-btn"
    onClick={() => setModal("login")}
  >
    Login
  </button>

  <button
    className="register-btn"
    onClick={() => setModal("register")}
  >
    Register
  </button>

</div>
    {modal && (
  <AuthModal
    type={modal}
    onClose={() => setModal(null)}
  />
)}
    </nav>
  );
}

export default Navbar;