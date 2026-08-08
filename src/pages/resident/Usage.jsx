import React, { useState } from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const weeklyUsage = [
    { day: "Mon", value: 210 },
    { day: "Tue", value: 260 },
    { day: "Wed", value: 180 },
    { day: "Thu", value: 310 },
    { day: "Fri", value: 240 },
    { day: "Sat", value: 280 },
    { day: "Sun", value: 190 },
];

const monthlyUsage = [
    { month: "Feb 2026", usage: "11,500 L" },
    { month: "Mar 2026", usage: "12,300 L" },
    { month: "Apr 2026", usage: "13,000 L" },
    { month: "May 2026", usage: "14,500 L" },
    { month: "Jun 2026", usage: "14,000 L" },
    { month: "Jul 2026", usage: "14,200 L" },
];

const dailyDetails = [
    {
        date: "04 Aug 2026",
        usage: "460 L",
        status: "Normal",
    },
    {
        date: "03 Aug 2026",
        usage: "510 L",
        status: "High",
    },
    {
        date: "02 Aug 2026",
        usage: "430 L",
        status: "Normal",
    },
    {
        date: "01 Aug 2026",
        usage: "470 L",
        status: "Normal",
    },
    {
        date: "31 Jul 2026",
        usage: "390 L",
        status: "Low",
    },
];

const Usage = () => {
    const [period, setPeriod] = useState("weekly");

    return (
        <DashboardLayout
            role="resident"
            user={{
                name: "Priya Nair",
                email: "priya@aquaflow.com",
            }}
        >
            {/* Page Header */}
            <div className="mb-8">
                <h1 className="text-3xl font-bold text-gray-900">
                    Water Usage
                </h1>

                <p className="text-gray-500 mt-1">
                    Monitor your water consumption and usage patterns.
                </p>
            </div>

            {/* Summary Cards */}
            <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-4 gap-6 mb-8">

                {/* Current Usage */}
                <div className="bg-white rounded-xl shadow p-6">
                    <p className="text-gray-500 text-sm">
                        Current Usage
                    </p>

                    <h2 className="text-3xl font-bold mt-3">
                        14,200 L
                    </h2>

                    <p className="text-green-600 text-sm mt-2">
                        ↓ 8% from last month
                    </p>
                </div>

                {/* Today's Usage */}
                <div className="bg-white rounded-xl shadow p-6">
                    <p className="text-gray-500 text-sm">
                        Today's Usage
                    </p>

                    <h2 className="text-3xl font-bold mt-3">
                        460 L
                    </h2>

                    <p className="text-gray-500 text-sm mt-2">
                        Within normal range
                    </p>
                </div>

                {/* Average */}
                <div className="bg-white rounded-xl shadow p-6">
                    <p className="text-gray-500 text-sm">
                        Average Daily
                    </p>

                    <h2 className="text-3xl font-bold mt-3">
                        470 L
                    </h2>

                    <p className="text-gray-500 text-sm mt-2">
                        Monthly average
                    </p>
                </div>

                {/* Efficiency */}
                <div className="bg-white rounded-xl shadow p-6">
                    <p className="text-gray-500 text-sm">
                        Efficiency
                    </p>

                    <h2 className="text-3xl font-bold text-green-600 mt-3">
                        Excellent
                    </h2>

                    <p className="text-gray-500 text-sm mt-2">
                        Good conservation
                    </p>
                </div>

            </div>

            {/* Usage Chart + Summary */}
            <div className="grid grid-cols-1 lg:grid-cols-3 gap-6 mb-8">

                {/* Chart */}
                <div className="lg:col-span-2 bg-white rounded-xl shadow p-6">

                    <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between mb-6">

                        <div>
                            <h2 className="text-xl font-semibold">
                                Water Consumption
                            </h2>

                            <p className="text-sm text-gray-500 mt-1">
                                Track your water usage over time
                            </p>
                        </div>

                        <div className="flex mt-4 sm:mt-0 border rounded-lg overflow-hidden">

                            <button
                                onClick={() => setPeriod("weekly")}
                                className={`px-4 py-2 text-sm ${
                                    period === "weekly"
                                        ? "bg-teal-600 text-white"
                                        : "bg-white text-gray-600"
                                }`}
                            >
                                Weekly
                            </button>

                            <button
                                onClick={() => setPeriod("monthly")}
                                className={`px-4 py-2 text-sm ${
                                    period === "monthly"
                                        ? "bg-teal-600 text-white"
                                        : "bg-white text-gray-600"
                                }`}
                            >
                                Monthly
                            </button>

                        </div>

                    </div>

                    {/* Weekly Chart */}
                    {period === "weekly" && (
                        <div>
                            <div className="flex items-end justify-between h-72 gap-3">

                                {weeklyUsage.map((item) => (
                                    <div
                                        key={item.day}
                                        className="flex-1 flex flex-col items-center justify-end h-full"
                                    >
                                        <span className="text-xs text-gray-500 mb-2">
                                            {item.value} L
                                        </span>

                                        <div
                                            className="w-full max-w-12 bg-teal-600 rounded-t-lg"
                                            style={{
                                                height: `${item.value / 1.2}px`,
                                            }}
                                        />

                                        <span className="mt-3 text-sm text-gray-500">
                                            {item.day}
                                        </span>
                                    </div>
                                ))}

                            </div>

                            <div className="mt-6 pt-4 border-t text-sm text-gray-500">
                                Highest usage:{" "}
                                <span className="font-semibold text-gray-800">
                                    Thursday — 310 L
                                </span>
                            </div>
                        </div>
                    )}

                    {/* Monthly Chart */}
                    {period === "monthly" && (
                        <div>
                            <div className="flex items-end justify-between h-72 gap-3">

                                {monthlyUsage.map((item) => {
                                    const numericValue = parseInt(
                                        item.usage.replace(/,/g, "")
                                    );

                                    return (
                                        <div
                                            key={item.month}
                                            className="flex-1 flex flex-col items-center justify-end h-full"
                                        >
                                            <span className="text-xs text-gray-500 mb-2">
                                                {numericValue.toLocaleString()}
                                            </span>

                                            <div
                                                className="w-full max-w-12 bg-teal-600 rounded-t-lg"
                                                style={{
                                                    height: `${
                                                        numericValue / 70
                                                    }px`,
                                                }}
                                            />

                                            <span className="mt-3 text-xs text-gray-500">
                                                {item.month.split(" ")[0]}
                                            </span>
                                        </div>
                                    );
                                })}

                            </div>

                            <div className="mt-6 pt-4 border-t text-sm text-gray-500">
                                Current month usage:{" "}
                                <span className="font-semibold text-gray-800">
                                    14,200 L
                                </span>
                            </div>
                        </div>
                    )}

                </div>

                {/* Usage Summary */}
                <div className="bg-white rounded-xl shadow p-6">

                    <h2 className="text-xl font-semibold mb-6">
                        Usage Summary
                    </h2>

                    <div className="space-y-6">

                        <div>
                            <p className="text-gray-500 text-sm">
                                Highest Day
                            </p>

                            <h3 className="text-2xl font-bold mt-1">
                                310 L
                            </h3>

                            <p className="text-xs text-gray-400 mt-1">
                                Thursday
                            </p>
                        </div>

                        <div>
                            <p className="text-gray-500 text-sm">
                                Lowest Day
                            </p>

                            <h3 className="text-2xl font-bold mt-1">
                                180 L
                            </h3>

                            <p className="text-xs text-gray-400 mt-1">
                                Wednesday
                            </p>
                        </div>

                        <div>
                            <p className="text-gray-500 text-sm">
                                Average
                            </p>

                            <h3 className="text-2xl font-bold mt-1">
                                470 L
                            </h3>

                            <p className="text-xs text-gray-400 mt-1">
                                Per day
                            </p>
                        </div>

                        <div>
                            <p className="text-gray-500 text-sm">
                                Efficiency
                            </p>

                            <h3 className="text-2xl font-bold text-green-600 mt-1">
                                Excellent
                            </h3>

                            <p className="text-xs text-gray-400 mt-1">
                                Keep it up!
                            </p>
                        </div>

                    </div>

                </div>

            </div>

            {/* Monthly Usage History */}
            <div className="bg-white rounded-xl shadow mb-8">

                <div className="p-6 border-b">
                    <h2 className="text-xl font-semibold">
                        Monthly Usage History
                    </h2>

                    <p className="text-sm text-gray-500 mt-1">
                        Your water consumption for the last six months
                    </p>
                </div>

                <div className="overflow-x-auto">

                    <table className="w-full">

                        <thead className="bg-gray-50">

                        <tr>
                            <th className="text-left p-4 text-sm font-semibold">
                                Month
                            </th>

                            <th className="text-left p-4 text-sm font-semibold">
                                Consumption
                            </th>

                            <th className="text-left p-4 text-sm font-semibold">
                                Comparison
                            </th>

                            <th className="text-left p-4 text-sm font-semibold">
                                Status
                            </th>
                        </tr>

                        </thead>

                        <tbody>

                        {monthlyUsage.map((item, index) => {

                            const previous =
                                index > 0
                                    ? parseInt(
                                        monthlyUsage[index - 1].usage.replace(
                                            /,/g,
                                            ""
                                        )
                                    )
                                    : null;

                            const current = parseInt(
                                item.usage.replace(/,/g, "")
                            );

                            let comparison = "—";

                            if (previous) {
                                const difference =
                                    ((current - previous) / previous) * 100;

                                comparison =
                                    difference > 0
                                        ? `↑ ${difference.toFixed(1)}%`
                                        : `↓ ${Math.abs(
                                            difference
                                        ).toFixed(1)}%`;
                            }

                            return (
                                <tr
                                    key={item.month}
                                    className="border-t hover:bg-gray-50"
                                >
                                    <td className="p-4 font-medium">
                                        {item.month}
                                    </td>

                                    <td className="p-4">
                                        {item.usage}
                                    </td>

                                    <td
                                        className={`p-4 ${
                                            comparison.startsWith("↑")
                                                ? "text-red-500"
                                                : "text-green-600"
                                        }`}
                                    >
                                        {comparison}
                                    </td>

                                    <td className="p-4">
                                        <span className="px-3 py-1 rounded-full text-xs bg-green-100 text-green-700">
                                            Normal
                                        </span>
                                    </td>
                                </tr>
                            );
                        })}

                        </tbody>

                    </table>

                </div>

            </div>

            {/* Daily Usage Details */}
            <div className="bg-white rounded-xl shadow">

                <div className="p-6 border-b">
                    <h2 className="text-xl font-semibold">
                        Recent Usage
                    </h2>

                    <p className="text-sm text-gray-500 mt-1">
                        Latest meter readings
                    </p>
                </div>

                <div className="overflow-x-auto">

                    <table className="w-full">

                        <thead className="bg-gray-50">

                        <tr>
                            <th className="text-left p-4 text-sm font-semibold">
                                Date
                            </th>

                            <th className="text-left p-4 text-sm font-semibold">
                                Usage
                            </th>

                            <th className="text-left p-4 text-sm font-semibold">
                                Status
                            </th>
                        </tr>

                        </thead>

                        <tbody>

                        {dailyDetails.map((item) => (
                            <tr
                                key={item.date}
                                className="border-t hover:bg-gray-50"
                            >
                                <td className="p-4">
                                    {item.date}
                                </td>

                                <td className="p-4 font-medium">
                                    {item.usage}
                                </td>

                                <td className="p-4">

                                    <span
                                        className={`px-3 py-1 rounded-full text-xs ${
                                            item.status === "High"
                                                ? "bg-red-100 text-red-700"
                                                : item.status === "Low"
                                                    ? "bg-blue-100 text-blue-700"
                                                    : "bg-green-100 text-green-700"
                                        }`}
                                    >
                                        {item.status}
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
};

export default Usage;