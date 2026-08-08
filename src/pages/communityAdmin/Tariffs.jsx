import React from "react";
import DashboardLayout from "../../layouts/DashboardLayout";
import MetricCard from "../../components/dashboard/MetricCard";
import DataTable from "../../components/dashboard/DataTable";

const tariffs = [
    {
        id: 1,
        type: "Residential",
        rate: "₹8.50 / KL",
        effective: "01 Jan 2026",
        status: "Active",
    },
    {
        id: 2,
        type: "Commercial",
        rate: "₹12.00 / KL",
        effective: "01 Jan 2026",
        status: "Active",
    },
    {
        id: 3,
        type: "Industrial",
        rate: "₹15.50 / KL",
        effective: "01 Jan 2026",
        status: "Inactive",
    },
];

const StatusBadge = ({ status }) => (
    <span
        className={`px-3 py-1 rounded-full text-xs font-medium ${
            status === "Active"
                ? "bg-green-100 text-green-700"
                : "bg-red-100 text-red-700"
        }`}
    >
    {status}
  </span>
);

const columns = [
    { key: "type", label: "Tariff Type" },
    { key: "rate", label: "Rate" },
    { key: "effective", label: "Effective From" },
    {
        key: "status",
        label: "Status",
        render: (row) => <StatusBadge status={row.status} />,
    },
];

const Tariffs = () => {
    return (
        <DashboardLayout
            role="communityAdmin"
            user={{
                name: "Rahul Menon",
                email: "rahul@aquaflow.com",
            }}
        >
            <div className="space-y-6">
                <div>
                    <h1 className="text-2xl font-bold">Tariff Management</h1>
                    <p className="text-gray-500">
                        Manage water billing tariffs for your community.
                    </p>
                </div>

                <div className="grid md:grid-cols-3 gap-5">
                    <MetricCard label="Active Tariffs" value="2" change={0} />
                    <MetricCard label="Average Rate" value="₹10.25/KL" change={2.5} />
                    <MetricCard label="Last Updated" value="Jan 2026" />
                </div>

                <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-6">
                    <div className="flex justify-between items-center mb-5">
                        <h2 className="text-lg font-semibold">Tariff List</h2>

                        <button className="bg-teal-600 hover:bg-teal-700 text-white px-4 py-2 rounded-lg">
                            + Add Tariff
                        </button>
                    </div>

                    <DataTable
                        columns={columns}
                        rows={tariffs}
                        pageSize={5}
                    />
                </div>
            </div>
        </DashboardLayout>
    );
};

export default Tariffs;