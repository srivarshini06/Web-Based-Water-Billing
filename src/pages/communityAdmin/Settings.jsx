import React from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

export default function Settings() {
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
                <div>
                    <h1 className="text-3xl font-bold text-gray-900">
                        Settings
                    </h1>

                    <p className="text-gray-500 mt-2">
                        Manage your community and account settings.
                    </p>
                </div>

                {/* Community Information */}
                <div className="bg-white rounded-xl shadow p-6">

                    <h2 className="text-xl font-semibold mb-6">
                        Community Information
                    </h2>

                    <div className="grid md:grid-cols-2 gap-6">

                        <div>
                            <label className="block text-sm font-medium text-gray-600 mb-2">
                                Community Name
                            </label>

                            <input
                                type="text"
                                value="Palm Residency"
                                readOnly
                                className="w-full border border-gray-300 rounded-lg px-4 py-3 bg-gray-50"
                            />
                        </div>

                        <div>
                            <label className="block text-sm font-medium text-gray-600 mb-2">
                                Community Code
                            </label>

                            <input
                                type="text"
                                value="PALM-001"
                                readOnly
                                className="w-full border border-gray-300 rounded-lg px-4 py-3 bg-gray-50"
                            />
                        </div>

                        <div>
                            <label className="block text-sm font-medium text-gray-600 mb-2">
                                Total Residents
                            </label>

                            <input
                                type="text"
                                value="1,284"
                                readOnly
                                className="w-full border border-gray-300 rounded-lg px-4 py-3 bg-gray-50"
                            />
                        </div>

                        <div>
                            <label className="block text-sm font-medium text-gray-600 mb-2">
                                Community Status
                            </label>

                            <div className="mt-1">
                                <span className="inline-block px-4 py-2 rounded-full bg-green-100 text-green-700 font-medium">
                                    Active
                                </span>
                            </div>
                        </div>

                    </div>

                </div>

                {/* Admin Profile */}
                <div className="bg-white rounded-xl shadow p-6">

                    <h2 className="text-xl font-semibold mb-6">
                        Admin Profile
                    </h2>

                    <div className="grid md:grid-cols-2 gap-6">

                        <div>
                            <label className="block text-sm font-medium text-gray-600 mb-2">
                                Name
                            </label>

                            <input
                                type="text"
                                defaultValue="Rahul"
                                className="w-full border border-gray-300 rounded-lg px-4 py-3 outline-none focus:ring-2 focus:ring-teal-500"
                            />
                        </div>

                        <div>
                            <label className="block text-sm font-medium text-gray-600 mb-2">
                                Email
                            </label>

                            <input
                                type="email"
                                defaultValue="rahul@aquaflow.com"
                                className="w-full border border-gray-300 rounded-lg px-4 py-3 outline-none focus:ring-2 focus:ring-teal-500"
                            />
                        </div>

                    </div>

                    <button
                        className="mt-6 bg-teal-600 hover:bg-teal-700 text-white px-6 py-3 rounded-lg"
                    >
                        Save Changes
                    </button>

                </div>

                {/* Notification Settings */}
                <div className="bg-white rounded-xl shadow p-6">

                    <h2 className="text-xl font-semibold mb-6">
                        Notifications
                    </h2>

                    <div className="space-y-5">

                        <label className="flex items-center justify-between border-b pb-4">
                            <div>
                                <p className="font-medium">
                                    Billing Notifications
                                </p>

                                <p className="text-sm text-gray-500">
                                    Receive notifications about pending and overdue bills.
                                </p>
                            </div>

                            <input
                                type="checkbox"
                                defaultChecked
                                className="w-5 h-5 accent-teal-600"
                            />
                        </label>

                        <label className="flex items-center justify-between border-b pb-4">
                            <div>
                                <p className="font-medium">
                                    Usage Alerts
                                </p>

                                <p className="text-sm text-gray-500">
                                    Get notified about unusual water consumption.
                                </p>
                            </div>

                            <input
                                type="checkbox"
                                defaultChecked
                                className="w-5 h-5 accent-teal-600"
                            />
                        </label>

                        <label className="flex items-center justify-between">
                            <div>
                                <p className="font-medium">
                                    Resident Updates
                                </p>

                                <p className="text-sm text-gray-500">
                                    Receive updates when residents are added or removed.
                                </p>
                            </div>

                            <input
                                type="checkbox"
                                defaultChecked
                                className="w-5 h-5 accent-teal-600"
                            />
                        </label>

                    </div>

                </div>

                {/* Security */}
                <div className="bg-white rounded-xl shadow p-6">

                    <h2 className="text-xl font-semibold mb-6">
                        Security
                    </h2>

                    <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4">

                        <div>
                            <p className="font-medium">
                                Change Password
                            </p>

                            <p className="text-sm text-gray-500">
                                Update your account password regularly to keep your account secure.
                            </p>
                        </div>

                        <button
                            className="border border-teal-600 text-teal-600 hover:bg-teal-50 px-5 py-2.5 rounded-lg"
                        >
                            Change Password
                        </button>

                    </div>

                </div>

            </div>
        </DashboardLayout>
    );
}