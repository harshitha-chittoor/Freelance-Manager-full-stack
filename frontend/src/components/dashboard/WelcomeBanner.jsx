import "../../styles/welcomeBanner.css";
import { useNavigate } from "react-router-dom";

function WelcomeBanner() {

    const navigate = useNavigate();

    const email = localStorage.getItem("userEmail");

    const name = email
        ? email.split("@")[0]
        : "User";

    return (

        <div className="welcome-banner">

            <div>

                <h2>Business Dashboard</h2>

                <p>
                   Here's what's happening with your freelance business today.
                </p>

            </div>

            <div className="banner-buttons">

                <button
                    className="primary-btn"
                    onClick={() => navigate("/clients")}
                >
                    + Add Client
                </button>

                <button
                    className="secondary-btn"
                    onClick={() => navigate("/invoices")}
                >
                    Create Invoice
                </button>

            </div>

        </div>

    );
}

export default WelcomeBanner;