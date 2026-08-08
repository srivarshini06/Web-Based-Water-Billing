import React, { useState } from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const initialUsers = [
    {
        id: 1,
        name: "Sarah Jenkins",
        email: "sarah.jenkins@aquaflow.com",
        role: "Community Admin",
        status: "Approved",
    },
    {
        id: 2,
        name: "Marcus Chen",
        email: "m.chen@waterfront-res.net",
        role: "Resident",
        status: "Approved",
    },
    {
        id: 3,
        name: "Elaine Miller",
        email: "elaine@aquaflow-hq.com",
        role: "Super Admin",
        status: "Approved",
    },
    {
        id: 4,
        name: "David Ortiz",
        email: "dortiz1985@gmail.com",
        role: "Resident",
        status: "Suspended",
    },
    {
        id: 5,
        name: "Jessica Vane",
        email: "j.vane@oakridge-heights.com",
        role: "Community Admin",
        status: "Approved",
    },
];

export default function Users() {
    const [users, setUsers] = useState(initialUsers);
    const [search, setSearch] = useState("");
    const [roleFilter, setRoleFilter] = useState("All Roles");
    const [statusFilter, setStatusFilter] = useState("All Statuses");

    const filteredUsers = users.filter((user) => {
        const matchesSearch =
            user.name.toLowerCase().includes(search.toLowerCase()) ||
            user.email.toLowerCase().includes(search.toLowerCase());

        const matchesRole =
            roleFilter === "All Roles" || user.role === roleFilter;

        const matchesStatus =
            statusFilter === "All Statuses" ||
            user.status === statusFilter;

        return matchesSearch && matchesRole && matchesStatus;
    });

    const toggleUserStatus = (id) => {
        setUsers((currentUsers) =>
            currentUsers.map((user) =>
                user.id === id
                    ? {
                        ...user,
                        status:
                            user.status === "Suspended"
                                ? "Approved"
                                : "Suspended",
                    }
                    : user
            )
        );
    };

    const approvedUsers = users.filter(
        (user) => user.status === "Approved"
    ).length;

    const suspendedUsers = users.filter(
        (user) => user.status === "Suspended"
    ).length;

    const communities = users.filter(
        (user) => user.role === "Community Admin"
    ).length;

    return (
        <DashboardLayout
            role="superAdmin"
            user={{
                name: "System Admin",
                email: "admin@aquaflow.com",
            }}
        >
            <div className="space-y-6">

                {/* Page Header */}
                <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4">
                    <div>
                        <h1 className="text-3xl font-bold text-gray-900">
                            User Management
                        </h1>

                        <p className="text-gray-500 mt-1">
                            Review and manage all AquaFlow users.
                        </p>
                    </div>

                    <div className="flex gap-3">
                        <button
                            className="px-4 py-2 bg-white border border-gray-200 rounded-lg text-sm font-medium hover:bg-gray-50"
                            onClick={() => {
                                const csv = users
                                    .map(
                                        (user) =>
                                            `${user.name},${user.email},${user.role},${user.status}`
                                    )
                                    .join("\n");

                                const blob = new Blob(
                                    [
                                        `Name,Email,Role,Status\n${csv}`,
                                    ],
                                    { type: "text/csv" }
                                );

                                const url =
                                    window.URL.createObjectURL(blob);

                                const link =
                                    document.createElement("a");

                                link.href = url;
                                link.download = "aquaflow-users.csv";
                                link.click();

                                window.URL.revokeObjectURL(url);
                            }}
                        >
                            Export List
                        </button>

                        <button
                            className="px-4 py-2 bg-teal-600 text-white rounded-lg text-sm font-medium hover:bg-teal-700"
                            onClick={() => {
                                alert(
                                    "Add User functionality can be connected to your backend later."
                                );
                            }}
                        >
                            + Create User
                        </button>
                    </div>
                </div>

                {/* Statistics */}
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-5">

                    <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-5">
                        <p className="text-sm text-gray-500">
                            Total Users
                        </p>

                        <p className="text-3xl font-bold text-gray-900 mt-2">
                            {users.length}
                        </p>

                        <p className="text-xs text-teal-600 mt-2">
                            ↑ 4.2% this month
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-5">
                        <p className="text-sm text-gray-500">
                            Approved Users
                        </p>

                        <p className="text-3xl font-bold text-gray-900 mt-2">
                            {approvedUsers}
                        </p>

                        <p className="text-xs text-green-600 mt-2">
                            Active accounts
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-5">
                        <p className="text-sm text-gray-500">
                            Suspended Users
                        </p>

                        <p className="text-3xl font-bold text-gray-900 mt-2">
                            {suspendedUsers}
                        </p>

                        <p className="text-xs text-red-500 mt-2">
                            Requires attention
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-5">
                        <p className="text-sm text-gray-500">
                            Community Admins
                        </p>

                        <p className="text-3xl font-bold text-gray-900 mt-2">
                            {communities}
                        </p>

                        <p className="text-xs text-gray-500 mt-2">
                            Managing communities
                        </p>
                    </div>

                </div>

                {/* Filters */}
                <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-4">

                    <div className="flex flex-col lg:flex-row gap-4">

                        <div className="flex-1">
                            <input
                                type="text"
                                placeholder="Search users or email..."
                                value={search}
                                onChange={(e) =>
                                    setSearch(e.target.value)
                                }
                                className="w-full border border-gray-200 rounded-lg px-4 py-2.5 text-sm outline-none focus:ring-2 focus:ring-teal-500"
                            />
                        </div>

                        <select
                            value={roleFilter}
                            onChange={(e) =>
                                setRoleFilter(e.target.value)
                            }
                            className="border border-gray-200 rounded-lg px-4 py-2.5 text-sm outline-none"
                        >
                            <option>All Roles</option>
                            <option>Resident</option>
                            <option>Community Admin</option>
                            <option>Super Admin</option>
                        </select>

                        <select
                            value={statusFilter}
                            onChange={(e) =>
                                setStatusFilter(e.target.value)
                            }
                            className="border border-gray-200 rounded-lg px-4 py-2.5 text-sm outline-none"
                        >
                            <option>All Statuses</option>
                            <option>Approved</option>
                            <option>Suspended</option>
                        </select>

                    </div>

                </div>

                {/* Users Table */}
                <div className="bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden">

                    <div className="px-6 py-4 border-b border-gray-100">
                        <h2 className="font-semibold text-gray-900">
                            All Users
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Showing {filteredUsers.length} of{" "}
                            {users.length} users
                        </p>
                    </div>

                    <div className="overflow-x-auto">

                        <table className="w-full">

                            <thead className="bg-gray-50">

                            <tr className="text-left text-xs uppercase tracking-wide text-gray-500">

                                <th className="px-6 py-4">
                                    Name
                                </th>

                                <th className="px-6 py-4">
                                    Email
                                </th>

                                <th className="px-6 py-4">
                                    Role
                                </th>

                                <th className="px-6 py-4">
                                    Status
                                </th>

                                <th className="px-6 py-4 text-right">
                                    Actions
                                </th>

                            </tr>

                            </thead>

                            <tbody>

                            {filteredUsers.map((user) => (

                                <tr
                                    key={user.id}
                                    className="border-t border-gray-100 hover:bg-gray-50"
                                >

                                    {/* Name */}
                                    <td className="px-6 py-4">

                                        <div className="flex items-center gap-3">

                                            <div className="w-9 h-9 rounded-full bg-teal-100 text-teal-700 flex items-center justify-center font-semibold text-sm">
                                                {user.name
                                                    .charAt(0)
                                                    .toUpperCase()}
                                            </div>

                                            <span className="font-medium text-gray-900">
                                                    {user.name}
                                                </span>

                                        </div>

                                    </td>

                                    {/* Email */}
                                    <td className="px-6 py-4 text-sm text-gray-500">
                                        {user.email}
                                    </td>

                                    {/* Role */}
                                    <td className="px-6 py-4">

                                            <span
                                                className={`px-2.5 py-1 rounded-md text-xs font-medium ${
                                                    user.role ===
                                                    "Super Admin"
                                                        ? "bg-purple-100 text-purple-700"
                                                        : user.role ===
                                                        "Community Admin"
                                                            ? "bg-blue-100 text-blue-700"
                                                            : "bg-gray-100 text-gray-700"
                                                }`}
                                            >
                                                {user.role}
                                            </span>

                                    </td>

                                    {/* Status */}
                                    <td className="px-6 py-4">

                                            <span
                                                className={`inline-flex items-center gap-1.5 px-2.5 py-1 rounded-full text-xs font-medium ${
                                                    user.status ===
                                                    "Approved"
                                                        ? "bg-green-100 text-green-700"
                                                        : "bg-red-100 text-red-700"
                                                }`}
                                            >
                                                <span
                                                    className={`w-1.5 h-1.5 rounded-full ${
                                                        user.status ===
                                                        "Approved"
                                                            ? "bg-green-500"
                                                            : "bg-red-500"
                                                    }`}
                                                />

                                                {user.status}
                                            </span>

                                    </td>

                                    {/* Actions */}
                                    <td className="px-6 py-4 text-right">

                                        <button
                                            onClick={() =>
                                                toggleUserStatus(
                                                    user.id
                                                )
                                            }
                                            className={`px-3 py-1.5 rounded-lg text-xs font-medium ${
                                                user.status ===
                                                "Suspended"
                                                    ? "bg-teal-600 text-white hover:bg-teal-700"
                                                    : "border border-red-200 text-red-600 hover:bg-red-50"
                                            }`}
                                        >
                                            {user.status ===
                                            "Suspended"
                                                ? "Reactivate"
                                                : "Suspend"}
                                        </button>

                                    </td>

                                </tr>

                            ))}

                            </tbody>

                        </table>

                    </div>

                    {/* Empty State */}
                    {filteredUsers.length === 0 && (
                        <div className="text-center py-12 text-gray-500">
                            No users found matching your filters.
                        </div>
                    )}

                    {/* Footer */}
                    <div className="px-6 py-4 border-t border-gray-100 flex items-center justify-between text-sm text-gray-500">

                        <span>
                            Showing {filteredUsers.length} users
                        </span>

                        <div className="flex gap-2">

                            <button
                                disabled
                                className="px-3 py-1.5 border rounded-lg text-gray-300"
                            >
                                Previous
                            </button>

                            <button className="px-3 py-1.5 bg-teal-600 text-white rounded-lg">
                                1
                            </button>

                            <button className="px-3 py-1.5 border rounded-lg hover:bg-gray-50">
                                2
                            </button>

                            <button className="px-3 py-1.5 border rounded-lg hover:bg-gray-50">
                                Next
                            </button>

                        </div>

                    </div>

                </div>

            </div>
        </DashboardLayout>
    );
}