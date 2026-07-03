import { Routes, Route } from "react-router-dom";

import LandingLayout from "../layouts/LandingLayout";
import DashboardLayout from "../layouts/DashboardLayout";

import Home from "../pages/Home";
import Login from "../pages/Login";
import Register from "../pages/Register";

import Dashboard from "../pages/Dashboard";
import Clients from "../pages/Clients";
import Proposals from "../pages/Proposals";
import Invoices from "../pages/Invoices";

import ProtectedRoute from "../components/auth/ProtectedRoute";
import Projects from "../pages/Projects";

import Settings from "../pages/Settings";

function AppRoutes() {
  return (
    <Routes>

      {/* Public Routes */}
      <Route element={<LandingLayout />}>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
      </Route>

      {/* Protected Routes */}
      <Route
        element={
          <ProtectedRoute>
            <DashboardLayout />
          </ProtectedRoute>
        }
      >
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/clients" element={<Clients />} />
        <Route path="/proposals" element={<Proposals />} />
        <Route path="/invoices" element={<Invoices />} />
        <Route path="/projects" element={<Projects />} />
        <Route path="/settings" element={<Settings />} />
      </Route>

    </Routes>
  );
}

export default AppRoutes;