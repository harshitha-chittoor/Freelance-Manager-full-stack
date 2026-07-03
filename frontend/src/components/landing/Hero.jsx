import { Link } from "react-router-dom";
import { FaArrowRight } from "react-icons/fa";
import heroImage from "../../assets/hero1.svg";
import "../../styles/hero.css";
function Hero() {
  return (
    <section className="hero">

      <div className="hero-left">

        <h1>
          Manage Your Freelance Business
          <span> Smarter.</span>
        </h1>

        <p>
          Organize clients, create proposals, generate invoices
          and track your business growth from one powerful dashboard.
        </p>

        <div className="hero-buttons">

          <Link to="/register" className="hero-register">
            Get Started
          </Link>

          <Link to="/login" className="hero-login">
            Login
            <FaArrowRight />
          </Link>

        </div>

      </div>

      <div className="hero-right">

        <img
            src={heroImage}
            alt="Freelance Manager Hero"
        />

      </div>

    </section>
  );
}

export default Hero;