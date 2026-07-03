import { useTheme } from "../context/ThemeContext";
import "../styles/settings.css";

function Settings() {

    const email = localStorage.getItem("userEmail");

    const { theme, toggleTheme } = useTheme();

    return (

        <div className="settings-page">

            <h1>Settings</h1>

            <p className="settings-subtitle">
                Manage your account preferences.
            </p>

            <div className="settings-card">

                <h3>Account Information</h3>

                <div className="setting-row">

                    <div className="setting-label">

                        <span>Email</span>

                        <span>Your registered account</span>

                    </div>

                    <div className="setting-value">

                        {email}

                    </div>

                </div>

                <div className="setting-row">

                    <div className="setting-label">

                        <span>Theme</span>

                        <span>
                            {theme === "light"
                                ? "Light Mode Enabled"
                                : "Dark Mode Enabled"}
                        </span>

                    </div>

                    <button
                        className="theme-toggle-btn"
                        onClick={toggleTheme}
                    >

                        {theme === "light"
                            ? "🌙 Switch to Dark"
                            : "☀️ Switch to Light"}

                    </button>

                </div>

            </div>

        </div>

    );

}

export default Settings;