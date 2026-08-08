import React from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const bills = [
    {
        month: "July 2026",
        amount: "₹842",
        status: "Paid",
    },
    {
        month: "June 2026",
        amount: "₹790",
        status: "Paid",
    },
    {
        month: "May 2026",
        amount: "₹915",
        status: "Paid",
    },
    {
        month: "April 2026",
        amount: "₹870",
        status: "Pending",
    },
];

const notifications = [
    "Your July bill has been paid successfully.",
    "Water usage increased by 6% this month.",
    "Next meter reading is on 5 August.",
    "New conservation tips are available.",
];

const ResidentDashboard = () => {
    return (
        <DashboardLayout
            role="resident"
            user={{
                name: "Priya Nair",
                email: "priya@aquaflow.com",
            }}
        >
            <div className="space-y-8">

                <div>
                    <h1 className="text-4xl font-bold text-gray-800">
                        Resident Dashboard
                    </h1>
                    <p className="text-gray-500 mt-2">
                        Welcome back! Here's an overview of your water usage and billing.
                    </p>
                </div>

                {/* Stats Cards */}

                <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-4 gap-6">

                    <div className="bg-white rounded-xl shadow-md p-6">
                        <p className="text-gray-500">Water Usage</p>
                        <h2 className="text-4xl font-bold mt-3 text-teal-600">
                            14,200 L
                        </h2>
                        <p className="text-green-600 mt-2 text-sm">
                            ▲ 8% lower than last month
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow-md p-6">
                        <p className="text-gray-500">
                            Current Bill
                        </p>

                        <h2 className="text-4xl font-bold mt-3">
                            ₹842
                        </h2>

                        <p className="text-gray-500 mt-2">
                            Due on 5 August
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow-md p-6">
                        <p className="text-gray-500">
                            Meter Status
                        </p>

                        <h2 className="text-3xl font-bold text-green-600 mt-3">
                            Active
                        </h2>

                        <p className="text-gray-500 mt-2">
                            Last synced today
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow-md p-6">
                        <p className="text-gray-500">
                            Payment Status
                        </p>

                        <h2 className="text-3xl font-bold text-green-600 mt-3">
                            Paid
                        </h2>

                        <p className="text-gray-500 mt-2">
                            No pending dues
                        </p>
                    </div>

                </div>

                {/* Charts + Bill */}

                <div className="grid lg:grid-cols-3 gap-6">

                    {/* Water Usage Chart */}

                    <div className="lg:col-span-2 bg-white rounded-xl shadow-md p-6">

                        <h2 className="text-2xl font-semibold mb-6">
                            Weekly Water Usage
                        </h2>

                        <div className="flex items-end justify-between h-72">

                            {[55, 80, 62, 92, 74, 65, 88].map((value, index) => (

                                <div
                                    key={index}
                                    className="flex flex-col items-center"
                                >
                                    <div
                                        className="w-10 bg-teal-500 rounded-t-lg transition-all hover:bg-teal-600"
                                        style={{
                                            height: `${value * 2}px`,
                                        }}
                                    />

                                    <span className="mt-3 text-sm text-gray-500">
                                        {["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"][index]}
                                    </span>

                                </div>

                            ))}

                        </div>

                    </div>

                    {/* Current Bill */}

                    <div className="bg-white rounded-xl shadow-md p-6">

                        <h2 className="text-2xl font-semibold">
                            Current Bill
                        </h2>

                        <h1 className="text-5xl font-bold mt-6 text-teal-600">
                            ₹842
                        </h1>

                        <p className="text-gray-500 mt-4">
                            Billing Period
                        </p>

                        <p className="font-semibold">
                            July 2026
                        </p>

                        <p className="text-gray-500 mt-4">
                            Due Date
                        </p>

                        <p className="font-semibold">
                            5 August 2026
                        </p>

                        <button
                            className="w-full bg-teal-600 hover:bg-teal-700 text-white py-3 rounded-lg mt-8 transition"
                        >
                            Pay Now
                        </button>

                        <button
                            className="w-full border border-teal-600 text-teal-600 py-3 rounded-lg mt-3 hover:bg-teal-50 transition"
                        >
                            Download Bill
                        </button>

                    </div>

                </div>

                {/* Bottom Section */}

                <div className="grid lg:grid-cols-2 gap-6">

                    {/* Bill History */}

                    <div className="bg-white rounded-xl shadow-md overflow-hidden">

                        <div className="p-6 border-b">

                            <h2 className="text-2xl font-semibold">
                                Bill History
                            </h2>

                        </div>

                        <table className="w-full">

                            <thead className="bg-gray-50">

                            <tr>

                                <th className="text-left p-4">Month</th>

                                <th className="text-left">Amount</th>

                                <th className="text-left">Status</th>

                            </tr>

                            </thead>

                            <tbody>

                            {bills.map((bill) => (

                                <tr
                                    key={bill.month}
                                    className="border-t hover:bg-gray-50"
                                >

                                    <td className="p-4">
                                        {bill.month}
                                    </td>

                                    <td>
                                        {bill.amount}
                                    </td>

                                    <td>

                                        <span
                                            className={`px-3 py-1 rounded-full text-sm font-medium ${
                                                bill.status === "Paid"
                                                    ? "bg-green-100 text-green-700"
                                                    : "bg-red-100 text-red-700"
                                            }`}
                                        >
                                            {bill.status}
                                        </span>

                                    </td>

                                </tr>

                            ))}

                            </tbody>

                        </table>

                    </div>

                    {/* Notifications */}

                    <div className="bg-white rounded-xl shadow-md p-6">

                        <h2 className="text-2xl font-semibold mb-6">
                            Notifications
                        </h2>

                        <div className="space-y-4">

                            {notifications.map((note, index) => (

                                <div
                                    key={index}
                                    className="border rounded-lg p-4 hover:bg-gray-50"
                                >

                                    <p>{note}</p>

                                </div>

                            ))}

                        </div>

                    </div>

                </div>

            </div>

        </DashboardLayout>
    );
};

export default ResidentDashboard;