import { useState } from "react";
import { loginUser, registerUser } from "../../services/authService";
import "../../styles/modal.css";
function AuthModal({ type, onClose }) {

  const [form, setForm] = useState({
    name: "",
    email: "",
    password: ""
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (type === "login") {

      loginUser(form).then((res) => {
        localStorage.setItem("token", res.data);
        localStorage.setItem("isLoggedIn", "true");

        onClose();
        window.location.href = "/dashboard";
      });

    } else {

      registerUser(form).then(() => {
        alert("Registered successfully");
        onClose();
      });

    }
  };

  return (
  <div className="auth-overlay">

    <div className="auth-modal">

      <div className="auth-header">
        <h2>
          {type === "login" ? "Welcome Back" : "Create Account"}
        </h2>

        <p>
          {type === "login"
            ? "Login to continue"
            : "Start managing your freelance business"}
        </p>
      </div>

      <form onSubmit={handleSubmit} className="auth-form">

        {type === "register" && (
          <input
            name="name"
            placeholder="Full Name"
            onChange={handleChange}
          />
        )}

        <input
          name="email"
          placeholder="Email Address"
          onChange={handleChange}
        />

        <input
          name="password"
          type="password"
          placeholder="Password"
          onChange={handleChange}
        />

        <button type="submit" className="auth-btn">
          {type === "login" ? "Login" : "Register"}
        </button>

      </form>

      <button className="auth-close" onClick={onClose}>
        Close
      </button>

    </div>

  </div>
);
}

export default AuthModal;