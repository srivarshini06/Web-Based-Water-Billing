import { Routes, Route } from "react-router-dom";

// Landing
import Home from "../pages/landing/Home";

// Authentication
import Login from "../pages/auth/Login";
import Register from "../pages/auth/Register";

// Resident
import ResidentDashboard from "../pages/resident/ResidentDashboard";
import Usage from "../pages/resident/Usage";
import Bills from "../pages/resident/Bills";
import ResidentSettings from "../pages/resident/Settings";
import Profile from "../pages/resident/Profile";

// Community Admin
import CommunityAdminDashboard from "../pages/communityAdmin/CommunityAdminDashboard";
import Residents from "../pages/communityAdmin/Residents";
import Tariffs from "../pages/communityAdmin/Tariffs";
import CommunitySettings from "../pages/communityAdmin/Settings";

// Super Admin
import SuperAdminDashboard from "../pages/superAdmin/SuperAdminDashboard";
import Users from "../pages/superAdmin/Users";
import Communities from "../pages/superAdmin/Communities";
import SuperSettings from "../pages/superAdmin/Settings";

const AppRoutes = () => {
        return (
            <Routes>

                    {/* Landing */}
                    <Route path="/" element={<Home />} />

                    {/* Authentication */}
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />

                    {/* ================= RESIDENT ================= */}

                    <Route
                        path="/resident/dashboard"
                        element={<ResidentDashboard />}
                    />

                    <Route
                        path="/resident/usage"
                        element={<Usage />}
                    />

                    <Route
                        path="/resident/bills"
                        element={<Bills />}
                    />

                    <Route
                        path="/resident/profile"
                        element={<Profile />}
                    />

                    <Route
                        path="/resident/settings"
                        element={<ResidentSettings />}
                    />

                    {/* ================= COMMUNITY ADMIN ================= */}

                    <Route
                        path="/community/dashboard"
                        element={<CommunityAdminDashboard />}
                    />

                    <Route
                        path="/community/residents"
                        element={<Residents />}
                    />

                    <Route
                        path="/community/tariffs"
                        element={<Tariffs />}
                    />

                    <Route
                        path="/community/settings"
                        element={<CommunitySettings />}
                    />

                    {/* ================= SUPER ADMIN ================= */}

                    <Route
                        path="/admin/dashboard"
                        element={<SuperAdminDashboard />}
                    />

                    <Route
                        path="/admin/users"
                        element={<Users />}
                    />

                    <Route
                        path="/admin/communities"
                        element={<Communities />}
                    />

                    <Route
                        path="/admin/settings"
                        element={<SuperSettings />}
                    />

                    {/* Fallback */}
                    <Route
                        path="*"
                        element={
                                <div className="flex items-center justify-center h-screen text-2xl font-bold">
                                        404 - Page Not Found
                                </div>
                        }
                    />

            </Routes>
        );
};

export default AppRoutes;