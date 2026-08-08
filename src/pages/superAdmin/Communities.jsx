

import React from "react";
import DashboardLayout from "../../layouts/DashboardLayout";
import MetricCard from "../../components/dashboard/MetricCard";
import DataTable from "../../components/dashboard/DataTable";

const communities = [
    {
        id: 1,
        name: "Palm Residency",
        city: "Chennai",
        residents: 1284,
        status: "Active",
    },
    {
        id: 2,
        name: "Lake View Homes",
        city: "Bangalore",
        residents: 846,
        status: "Active",
    },
    {
        id: 3,
        name: "Green Valley",
        city: "Hyderabad",
        residents: 632,
        status: "Pending",
    },
];

const StatusBadge = ({ status }) => (
    <span
        className={`px-2 py-1 rounded-full text-xs ${
            status === "Active"
                ? "bg-green-100 text-green-700"
                : "bg-yellow-100 text-yellow-700"
        }`}
    >
    {status}
  </span>
);

const columns = [
    { key: "name", label: "Community" },
    { key: "city", label: "City" },
    { key: "residents", label: "Residents" },
    {
        key: "status",
        label: "Status",
        render: (row) => <StatusBadge status={row.status} />,
    },
];

const Communities = () => {
    return (
        <DashboardLayout
            role="superAdmin"
            user={{ name: "Admin", email: "admin@aquaflow.com" }}
        >
            <div className="space-y-6">

                <div>
                    <h1 className="text-2xl font-bold">
                        Communities
                    </h1>
                    <p className="text-gray-500">
                        Manage all registered communities.
                    </p>
                </div>

                <div className="grid md:grid-cols-3 gap-5">
                    <MetricCard label="Communities" value="142" change={4.2}/>
                    <MetricCard label="Residents" value="12,942" change={2.1}/>
                    <MetricCard label="Pending Approval" value="8"/>
                </div>

                <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-6">

                    <div className="flex justify-between items-center mb-5">
                        <h2 className="font-semibold text-lg">
                            Community List
                        </h2>

                        <button className="bg-teal-600 text-white px-4 py-2 rounded-lg">
                            + Add Community
                        </button>

                    </div>

                    <DataTable
                        columns={columns}
                        rows={communities}
                        pageSize={5}
                    />

                </div>

            </div>
        </DashboardLayout>
    );
};

export default Communities;