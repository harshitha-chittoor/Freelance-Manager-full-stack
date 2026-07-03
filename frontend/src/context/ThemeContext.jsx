import { createContext, useContext, useEffect, useState } from "react";

const ThemeContext = createContext();

export const ThemeProvider = ({ children }) => {

    const [theme, setTheme] = useState("light");

    // Load theme ONLY once on mount
    useEffect(() => {

        const savedTheme = localStorage.getItem("theme");

        if (savedTheme) {
            setTheme(savedTheme);
        }

    }, []);

    // Apply theme whenever it changes
    useEffect(() => {

        document.body.className = theme;
        localStorage.setItem("theme", theme);

        console.log("Applied theme:", theme);

    }, [theme]);

    const toggleTheme = () => {

        console.log("Toggle clicked");

        setTheme(prev => {
            const next = prev === "light" ? "dark" : "light";
            console.log("Switching:", prev, "→", next);
            return next;
        });

    };

    return (
        <ThemeContext.Provider value={{ theme, toggleTheme }}>
            {children}
        </ThemeContext.Provider>
    );
};

export const useTheme = () => useContext(ThemeContext);