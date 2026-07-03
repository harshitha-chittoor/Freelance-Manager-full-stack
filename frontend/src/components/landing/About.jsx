import aboutImage from "../../assets/collaborations.svg";
import "../../styles/about.css";

function About() {
  return (
    <section id="about" className="about">

      <div className="about-image">
        <img src={aboutImage} alt="About Freelance Manager" />
      </div>

      <div className="about-content">

        <h2>About Freelance Manager</h2>

        <p>
          Freelance Manager is a complete platform designed to help freelancers
          organize their work efficiently. From managing clients to creating
          proposals, generating invoices, and tracking payments, everything is
          available in one place.
        </p>

        <p>
          Our goal is to simplify business management so freelancers can spend
          more time focusing on their projects and less time on paperwork.
        </p>

      </div>

    </section>
  );
}

export default About;