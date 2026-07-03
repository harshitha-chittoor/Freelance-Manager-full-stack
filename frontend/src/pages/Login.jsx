import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../services/authService";
import { useEffect } from "react";
import "../styles/auth.css"; 

function Login() {

  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: "",
    password: ""
  });
  const [showPassword, setShowPassword] = useState(false);
  useEffect(() => {

    if (localStorage.getItem("isLoggedIn")) {
      navigate("/dashboard");
    }

  }, [navigate]);
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
  e.preventDefault();

  loginUser(formData)
    .then((response) => {

  console.log("Response:", response);
  console.log("Token:", response.data);

  const token = response.data;

  alert("Login successful");

  localStorage.setItem("token", token);
  localStorage.setItem("isLoggedIn", "true");
  localStorage.setItem("userEmail", formData.email);

  navigate("/dashboard");

})
    .catch((error) => {

      console.error(error);

      if (error.response) {
        alert(error.response.data);
      } else {
        alert("Unable to connect to server.");
      }

    });
};

  return (
  <div className="auth-container">

    <div className="auth-box">

      <h1 className="auth-title">Login</h1>

      <form onSubmit={handleSubmit}>

        <input
          className="auth-input"
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          required
        />

        <div className="password-wrapper">

          <input
            className="auth-input"
            type={showPassword ? "text" : "password"}
            name="password"
            placeholder="Password"
            value={formData.password}
            onChange={handleChange}
            required
          />

          <button
            type="button"
            className="toggle-password"
            onClick={() => setShowPassword(!showPassword)}
          >
            {showPassword ? "Hide" : "Show"}
          </button>

        </div>

        <button className="auth-btn" type="submit">
          Login
        </button>

      </form>

    </div>

  </div>
);
}

export default Login;