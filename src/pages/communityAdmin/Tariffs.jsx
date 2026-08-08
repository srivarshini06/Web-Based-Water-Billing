import React, { useState } from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const initialTariffs = [
    {
        id: 1,
        name: "Residential Standard",
        type: "Residential",
        rate: "₹12",
        unit: "per 1,000 L",
        status: "Active",
        updated: "01 July 2026",
    },
    {
        id: 2,
        name: "Residential Premium",
        type: "Residential",
        rate: "₹15",
        unit: "per 1,000 L",
        status: "Active",
        updated: "01 July 2026",
    },
    {
        id: 3,
        name: "High Consumption",
        type: "Residential",
        rate: "₹20",
        unit: "per 1,000 L",
        status: "Active",
        updated: "15 June 2026",
    },
    {
        id: 4,
        name: "Commercial",
        type: "Commercial",
        rate: "₹28",
        unit: "per 1,000 L",
        status: "Inactive",
        updated: "10 May 2026",
    },
];

export default function Tariffs() {
    const [tariffs, setTariffs] = useState(initialTariffs);
    const [showForm, setShowForm] = useState(false);

    const [newTariff, setNewTariff] = useState({
        name: "",
        type: "Residential",
        rate: "",
    });

    const handleChange = (e) => {
        setNewTariff({
            ...newTariff,
            [e.target.name]: e.target.value,
        });
    };

    const handleAddTariff = (e) => {
        e.preventDefault();

        if (!newTariff.name || !newTariff.rate) {
            return;
        }

        const tariff = {
            id: tariffs.length + 1,
            name: newTariff.name,
            type: newTariff.type,
            rate: `₹${newTariff.rate}`,
            unit: "per 1,000 L",
            status: "Active",
            updated: "08 August 2026",
        };

        setTariffs([...tariffs, tariff]);

        setNewTariff({
            name: "",
            type: "Residential",
            rate: "",
        });

        setShowForm(false);
    };

    const toggleStatus = (id) => {
        setTariffs(
            tariffs.map((tariff) =>
                tariff.id === id
                    ? {
                        ...tariff,
                        status:
                            tariff.status === "Active"
                                ? "Inactive"
                                : "Active",
                    }
                    : tariff
            )
        );
    };

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
                            Tariffs
                        </h1>

                        <p className="text-gray-500 mt-2">
                            Manage water billing rates for your community.
                        </p>
                    </div>

                    <button
                        onClick={() => setShowForm(!showForm)}
                        className="bg-teal-600 hover:bg-teal-700 text-white px-5 py-3 rounded-lg"
                    >
                        {showForm ? "Cancel" : "+ Add Tariff"}
                    </button>

                </div>

                {/* Summary Cards */}
                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">

                    <div className="bg-white rounded-xl shadow p-6">
                        <p className="text-gray-500">
                            Active Tariffs
                        </p>

                        <h2 className="text-3xl font-bold text-green-600 mt-2">
                            {tariffs.filter(
                                (tariff) => tariff.status === "Active"
                            ).length}
                        </h2>
                    </div>

                    <div className="bg-white rounded-xl shadow p-6">
                        <p className="text-gray-500">
                            Standard Rate
                        </p>

                        <h2 className="text-3xl font-bold text-teal-600 mt-2">
                            ₹12
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            per 1,000 L
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow p-6">
                        <p className="text-gray-500">
                            Highest Rate
                        </p>

                        <h2 className="text-3xl font-bold mt-2">
                            ₹28
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            per 1,000 L
                        </p>
                    </div>

                </div>

                {/* Add Tariff Form */}
                {showForm && (
                    <div className="bg-white rounded-xl shadow p-6">

                        <h2 className="text-xl font-semibold mb-6">
                            Add New Tariff
                        </h2>

                        <form
                            onSubmit={handleAddTariff}
                            className="grid grid-cols-1 md:grid-cols-3 gap-5"
                        >

                            <div>
                                <label className="block text-sm font-medium mb-2">
                                    Tariff Name
                                </label>

                                <input
                                    type="text"
                                    name="name"
                                    value={newTariff.name}
                                    onChange={handleChange}
                                    placeholder="e.g. Residential Standard"
                                    className="w-full border border-gray-300 rounded-lg px-4 py-3 outline-none focus:ring-2 focus:ring-teal-500"
                                />
                            </div>

                            <div>
                                <label className="block text-sm font-medium mb-2">
                                    Type
                                </label>

                                <select
                                    name="type"
                                    value={newTariff.type}
                                    onChange={handleChange}
                                    className="w-full border border-gray-300 rounded-lg px-4 py-3"
                                >
                                    <option value="Residential">
                                        Residential
                                    </option>

                                    <option value="Commercial">
                                        Commercial
                                    </option>
                                </select>
                            </div>

                            <div>
                                <label className="block text-sm font-medium mb-2">
                                    Rate per 1,000 L
                                </label>

                                <input
                                    type="number"
                                    name="rate"
                                    value={newTariff.rate}
                                    onChange={handleChange}
                                    placeholder="12"
                                    className="w-full border border-gray-300 rounded-lg px-4 py-3 outline-none focus:ring-2 focus:ring-teal-500"
                                />
                            </div>

                            <div className="md:col-span-3">

                                <button
                                    type="submit"
                                    className="bg-teal-600 hover:bg-teal-700 text-white px-6 py-3 rounded-lg"
                                >
                                    Add Tariff
                                </button>

                            </div>

                        </form>

                    </div>
                )}

                {/* Tariff Table */}
                <div className="bg-white rounded-xl shadow overflow-hidden">

                    <div className="p-6 border-b">

                        <h2 className="text-xl font-semibold">
                            Current Tariffs
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Billing rates currently configured for the
                            community.
                        </p>

                    </div>

                    <div className="overflow-x-auto">

                        <table className="w-full">

                            <thead className="bg-gray-50">

                            <tr className="text-left">

                                <th className="p-4">
                                    Tariff
                                </th>

                                <th className="p-4">
                                    Type
                                </th>

                                <th className="p-4">
                                    Rate
                                </th>

                                <th className="p-4">
                                    Status
                                </th>

                                <th className="p-4">
                                    Last Updated
                                </th>

                                <th className="p-4">
                                    Action
                                </th>

                            </tr>

                            </thead>

                            <tbody>

                            {tariffs.map((tariff) => (

                                <tr
                                    key={tariff.id}
                                    className="border-t hover:bg-gray-50"
                                >

                                    <td className="p-4">

                                        <p className="font-semibold">
                                            {tariff.name}
                                        </p>

                                    </td>

                                    <td className="p-4">
                                        {tariff.type}
                                    </td>

                                    <td className="p-4">

                                        <p className="font-semibold">
                                            {tariff.rate}
                                        </p>

                                        <p className="text-xs text-gray-500">
                                            {tariff.unit}
                                        </p>

                                    </td>

                                    <td className="p-4">

                                            <span
                                                className={`px-3 py-1 rounded-full text-sm ${
                                                    tariff.status === "Active"
                                                        ? "bg-green-100 text-green-700"
                                                        : "bg-gray-100 text-gray-600"
                                                }`}
                                            >
                                                {tariff.status}
                                            </span>

                                    </td>

                                    <td className="p-4 text-gray-600">
                                        {tariff.updated}
                                    </td>

                                    <td className="p-4">

                                        <button
                                            onClick={() =>
                                                toggleStatus(tariff.id)
                                            }
                                            className={`font-medium ${
                                                tariff.status === "Active"
                                                    ? "text-red-500 hover:text-red-700"
                                                    : "text-teal-600 hover:text-teal-800"
                                            }`}
                                        >
                                            {tariff.status === "Active"
                                                ? "Deactivate"
                                                : "Activate"}
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