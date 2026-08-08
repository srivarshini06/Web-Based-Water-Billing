import React from "react";
import DashboardLayout from "../../layouts/DashboardLayout";
import MetricCard from "../../components/dashboard/MetricCard";
import DataTable from "../../components/dashboard/DataTable";

const residents = [
    {
        id: 1,
        name: "Priya Nair",
        unit: "A-204",
        meter: "MTR-1001",
        status: "Active",
    },
    {
        id: 2,
        name: "Arun Kumar",
        unit: "B-108",
        meter: "MTR-1002",
        status: "Inactive",
    },
    {
        id: 3,
        name: "Sneha Iyer",
        unit: "C-305",
        meter: "MTR-1003",
        status: "Active",
    },
    {
        id: 4,
        name: "Rahul Das",
        unit: "A-112",
        meter: "MTR-1004",
        status: "Pending",
    },
    {
        id: 5,
        name: "Karthik R",
        unit: "D-401",
        meter: "MTR-1005",
        status: "Active",
    },
];

const StatusBadge = ({ status }) => {
    const colors = {
        Active: "bg-green-100 text-green-700",
        Pending: "bg-yellow-100 text-yellow-700",
        Inactive: "bg-red-100 text-red-700",
    };

    return (
        <span
            className={`px-3 py-1 rounded-full text-xs font-medium ${
                colors[status] || "bg-gray-100 text-gray-700"
            }`}
        >
      {status}
    </span>
    );
};

const columns = [
    {
        key: "name",
        label: "Resident",
    },
    {
        key: "unit",
        label: "Unit",
    },
    {
        key: "meter",
        label: "Meter ID",
    },
    {
        key: "status",
        label: "Status",
        render: (row) => <StatusBadge status={row.status} />,
    },
];

const Residents = () => {
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
                    <h1 className="text-2xl font-bold text-gray-900">
                        Resident Management
                    </h1>
                    <p className="text-gray-500">
                        View and manage all residents in your community.
                    </p>
                </div>

                <div className="grid md:grid-cols-3 gap-5">
                    <MetricCard
                        label="Total Residents"
                        value="1,284"
                        change={3.2}
                    />

                    <MetricCard
                        label="Active Residents"
                        value="1,240"
                        change={1.8}
                    />

                    <MetricCard
                        label="Pending Approvals"
                        value="44"
                        change={-5.1}
                    />
                </div>

                <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-6">
                    <div className="flex justify-between items-center mb-5">
                        <h2 className="text-lg font-semibold">
                            Residents
                        </h2>

                        <button className="bg-teal-600 hover:bg-teal-700 text-white px-4 py-2 rounded-lg text-sm font-medium transition">
                            + Add Resident
                        </button>
                    </div>

                    <DataTable
                        columns={columns}
                        rows={residents}
                        pageSize={5}
                    />
                </div>
            </div>
        </DashboardLayout>
    );
};

export default Residents;