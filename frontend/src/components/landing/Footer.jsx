import { FaBriefcase, FaGithub, FaLinkedin, FaEnvelope } from "react-icons/fa";
import "../../styles/footer.css";

function Footer() {
  return (
    <footer id="contact" className="footer">

      <div className="footer-container">

        <div className="footer-section">

          <div className="footer-logo">
            <FaBriefcase />
            <span>Freelance Manager</span>
          </div>

          <p>
            Manage clients, proposals, invoices and business growth
            from one powerful platform.
          </p>

        </div>

        <div className="footer-section">

          <h3>Quick Links</h3>

          <a href="#features">Features</a>
          <a href="#about">About</a>
          <a href="/login">Login</a>
          <a href="/register">Register</a>

        </div>

        <div className="footer-section">

          <h3>Contact</h3>

          <p>
            <FaEnvelope /> harshitha@example.com
          </p>

          <p>
            <FaGithub /> github.com/yourusername
          </p>

          <p>
            <FaLinkedin /> linkedin.com/in/yourprofile
          </p>

        </div>

      </div>

      <hr />

      <p className="copyright">
        © 2026 Freelance Manager. All Rights Reserved.
      </p>

    </footer>
  );
}

export default Footer;