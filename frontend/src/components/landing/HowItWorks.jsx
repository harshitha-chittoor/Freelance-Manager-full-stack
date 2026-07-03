import {
  FaUserPlus,
  FaUsers,
  FaFileSignature,
  FaFileInvoiceDollar,
  FaChartLine,
} from "react-icons/fa";

import "../../styles/howItWorks.css";

function HowItWorks() {
  const steps = [
    {
      icon: <FaUserPlus />,
      title: "Register",
      description: "Create your freelancer account in seconds.",
    },
    {
      icon: <FaUsers />,
      title: "Add Clients",
      description: "Store and organize all your clients.",
    },
    {
      icon: <FaFileSignature />,
      title: "Create Proposals",
      description: "Send professional proposals to clients.",
    },
    {
      icon: <FaFileInvoiceDollar />,
      title: "Generate Invoices",
      description: "Create invoices and track payments.",
    },
    {
      icon: <FaChartLine />,
      title: "Monitor Growth",
      description: "Analyze revenue and pending payments.",
    },
  ];

  return (
    <section className="how-it-works">
      <h2>How It Works</h2>

      <p className="how-subtitle">
        Get started in just a few simple steps.
      </p>

      <div className="steps">

        {steps.map((step, index) => (
          <div className="step-card" key={index}>

            <div className="step-icon">
              {step.icon}
            </div>

            <h3>{step.title}</h3>

            <p>{step.description}</p>

          </div>
        ))}

      </div>
    </section>
  );
}

export default HowItWorks;