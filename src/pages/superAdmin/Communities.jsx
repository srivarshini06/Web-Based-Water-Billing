import React, { useState } from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const initialCommunities = [
    {
        id: 1,
        name: "Palm Residency",
        location: "Chennai",
        residents: 428,
        admin: "Rahul Menon",
        status: "Active",
    },
    {
        id: 2,
        name: "Lakeview Homes",
        location: "Bangalore",
        residents: 315,
        admin: "Divya S",
        status: "Active",
    },
    {
        id: 3,
        name: "Green Valley",
        location: "Coimbatore",
        residents: 256,
        admin: "Kavya R",
        status: "Active",
    },
    {
        id: 4,
        name: "Oakridge Heights",
        location: "Hyderabad",
        residents: 189,
        admin: "Jessica Vane",
        status: "Pending",
    },
    {
        id: 5,
        name: "Sunrise Apartments",
        location: "Chennai",
        residents: 174,
        admin: "Arun Kumar",
        status: "Active",
    },
];

export default function Communities() {
    const [communities, setCommunities] = useState(
        initialCommunities
    );

    const [search, setSearch] = useState("");
    const [statusFilter, setStatusFilter] = useState(
        "All Statuses"
    );

    const filteredCommunities = communities.filter((community) => {
        const matchesSearch =
            community.name
                .toLowerCase()
                .includes(search.toLowerCase()) ||
            community.location
                .toLowerCase()
                .includes(search.toLowerCase()) ||
            community.admin
                .toLowerCase()
                .includes(search.toLowerCase());

        const matchesStatus =
            statusFilter === "All Statuses" ||
            community.status === statusFilter;

        return matchesSearch && matchesStatus;
    });

    const toggleStatus = (id) => {
        setCommunities((current) =>
            current.map((community) =>
                community.id === id
                    ? {
                        ...community,
                        status:
                            community.status === "Active"
                                ? "Suspended"
                                : "Active",
                    }
                    : community
            )
        );
    };

    const activeCount = communities.filter(
        (community) => community.status === "Active"
    ).length;

    const pendingCount = communities.filter(
        (community) => community.status === "Pending"
    ).length;

    const totalResidents = communities.reduce(
        (total, community) => total + community.residents,
        0
    );

    return (
        <DashboardLayout
            role="superAdmin"
            user={{
                name: "System Admin",
                email: "admin@aquaflow.com",
            }}
        >
            <div className="space-y-6">

                {/* Header */}
                <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4">

                    <div>
                        <h1 className="text-3xl font-bold text-gray-900">
                            Communities
                        </h1>

                        <p className="text-gray-500 mt-1">
                            Manage all registered AquaFlow communities.
                        </p>
                    </div>

                    <button
                        onClick={() =>
                            alert(
                                "Create Community functionality can be connected to the backend later."
                            )
                        }
                        className="bg-teal-600 hover:bg-teal-700 text-white px-4 py-2 rounded-lg text-sm font-medium"
                    >
                        + Create Community
                    </button>

                </div>

                {/* Statistics */}
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-5">

                    <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-5">
                        <p className="text-sm text-gray-500">
                            Total Communities
                        </p>

                        <p className="text-3xl font-bold mt-2">
                            {communities.length}
                        </p>

                        <p className="text-xs text-teal-600 mt-2">
                            Registered communities
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-5">
                        <p className="text-sm text-gray-500">
                            Active Communities
                        </p>

                        <p className="text-3xl font-bold mt-2">
                            {activeCount}
                        </p>

                        <p className="text-xs text-green-600 mt-2">
                            Currently operational
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-5">
                        <p className="text-sm text-gray-500">
                            Pending Approval
                        </p>

                        <p className="text-3xl font-bold mt-2">
                            {pendingCount}
                        </p>

                        <p className="text-xs text-orange-600 mt-2">
                            Requires review
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-5">
                        <p className="text-sm text-gray-500">
                            Total Residents
                        </p>

                        <p className="text-3xl font-bold mt-2">
                            {totalResidents.toLocaleString()}
                        </p>

                        <p className="text-xs text-gray-500 mt-2">
                            Across listed communities
                        </p>
                    </div>

                </div>

                {/* Search and Filter */}
                <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-4">

                    <div className="flex flex-col md:flex-row gap-4">

                        <input
                            type="text"
                            value={search}
                            onChange={(e) =>
                                setSearch(e.target.value)
                            }
                            placeholder="Search community, location or admin..."
                            className="flex-1 border border-gray-200 rounded-lg px-4 py-2.5 text-sm outline-none focus:ring-2 focus:ring-teal-500"
                        />

                        <select
                            value={statusFilter}
                            onChange={(e) =>
                                setStatusFilter(e.target.value)
                            }
                            className="border border-gray-200 rounded-lg px-4 py-2.5 text-sm outline-none"
                        >
                            <option>All Statuses</option>
                            <option>Active</option>
                            <option>Pending</option>
                            <option>Suspended</option>
                        </select>

                    </div>

                </div>

                {/* Communities Table */}
                <div className="bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden">

                    <div className="px-6 py-4 border-b border-gray-100">

                        <h2 className="font-semibold text-gray-900">
                            All Communities
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Showing {filteredCommunities.length} of{" "}
                            {communities.length} communities
                        </p>

                    </div>

                    <div className="overflow-x-auto">

                        <table className="w-full">

                            <thead className="bg-gray-50">

                            <tr className="text-left text-xs uppercase tracking-wide text-gray-500">

                                <th className="px-6 py-4">
                                    Community
                                </th>

                                <th className="px-6 py-4">
                                    Location
                                </th>

                                <th className="px-6 py-4">
                                    Residents
                                </th>

                                <th className="px-6 py-4">
                                    Admin
                                </th>

                                <th className="px-6 py-4">
                                    Status
                                </th>

                                <th className="px-6 py-4 text-right">
                                    Action
                                </th>

                            </tr>

                            </thead>

                            <tbody>

                            {filteredCommunities.map(
                                (community) => (

                                    <tr
                                        key={community.id}
                                        className="border-t border-gray-100 hover:bg-gray-50"
                                    >

                                        {/* Community */}
                                        <td className="px-6 py-4">

                                            <div className="flex items-center gap-3">

                                                <div className="w-9 h-9 rounded-lg bg-teal-100 text-teal-700 flex items-center justify-center font-bold">
                                                    A
                                                </div>

                                                <span className="font-medium text-gray-900">
                                                        {
                                                            community.name
                                                        }
                                                    </span>

                                            </div>

                                        </td>

                                        {/* Location */}
                                        <td className="px-6 py-4 text-gray-600">
                                            {
                                                community.location
                                            }
                                        </td>

                                        {/* Residents */}
                                        <td className="px-6 py-4 text-gray-700">
                                            {community.residents.toLocaleString()}
                                        </td>

                                        {/* Admin */}
                                        <td className="px-6 py-4 text-gray-700">
                                            {community.admin}
                                        </td>

                                        {/* Status */}
                                        <td className="px-6 py-4">

                                                <span
                                                    className={`px-2.5 py-1 rounded-full text-xs font-medium ${
                                                        community.status ===
                                                        "Active"
                                                            ? "bg-green-100 text-green-700"
                                                            : community.status ===
                                                            "Pending"
                                                                ? "bg-orange-100 text-orange-700"
                                                                : "bg-red-100 text-red-700"
                                                    }`}
                                                >
                                                    {
                                                        community.status
                                                    }
                                                </span>

                                        </td>

                                        {/* Action */}
                                        <td className="px-6 py-4 text-right">

                                            <button
                                                onClick={() =>
                                                    toggleStatus(
                                                        community.id
                                                    )
                                                }
                                                className={`px-3 py-1.5 rounded-lg text-xs font-medium ${
                                                    community.status ===
                                                    "Suspended"
                                                        ? "bg-teal-600 text-white hover:bg-teal-700"
                                                        : "border border-red-200 text-red-600 hover:bg-red-50"
                                                }`}
                                            >
                                                {community.status ===
                                                "Suspended"
                                                    ? "Activate"
                                                    : "Suspend"}
                                            </button>

                                        </td>

                                    </tr>
                                )
                            )}

                            </tbody>

                        </table>

                    </div>

                    {/* Empty State */}
                    {filteredCommunities.length === 0 && (
                        <div className="py-12 text-center text-gray-500">
                            No communities found.
                        </div>
                    )}

                </div>

            </div>
        </DashboardLayout>
    );
}