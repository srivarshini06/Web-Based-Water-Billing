import React from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const residents = [
    {
        id: 1,
        name: "Priya Nair",
        email: "priya@aquaflow.com",
        unit: "A-204",
        meter: "MTR-88213",
        usage: "14,200 L",
        bill: "₹842",
        status: "Active",
    },
    {
        id: 2,
        name: "Arun Kumar",
        email: "arun@aquaflow.com",
        unit: "B-108",
        meter: "MTR-88214",
        usage: "16,400 L",
        bill: "₹975",
        status: "Active",
    },
    {
        id: 3,
        name: "Sneha Iyer",
        email: "sneha@aquaflow.com",
        unit: "A-311",
        meter: "MTR-88215",
        usage: "11,800 L",
        bill: "₹690",
        status: "Active",
    },
    {
        id: 4,
        name: "Karthik R",
        email: "karthik@aquaflow.com",
        unit: "C-402",
        meter: "MTR-88216",
        usage: "13,900 L",
        bill: "₹910",
        status: "Active",
    },
    {
        id: 5,
        name: "Meera Das",
        email: "meera@aquaflow.com",
        unit: "B-215",
        meter: "MTR-88217",
        usage: "15,100 L",
        bill: "₹880",
        status: "Pending",
    },
];

export default function Residents() {
    return (
        <DashboardLayout
            role="communityAdmin"
            user={{
                name: "Rahul",
                email: "rahul@aquaflow.com",
            }}
        >
            <div className="space-y-8">

                {/* Header */}
                <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4">
                    <div>
                        <h1 className="text-3xl font-bold">
                            Residents
                        </h1>

                        <p className="text-gray-500 mt-2">
                            Manage residents and monitor their water usage.
                        </p>
                    </div>

                    <button
                        className="bg-teal-600 hover:bg-teal-700 text-white px-5 py-3 rounded-lg"
                    >
                        + Add Resident
                    </button>
                </div>

                {/* Summary Cards */}
                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">

                    <div className="bg-white rounded-xl shadow p-6">
                        <p className="text-gray-500">
                            Total Residents
                        </p>

                        <h2 className="text-3xl font-bold mt-2">
                            1,284
                        </h2>
                    </div>

                    <div className="bg-white rounded-xl shadow p-6">
                        <p className="text-gray-500">
                            Active Residents
                        </p>

                        <h2 className="text-3xl font-bold text-green-600 mt-2">
                            1,240
                        </h2>
                    </div>

                    <div className="bg-white rounded-xl shadow p-6">
                        <p className="text-gray-500">
                            Pending Accounts
                        </p>

                        <h2 className="text-3xl font-bold text-orange-500 mt-2">
                            44
                        </h2>
                    </div>

                </div>

                {/* Search / Filter */}
                <div className="bg-white rounded-xl shadow p-5">

                    <div className="flex flex-col md:flex-row gap-4">

                        <input
                            type="text"
                            placeholder="Search residents..."
                            className="border border-gray-300 rounded-lg px-4 py-3 flex-1 outline-none focus:ring-2 focus:ring-teal-500"
                        />

                        <select
                            className="border border-gray-300 rounded-lg px-4 py-3"
                        >
                            <option>All Status</option>
                            <option>Active</option>
                            <option>Pending</option>
                        </select>

                    </div>

                </div>

                {/* Residents Table */}
                <div className="bg-white rounded-xl shadow overflow-hidden">

                    <div className="p-6 border-b">
                        <h2 className="text-xl font-semibold">
                            Resident List
                        </h2>
                    </div>

                    <div className="overflow-x-auto">

                        <table className="w-full">

                            <thead className="bg-gray-50">

                            <tr className="text-left">

                                <th className="p-4">
                                    Resident
                                </th>

                                <th className="p-4">
                                    Unit
                                </th>

                                <th className="p-4">
                                    Meter ID
                                </th>

                                <th className="p-4">
                                    Usage
                                </th>

                                <th className="p-4">
                                    Current Bill
                                </th>

                                <th className="p-4">
                                    Status
                                </th>

                                <th className="p-4">
                                    Action
                                </th>

                            </tr>

                            </thead>

                            <tbody>

                            {residents.map((resident) => (

                                <tr
                                    key={resident.id}
                                    className="border-t hover:bg-gray-50"
                                >

                                    <td className="p-4">

                                        <div>
                                            <p className="font-semibold">
                                                {resident.name}
                                            </p>

                                            <p className="text-sm text-gray-500">
                                                {resident.email}
                                            </p>
                                        </div>

                                    </td>

                                    <td className="p-4">
                                        {resident.unit}
                                    </td>

                                    <td className="p-4 text-sm">
                                        {resident.meter}
                                    </td>

                                    <td className="p-4">
                                        {resident.usage}
                                    </td>

                                    <td className="p-4 font-medium">
                                        {resident.bill}
                                    </td>

                                    <td className="p-4">

                                        <span
                                            className={`px-3 py-1 rounded-full text-sm ${
                                                resident.status === "Active"
                                                    ? "bg-green-100 text-green-700"
                                                    : "bg-orange-100 text-orange-700"
                                            }`}
                                        >
                                            {resident.status}
                                        </span>

                                    </td>

                                    <td className="p-4">

                                        <button
                                            className="text-teal-600 hover:text-teal-800 font-medium"
                                        >
                                            View
                                        </button>

                                    </td>

                                </tr>

                            ))}

                            </tbody>

                        </table>

                    </div>

                </div>

            </div>
        </DashboardLayout>
    );
}