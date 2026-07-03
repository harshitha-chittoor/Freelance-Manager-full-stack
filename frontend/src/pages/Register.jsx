import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../services/authService";
import "../styles/auth.css";

function Register() {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: ""
    });
    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };
    const handleSubmit = (e) => {
        e.preventDefault();

        registerUser(formData)
            .then((response) => {

                alert(response.data);

                if (response.data === "User registered successfully") {

                    setFormData({
                        name: "",
                        email: "",
                        password: ""
                    });

                    navigate("/login");
                }

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

      <h1 className="auth-title">Register</h1>

      <form onSubmit={handleSubmit}>

        <input className="auth-input" name="name" placeholder="Name" value={formData.name} onChange={handleChange} required />

        <input className="auth-input" name="email" placeholder="Email" value={formData.email} onChange={handleChange} required />

        <input className="auth-input" name="password" type="password" placeholder="Password" value={formData.password} onChange={handleChange} required />

        <button className="auth-btn" type="submit">
          Register
        </button>

      </form>

    </div>

  </div>
);
}

export default Register;