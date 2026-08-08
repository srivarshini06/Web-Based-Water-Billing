import DashboardLayout from "../../layouts/DashboardLayout";

const users = [
    {
        name: "Sarah Jenkins",
        email: "sarah.jenkins@gmail.com",
        role: "Community Admin",
        status: "Approved",
    },
    {
        name: "Marcus Chen",
        email: "m.chen@waterfirst.net",
        role: "Resident",
        status: "Approved",
    },
    {
        name: "Elaine Miller",
        email: "elaine@aquaflow-hq.com",
        role: "Super Admin",
        status: "Approved",
    },
    {
        name: "David Ortiz",
        email: "dortiz88@gmail.com",
        role: "Resident",
        status: "Suspended",
    },
    {
        name: "Jessica Vane",
        email: "jvane@oakridge-heights.com",
        role: "Community Admin",
        status: "Approved",
    },
];

export default function SuperAdminDashboard() {
    return (
        <DashboardLayout
            role="superAdmin"
            user={{
                name: "System Admin",
                email: "admin@aquaflow.com",
            }}
        >
            {/* Header */}
            <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4 mb-8">

                <div>
                    <h1 className="text-3xl font-bold text-gray-900">
                        User Management
                    </h1>

                    <p className="text-gray-500 mt-1">
                        Review and manage platform access for all AquaFlow users.
                    </p>
                </div>

                <div className="flex gap-3">
                    <button
                        className="px-4 py-2 border border-gray-300 rounded-lg
                        text-sm font-medium text-gray-700 hover:bg-gray-50"
                    >
                        ↓ Export List
                    </button>

                    <button
                        className="px-4 py-2 bg-teal-600 text-white rounded-lg
                        text-sm font-medium hover:bg-teal-700"
                    >
                        + Create User
                    </button>
                </div>

            </div>

            {/* Statistics */}
            <div className="grid grid-cols-1 md:grid-cols-3 gap-5 mb-8">

                {/* Total Users */}
                <div className="bg-white rounded-xl border border-gray-200 shadow-sm p-5">

                    <div className="flex items-start justify-between">

                        <div>
                            <p className="text-sm text-gray-500">
                                Total Users
                            </p>

                            <h2 className="text-3xl font-bold text-gray-900 mt-2">
                                12,842
                            </h2>
                        </div>

                        <div className="w-10 h-10 rounded-lg bg-gray-100 flex items-center justify-center">
                            👤
                        </div>

                    </div>

                    <p className="text-xs text-green-600 mt-4 font-medium">
                        ↑ 4.2% from last month
                    </p>

                </div>

                {/* Pending Approvals */}
                <div className="bg-white rounded-xl border border-gray-200 shadow-sm p-5">

                    <div className="flex items-start justify-between">

                        <div>
                            <p className="text-sm text-gray-500">
                                Pending Approvals
                            </p>

                            <h2 className="text-3xl font-bold text-gray-900 mt-2">
                                142
                            </h2>
                        </div>

                        <div className="w-10 h-10 rounded-lg bg-red-50 flex items-center justify-center">
                            ⚠️
                        </div>

                    </div>

                    <p className="text-xs text-red-500 mt-4 font-medium">
                        High Priority
                    </p>

                </div>

                {/* Active Communities */}
                <div className="bg-white rounded-xl border border-gray-200 shadow-sm p-5">

                    <div className="flex items-start justify-between">

                        <div>
                            <p className="text-sm text-gray-500">
                                Active Communities
                            </p>

                            <h2 className="text-3xl font-bold text-gray-900 mt-2">
                                84
                            </h2>
                        </div>

                        <div className="w-10 h-10 rounded-lg bg-teal-50 flex items-center justify-center">
                            🏢
                        </div>

                    </div>

                    <p className="text-xs text-gray-500 mt-4">
                        Across all regions
                    </p>

                </div>

            </div>

            {/* Filters */}
            <div className="bg-white rounded-xl border border-gray-200 shadow-sm">

                <div className="p-5 border-b border-gray-200">

                    <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4">

                        <div className="flex gap-3">

                            <select
                                className="border border-gray-300 rounded-lg px-3 py-2
                                text-sm text-gray-600 outline-none"
                            >
                                <option>All Roles</option>
                                <option>Resident</option>
                                <option>Community Admin</option>
                                <option>Super Admin</option>
                            </select>

                            <select
                                className="border border-gray-300 rounded-lg px-3 py-2
                                text-sm text-gray-600 outline-none"
                            >
                                <option>All Statuses</option>
                                <option>Approved</option>
                                <option>Pending</option>
                                <option>Suspended</option>
                            </select>

                        </div>

                        <p className="text-xs text-gray-500">
                            Showing 1–5 of 12,842 users
                        </p>

                    </div>

                </div>

                {/* User Table */}
                <div className="overflow-x-auto">

                    <table className="w-full">

                        <thead className="bg-gray-50">

                        <tr className="text-left">

                            <th className="px-5 py-4 text-xs font-semibold text-gray-500 uppercase">
                                Name
                            </th>

                            <th className="px-5 py-4 text-xs font-semibold text-gray-500 uppercase">
                                Email
                            </th>

                            <th className="px-5 py-4 text-xs font-semibold text-gray-500 uppercase">
                                Role
                            </th>

                            <th className="px-5 py-4 text-xs font-semibold text-gray-500 uppercase">
                                Status
                            </th>

                            <th className="px-5 py-4 text-xs font-semibold text-gray-500 uppercase">
                                Actions
                            </th>

                        </tr>

                        </thead>

                        <tbody>

                        {users.map((user) => (

                            <tr
                                key={user.email}
                                className="border-t border-gray-100 hover:bg-gray-50"
                            >

                                {/* Name */}
                                <td className="px-5 py-4">

                                    <div className="flex items-center gap-3">

                                        <div className="w-9 h-9 rounded-full bg-teal-100 text-teal-700
                                        flex items-center justify-center text-sm font-semibold">
                                            {user.name.charAt(0)}
                                        </div>

                                        <span className="font-medium text-gray-800">
                                            {user.name}
                                        </span>

                                    </div>

                                </td>

                                {/* Email */}
                                <td className="px-5 py-4 text-sm text-gray-500">
                                    {user.email}
                                </td>

                                {/* Role */}
                                <td className="px-5 py-4">

                                    <span
                                        className={`px-2.5 py-1 rounded-md text-xs font-medium
                                        ${
                                            user.role === "Super Admin"
                                                ? "bg-purple-100 text-purple-700"
                                                : user.role === "Community Admin"
                                                    ? "bg-blue-100 text-blue-700"
                                                    : "bg-gray-100 text-gray-700"
                                        }`}
                                    >
                                        {user.role}
                                    </span>

                                </td>

                                {/* Status */}
                                <td className="px-5 py-4">

                                    <span
                                        className={`flex items-center gap-1.5 text-sm font-medium
                                        ${
                                            user.status === "Approved"
                                                ? "text-green-600"
                                                : "text-red-500"
                                        }`}
                                    >

                                        <span
                                            className={`w-2 h-2 rounded-full
                                            ${
                                                user.status === "Approved"
                                                    ? "bg-green-500"
                                                    : "bg-red-500"
                                            }`}
                                        />

                                        {user.status}

                                    </span>

                                </td>

                                {/* Actions */}
                                <td className="px-5 py-4">

                                    {user.status === "Suspended" ? (

                                        <button
                                            className="text-xs border border-gray-300
                                            px-3 py-1.5 rounded-md hover:bg-gray-100"
                                        >
                                            Reactivate
                                        </button>

                                    ) : (

                                        <div className="flex gap-2">

                                            <button
                                                className="text-xs bg-green-600 text-white
                                                px-3 py-1.5 rounded-md hover:bg-green-700"
                                            >
                                                Approve
                                            </button>

                                            <button
                                                className="text-xs border border-red-200
                                                text-red-500 px-3 py-1.5 rounded-md
                                                hover:bg-red-50"
                                            >
                                                Reject
                                            </button>

                                        </div>

                                    )}

                                </td>

                            </tr>

                        ))}

                        </tbody>

                    </table>

                </div>

                {/* Pagination */}
                <div className="px-5 py-4 border-t border-gray-200 flex items-center justify-between">

                    <button
                        className="text-sm text-gray-400"
                    >
                        ← Previous
                    </button>

                    <div className="flex items-center gap-2">

                        <button
                            className="w-8 h-8 rounded-md bg-teal-600 text-white text-sm"
                        >
                            1
                        </button>

                        <button
                            className="w-8 h-8 rounded-md text-sm text-gray-600 hover:bg-gray-100"
                        >
                            2
                        </button>

                        <button
                            className="w-8 h-8 rounded-md text-sm text-gray-600 hover:bg-gray-100"
                        >
                            3
                        </button>

                        <span className="text-gray-400">
                            ...
                        </span>

                        <button
                            className="w-8 h-8 rounded-md text-sm text-gray-600 hover:bg-gray-100"
                        >
                            1284
                        </button>

                    </div>

                    <button
                        className="text-sm text-gray-600 hover:text-gray-900"
                    >
                        Next →
                    </button>

                </div>

            </div>

        </DashboardLayout>
    );
}