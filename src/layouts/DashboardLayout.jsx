import React from "react";
import { Link, useLocation } from "react-router-dom";
import {
    LayoutDashboard,
    Users,
    Droplets,
    Receipt,
    Settings,
    LogOut,
} from "lucide-react";

const DashboardLayout = ({ role, user, children }) => {
    const location = useLocation();

    // Resident Menu
    const residentMenu = [
        {
            name: "Dashboard",
            icon: LayoutDashboard,
            path: "/resident/dashboard",
        },
        {
            name: "Water Usage",
            icon: Droplets,
            path: "/resident/usage",
        },
        {
            name: "Bills",
            icon: Receipt,
            path: "/resident/bills",
        },
        {
            name: "Settings",
            icon: Settings,
            path: "/resident/settings",
        },
    ];

    // Community Admin Menu
    const communityMenu = [
        {
            name: "Dashboard",
            icon: LayoutDashboard,
            path: "/community/dashboard",
        },
        {
            name: "Residents",
            icon: Users,
            path: "/community/residents",
        },
        {
            name: "Tariffs",
            icon: Receipt,
            path: "/community/tariffs",
        },
        {
            name: "Settings",
            icon: Settings,
            path: "/community/settings",
        },
    ];

    // Super Admin Menu
    const adminMenu = [
        {
            name: "Dashboard",
            icon: LayoutDashboard,
            path: "/admin/dashboard",
        },
        {
            name: "Users",
            icon: Users,
            path: "/admin/users",
        },
        {
            name: "Communities",
            icon: Droplets,
            path: "/admin/communities",
        },
        {
            name: "Settings",
            icon: Settings,
            path: "/admin/settings",
        },
    ];

    const menu =
        role === "resident"
            ? residentMenu
            : role === "communityAdmin"
                ? communityMenu
                : adminMenu;

    return (
        <div className="min-h-screen bg-gray-100 flex">
            {/* Sidebar */}
            <aside className="w-64 bg-white shadow-lg">
                <div className="p-6 border-b">
                    <h1 className="text-2xl font-bold text-teal-600">
                        AquaFlow
                    </h1>

                    <p className="text-sm text-gray-500 capitalize">
                        {role}
                    </p>
                </div>

                <nav className="mt-4">
                    {menu.map((item) => {
                        const Icon = item.icon;

                        return (
                            <Link
                                key={item.path}
                                to={item.path}
                                className={`flex items-center gap-3 px-6 py-3 transition ${
                                    location.pathname === item.path
                                        ? "bg-teal-100 text-teal-700 font-semibold"
                                        : "text-gray-700 hover:bg-gray-100"
                                }`}
                            >
                                <Icon size={20} />
                                <span>{item.name}</span>
                            </Link>
                        );
                    })}
                </nav>
            </aside>

            {/* Main Content */}
            <div className="flex-1">
                <header className="bg-white shadow px-8 py-4 flex justify-between items-center">
                    <div>
                        <h2 className="text-xl font-bold">
                            Welcome, {user?.name || "User"}
                        </h2>

                        <p className="text-sm text-gray-500">
                            {user?.email || ""}
                        </p>
                    </div>

                    <button
                        onClick={() => {
                            localStorage.clear();
                            window.location.href = "/login";
                        }}
                        className="flex items-center gap-2 bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-lg"
                    >
                        <LogOut size={18} />
                        Logout
                    </button>
                </header>

                <main className="p-8">{children}</main>
            </div>
        </div>
    );
};

export default DashboardLayout;