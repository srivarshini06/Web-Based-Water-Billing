import React, { useState } from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const bills = [
    {
        id: 1,
        month: "July 2026",
        date: "01 Aug 2026",
        amount: "₹842",
        status: "Paid",
        dueDate: "05 Aug 2026",
    },
    {
        id: 2,
        month: "June 2026",
        date: "01 Jul 2026",
        amount: "₹790",
        status: "Paid",
        dueDate: "05 Jul 2026",
    },
    {
        id: 3,
        month: "May 2026",
        date: "01 Jun 2026",
        amount: "₹915",
        status: "Paid",
        dueDate: "05 Jun 2026",
    },
    {
        id: 4,
        month: "April 2026",
        date: "01 May 2026",
        amount: "₹870",
        status: "Pending",
        dueDate: "05 May 2026",
    },
    {
        id: 5,
        month: "March 2026",
        date: "01 Apr 2026",
        amount: "₹760",
        status: "Paid",
        dueDate: "05 Apr 2026",
    },
    {
        id: 6,
        month: "February 2026",
        date: "01 Mar 2026",
        amount: "₹820",
        status: "Paid",
        dueDate: "05 Mar 2026",
    },
];

const Bills = () => {
    const [selectedBill, setSelectedBill] = useState(null);
    const [showAll, setShowAll] = useState(false);

    const displayedBills = showAll ? bills : bills.slice(0, 4);

    const totalPaid = bills
        .filter((bill) => bill.status === "Paid")
        .reduce(
            (total, bill) =>
                total + Number(bill.amount.replace(/[₹,]/g, "")),
            0
        );

    const pendingBills = bills.filter(
        (bill) => bill.status === "Pending"
    );

    const pendingAmount = pendingBills.reduce(
        (total, bill) =>
            total + Number(bill.amount.replace(/[₹,]/g, "")),
        0
    );

    const handlePay = (bill) => {
        alert(`Payment for ${bill.month} will be processed.`);
    };

    const handleDownload = (bill) => {
        alert(`Bill for ${bill.month} selected for download.`);
    };

    return (
        <DashboardLayout
            role="resident"
            user={{
                name: "Priya Nair",
                email: "priya@aquaflow.com",
            }}
        >
            {/* Header */}
            <div className="mb-8">
                <h1 className="text-3xl font-bold text-gray-900">
                    Bills
                </h1>

                <p className="text-gray-500 mt-1">
                    View your water bills, payment history and pending dues.
                </p>
            </div>

            {/* Summary Cards */}
            <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-4 gap-6 mb-8">

                <div className="bg-white rounded-xl shadow p-6">
                    <p className="text-gray-500 text-sm">
                        Current Bill
                    </p>

                    <h2 className="text-3xl font-bold mt-3">
                        ₹842
                    </h2>

                    <p className="text-gray-500 text-sm mt-2">
                        July 2026
                    </p>
                </div>

                <div className="bg-white rounded-xl shadow p-6">
                    <p className="text-gray-500 text-sm">
                        Payment Status
                    </p>

                    <h2 className="text-3xl font-bold text-green-600 mt-3">
                        Paid
                    </h2>

                    <p className="text-gray-500 text-sm mt-2">
                        No immediate action needed
                    </p>
                </div>

                <div className="bg-white rounded-xl shadow p-6">
                    <p className="text-gray-500 text-sm">
                        Pending Amount
                    </p>

                    <h2 className="text-3xl font-bold text-orange-500 mt-3">
                        ₹{pendingAmount}
                    </h2>

                    <p className="text-gray-500 text-sm mt-2">
                        {pendingBills.length} pending bill
                        {pendingBills.length !== 1 ? "s" : ""}
                    </p>
                </div>

                <div className="bg-white rounded-xl shadow p-6">
                    <p className="text-gray-500 text-sm">
                        Total Paid
                    </p>

                    <h2 className="text-3xl font-bold mt-3">
                        ₹{totalPaid}
                    </h2>

                    <p className="text-gray-500 text-sm mt-2">
                        Last 6 months
                    </p>
                </div>

            </div>

            {/* Current Bill */}
            <div className="grid grid-cols-1 lg:grid-cols-3 gap-6 mb-8">

                <div className="lg:col-span-2 bg-white rounded-xl shadow p-6">

                    <div className="flex flex-col sm:flex-row sm:items-start sm:justify-between gap-4">

                        <div>
                            <p className="text-gray-500 text-sm">
                                Latest Bill
                            </p>

                            <h2 className="text-4xl font-bold mt-2">
                                ₹842
                            </h2>

                            <p className="text-gray-500 mt-2">
                                Billing period: July 2026
                            </p>
                        </div>

                        <span className="px-4 py-2 rounded-full bg-green-100 text-green-700 text-sm font-medium">
                            Paid
                        </span>

                    </div>

                    <div className="grid grid-cols-1 sm:grid-cols-3 gap-4 mt-8">

                        <div className="bg-gray-50 rounded-lg p-4">
                            <p className="text-gray-500 text-sm">
                                Water Usage
                            </p>

                            <p className="font-bold text-lg mt-1">
                                14,200 L
                            </p>
                        </div>

                        <div className="bg-gray-50 rounded-lg p-4">
                            <p className="text-gray-500 text-sm">
                                Bill Date
                            </p>

                            <p className="font-bold text-lg mt-1">
                                01 Aug 2026
                            </p>
                        </div>

                        <div className="bg-gray-50 rounded-lg p-4">
                            <p className="text-gray-500 text-sm">
                                Due Date
                            </p>

                            <p className="font-bold text-lg mt-1">
                                05 Aug 2026
                            </p>
                        </div>

                    </div>

                    <div className="flex flex-col sm:flex-row gap-3 mt-6">

                        <button
                            onClick={() => handleDownload(bills[0])}
                            className="flex-1 border border-teal-600 text-teal-600 hover:bg-teal-50 py-3 rounded-lg font-medium"
                        >
                            Download Bill
                        </button>

                        <button
                            onClick={() => setSelectedBill(bills[0])}
                            className="flex-1 bg-teal-600 hover:bg-teal-700 text-white py-3 rounded-lg font-medium"
                        >
                            View Details
                        </button>

                    </div>

                </div>

                {/* Payment Info */}
                <div className="bg-gray-900 rounded-xl shadow p-6 text-white">

                    <p className="text-gray-400 text-sm">
                        Payment Method
                    </p>

                    <h2 className="text-xl font-semibold mt-2">
                        AutoPay
                    </h2>

                    <p className="text-gray-400 text-sm mt-2">
                        Your registered payment method is active.
                    </p>

                    <div className="mt-6 border border-gray-700 rounded-lg p-4">
                        <p className="text-gray-400 text-xs">
                            NEXT PAYMENT
                        </p>

                        <p className="font-semibold mt-1">
                            05 Aug 2026
                        </p>
                    </div>

                    <button className="w-full mt-6 bg-teal-500 hover:bg-teal-400 text-white py-3 rounded-lg font-medium">
                        Manage Payment
                    </button>

                </div>

            </div>

            {/* Bill History */}
            <div className="bg-white rounded-xl shadow mb-8">

                <div className="p-6 border-b flex flex-col sm:flex-row sm:items-center sm:justify-between gap-3">

                    <div>
                        <h2 className="text-xl font-semibold">
                            Bill History
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Your previous water bills
                        </p>
                    </div>

                    <button
                        onClick={() => setShowAll(!showAll)}
                        className="text-sm font-medium text-teal-600 hover:text-teal-700"
                    >
                        {showAll ? "Show Less" : "View All"}
                    </button>

                </div>

                <div className="overflow-x-auto">

                    <table className="w-full">

                        <thead className="bg-gray-50">

                        <tr>
                            <th className="text-left p-4 text-sm font-semibold">
                                Billing Period
                            </th>

                            <th className="text-left p-4 text-sm font-semibold">
                                Bill Date
                            </th>

                            <th className="text-left p-4 text-sm font-semibold">
                                Due Date
                            </th>

                            <th className="text-left p-4 text-sm font-semibold">
                                Amount
                            </th>

                            <th className="text-left p-4 text-sm font-semibold">
                                Status
                            </th>

                            <th className="text-left p-4 text-sm font-semibold">
                                Action
                            </th>
                        </tr>

                        </thead>

                        <tbody>

                        {displayedBills.map((bill) => (
                            <tr
                                key={bill.id}
                                className="border-t hover:bg-gray-50"
                            >
                                <td className="p-4 font-medium">
                                    {bill.month}
                                </td>

                                <td className="p-4 text-gray-600">
                                    {bill.date}
                                </td>

                                <td className="p-4 text-gray-600">
                                    {bill.dueDate}
                                </td>

                                <td className="p-4 font-medium">
                                    {bill.amount}
                                </td>

                                <td className="p-4">

                                    <span
                                        className={`px-3 py-1 rounded-full text-xs font-medium ${
                                            bill.status === "Paid"
                                                ? "bg-green-100 text-green-700"
                                                : "bg-orange-100 text-orange-700"
                                        }`}
                                    >
                                        {bill.status}
                                    </span>

                                </td>

                                <td className="p-4">

                                    <div className="flex gap-2">

                                        <button
                                            onClick={() =>
                                                setSelectedBill(bill)
                                            }
                                            className="text-sm text-teal-600 hover:text-teal-800"
                                        >
                                            View
                                        </button>

                                        <button
                                            onClick={() =>
                                                handleDownload(bill)
                                            }
                                            className="text-sm text-gray-500 hover:text-gray-800"
                                        >
                                            Download
                                        </button>

                                        {bill.status === "Pending" && (
                                            <button
                                                onClick={() =>
                                                    handlePay(bill)
                                                }
                                                className="text-sm text-red-600 hover:text-red-800"
                                            >
                                                Pay
                                            </button>
                                        )}

                                    </div>

                                </td>
                            </tr>
                        ))}

                        </tbody>

                    </table>

                </div>

            </div>

            {/* Billing Information */}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">

                <div className="bg-white rounded-xl shadow p-6">

                    <h2 className="text-xl font-semibold mb-5">
                        Billing Information
                    </h2>

                    <div className="space-y-4">

                        <div className="flex justify-between">
                            <span className="text-gray-500">
                                Resident
                            </span>

                            <span className="font-medium">
                                Priya Nair
                            </span>
                        </div>

                        <div className="flex justify-between">
                            <span className="text-gray-500">
                                Unit
                            </span>

                            <span className="font-medium">
                                A-204
                            </span>
                        </div>

                        <div className="flex justify-between">
                            <span className="text-gray-500">
                                Community
                            </span>

                            <span className="font-medium">
                                Palm Residency
                            </span>
                        </div>

                        <div className="flex justify-between">
                            <span className="text-gray-500">
                                Meter ID
                            </span>

                            <span className="font-medium">
                                MTR-88213
                            </span>
                        </div>

                    </div>

                </div>

                <div className="bg-white rounded-xl shadow p-6">

                    <h2 className="text-xl font-semibold mb-5">
                        Need Help?
                    </h2>

                    <p className="text-gray-500 text-sm leading-6">
                        If you have a question about your bill, payment,
                        meter reading or water charges, contact your
                        community administrator.
                    </p>

                    <button className="mt-6 border border-teal-600 text-teal-600 hover:bg-teal-50 px-5 py-3 rounded-lg font-medium">
                        Contact Administrator
                    </button>

                </div>

            </div>

            {/* Bill Details Modal */}
            {selectedBill && (
                <div className="fixed inset-0 bg-black/40 flex items-center justify-center z-50 p-4">

                    <div className="bg-white rounded-xl shadow-xl w-full max-w-lg">

                        <div className="p-6 border-b flex justify-between items-center">

                            <div>
                                <h2 className="text-xl font-semibold">
                                    Bill Details
                                </h2>

                                <p className="text-sm text-gray-500 mt-1">
                                    {selectedBill.month}
                                </p>
                            </div>

                            <button
                                onClick={() => setSelectedBill(null)}
                                className="text-gray-400 hover:text-gray-700 text-2xl"
                            >
                                ×
                            </button>

                        </div>

                        <div className="p-6 space-y-4">

                            <div className="flex justify-between">
                                <span className="text-gray-500">
                                    Billing Period
                                </span>

                                <span className="font-medium">
                                    {selectedBill.month}
                                </span>
                            </div>

                            <div className="flex justify-between">
                                <span className="text-gray-500">
                                    Bill Date
                                </span>

                                <span className="font-medium">
                                    {selectedBill.date}
                                </span>
                            </div>

                            <div className="flex justify-between">
                                <span className="text-gray-500">
                                    Due Date
                                </span>

                                <span className="font-medium">
                                    {selectedBill.dueDate}
                                </span>
                            </div>

                            <div className="flex justify-between">
                                <span className="text-gray-500">
                                    Water Usage
                                </span>

                                <span className="font-medium">
                                    14,200 L
                                </span>
                            </div>

                            <div className="border-t pt-4 flex justify-between">
                                <span className="font-semibold">
                                    Total Amount
                                </span>

                                <span className="text-2xl font-bold">
                                    {selectedBill.amount}
                                </span>
                            </div>

                            <div className="flex justify-between items-center">
                                <span className="text-gray-500">
                                    Status
                                </span>

                                <span
                                    className={`px-3 py-1 rounded-full text-xs font-medium ${
                                        selectedBill.status === "Paid"
                                            ? "bg-green-100 text-green-700"
                                            : "bg-orange-100 text-orange-700"
                                    }`}
                                >
                                    {selectedBill.status}
                                </span>
                            </div>

                        </div>

                        <div className="p-6 border-t flex gap-3">

                            <button
                                onClick={() =>
                                    handleDownload(selectedBill)
                                }
                                className="flex-1 border border-teal-600 text-teal-600 py-3 rounded-lg"
                            >
                                Download
                            </button>

                            {selectedBill.status === "Pending" && (
                                <button
                                    onClick={() =>
                                        handlePay(selectedBill)
                                    }
                                    className="flex-1 bg-teal-600 text-white py-3 rounded-lg"
                                >
                                    Pay Now
                                </button>
                            )}

                        </div>

                    </div>

                </div>
            )}

        </DashboardLayout>
    );
};

export default Bills;