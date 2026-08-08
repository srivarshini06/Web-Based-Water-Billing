import React from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const residents = [
    {
        name: "Priya Nair",
        unit: "A-204",
        usage: "1,240 L",
        bill: "₹842",
        status: "Paid",
    },
    {
        name: "Arun Kumar",
        unit: "B-108",
        usage: "1,480 L",
        bill: "₹975",
        status: "Pending",
    },
    {
        name: "Sneha Iyer",
        unit: "A-311",
        usage: "980 L",
        bill: "₹690",
        status: "Paid",
    },
    {
        name: "Karthik R",
        unit: "C-402",
        usage: "1,320 L",
        bill: "₹910",
        status: "Paid",
    },
    {
        name: "Meera Das",
        unit: "B-215",
        usage: "1,410 L",
        bill: "₹880",
        status: "Pending",
    },
];

const monthlyUsage = [
    { month: "Feb", value: 55 },
    { month: "Mar", value: 68 },
    { month: "Apr", value: 62 },
    { month: "May", value: 78 },
    { month: "Jun", value: 72 },
    { month: "Jul", value: 65 },
];

export default function CommunityAdminDashboard() {
    return (
        <DashboardLayout
            role="communityAdmin"
            user={{
                name: "Rahul",
                email: "rahul@aquaflow.com",
            }}
        >
            {/* Header */}
            <div className="mb-8">
                <h1 className="text-3xl font-bold text-gray-900">
                    Community Dashboard
                </h1>

                <p className="text-gray-500 mt-2">
                    Monitor residents, water usage and billing activity.
                </p>
            </div>

            {/* Summary Cards */}
            <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-4 gap-6 mb-8">

                {/* Residents */}
                <div className="bg-white rounded-xl shadow p-6">
                    <p className="text-gray-500">
                        Total Residents
                    </p>

                    <h2 className="text-4xl font-bold mt-3">
                        1,284
                    </h2>

                    <p className="text-green-600 text-sm mt-2">
                        ▲ 2.6% from last month
                    </p>
                </div>

                {/* Usage */}
                <div className="bg-white rounded-xl shadow p-6">
                    <p className="text-gray-500">
                        Total Water Usage
                    </p>

                    <h2 className="text-4xl font-bold mt-3">
                        24,512 L
                    </h2>

                    <p className="text-green-600 text-sm mt-2">
                        ▼ 8.8% from last month
                    </p>
                </div>

                {/* Bills */}
                <div className="bg-white rounded-xl shadow p-6">
                    <p className="text-gray-500">
                        Pending Bills
                    </p>

                    <h2 className="text-4xl font-bold mt-3">
                        124
                    </h2>

                    <p className="text-red-500 text-sm mt-2">
                        18 overdue
                    </p>
                </div>

                {/* Collection */}
                <div className="bg-white rounded-xl shadow p-6">
                    <p className="text-gray-500">
                        Collection Rate
                    </p>

                    <h2 className="text-4xl font-bold mt-3">
                        94.2%
                    </h2>

                    <p className="text-green-600 text-sm mt-2">
                        ▲ 3.1% improvement
                    </p>
                </div>
            </div>

            {/* Charts + Quick Info */}
            <div className="grid lg:grid-cols-3 gap-6 mb-8">

                {/* Usage Chart */}
                <div className="lg:col-span-2 bg-white rounded-xl shadow p-6">

                    <div className="flex items-center justify-between mb-6">
                        <div>
                            <h2 className="text-xl font-semibold">
                                Community Water Usage
                            </h2>

                            <p className="text-sm text-gray-500 mt-1">
                                Monthly consumption
                            </p>
                        </div>

                        <span className="text-sm text-gray-500">
                            Litres
                        </span>
                    </div>

                    <div className="flex items-end justify-between h-64 px-4">

                        {monthlyUsage.map((item) => (
                            <div
                                key={item.month}
                                className="flex flex-col items-center"
                            >
                                <div
                                    className="w-10 bg-teal-500 rounded-t"
                                    style={{
                                        height: `${item.value * 3}px`,
                                    }}
                                />

                                <span className="mt-3 text-sm text-gray-500">
                                    {item.month}
                                </span>
                            </div>
                        ))}

                    </div>
                </div>

                {/* Community Information */}
                <div className="bg-white rounded-xl shadow p-6">

                    <h2 className="text-xl font-semibold mb-6">
                        Community Overview
                    </h2>

                    <div className="space-y-5">

                        <div>
                            <p className="text-gray-500 text-sm">
                                Community
                            </p>

                            <p className="font-semibold text-lg">
                                Palm Residency
                            </p>
                        </div>

                        <div>
                            <p className="text-gray-500 text-sm">
                                Total Units
                            </p>

                            <p className="font-semibold text-lg">
                                1,350
                            </p>
                        </div>

                        <div>
                            <p className="text-gray-500 text-sm">
                                Active Meters
                            </p>

                            <p className="font-semibold text-lg text-green-600">
                                1,284
                            </p>
                        </div>

                        <div>
                            <p className="text-gray-500 text-sm">
                                Current Tariff
                            </p>

                            <p className="font-semibold text-lg">
                                ₹5.50 / KL
                            </p>
                        </div>

                    </div>
                </div>
            </div>

            {/* Residents Table */}
            <div className="bg-white rounded-xl shadow">

                <div className="p-6 border-b flex items-center justify-between">

                    <div>
                        <h2 className="text-xl font-semibold">
                            Recent Residents
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Recent usage and billing information
                        </p>
                    </div>

                    <button
                        onClick={() =>
                            window.location.href = "/community/residents"
                        }
                        className="bg-teal-600 hover:bg-teal-700 text-white px-4 py-2 rounded-lg text-sm"
                    >
                        View All
                    </button>
                </div>

                <div className="overflow-x-auto">

                    <table className="w-full">

                        <thead className="bg-gray-50">

                        <tr>
                            <th className="text-left p-4 text-gray-600">
                                Resident
                            </th>

                            <th className="text-left p-4 text-gray-600">
                                Unit
                            </th>

                            <th className="text-left p-4 text-gray-600">
                                Usage
                            </th>

                            <th className="text-left p-4 text-gray-600">
                                Bill
                            </th>

                            <th className="text-left p-4 text-gray-600">
                                Status
                            </th>
                        </tr>

                        </thead>

                        <tbody>

                        {residents.map((resident) => (
                            <tr
                                key={resident.unit}
                                className="border-t hover:bg-gray-50"
                            >

                                <td className="p-4 font-medium">
                                    {resident.name}
                                </td>

                                <td className="p-4">
                                    {resident.unit}
                                </td>

                                <td className="p-4">
                                    {resident.usage}
                                </td>

                                <td className="p-4">
                                    {resident.bill}
                                </td>

                                <td className="p-4">

                                    <span
                                        className={`px-3 py-1 rounded-full text-sm ${
                                            resident.status === "Paid"
                                                ? "bg-green-100 text-green-700"
                                                : "bg-yellow-100 text-yellow-700"
                                        }`}
                                    >
                                        {resident.status}
                                    </span>

                                </td>

                            </tr>
                        ))}

                        </tbody>

                    </table>

                </div>
            </div>

        </DashboardLayout>
    );
}