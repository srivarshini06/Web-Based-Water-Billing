import React, { useState } from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

export default function Settings() {
    const [notifications, setNotifications] = useState(true);
    const [emailAlerts, setEmailAlerts] = useState(true);
    const [maintenanceMode, setMaintenanceMode] = useState(false);

    const [saved, setSaved] = useState(false);

    const handleSave = () => {
        setSaved(true);

        setTimeout(() => {
            setSaved(false);
        }, 2500);
    };

    return (
        <DashboardLayout
            role="superAdmin"
            user={{
                name: "System Admin",
                email: "admin@aquaflow.com",
            }}
        >
            <div className="max-w-5xl space-y-6">

                {/* Header */}
                <div>
                    <h1 className="text-3xl font-bold text-gray-900">
                        Settings
                    </h1>

                    <p className="text-gray-500 mt-1">
                        Manage system-wide AquaFlow settings and preferences.
                    </p>
                </div>

                {/* Success Message */}
                {saved && (
                    <div className="bg-green-50 border border-green-200 text-green-700 px-4 py-3 rounded-lg">
                        Settings saved successfully.
                    </div>
                )}

                {/* Account Information */}
                <div className="bg-white rounded-xl shadow-sm border border-gray-100">

                    <div className="p-6 border-b border-gray-100">
                        <h2 className="text-lg font-semibold text-gray-900">
                            Administrator Account
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Information about the current system administrator.
                        </p>
                    </div>

                    <div className="p-6 grid md:grid-cols-2 gap-6">

                        <div>
                            <label className="block text-sm font-medium text-gray-700 mb-2">
                                Full Name
                            </label>

                            <input
                                type="text"
                                value="System Admin"
                                readOnly
                                className="w-full border border-gray-200 rounded-lg px-4 py-2.5 bg-gray-50 text-gray-700"
                            />
                        </div>

                        <div>
                            <label className="block text-sm font-medium text-gray-700 mb-2">
                                Email Address
                            </label>

                            <input
                                type="email"
                                value="admin@aquaflow.com"
                                readOnly
                                className="w-full border border-gray-200 rounded-lg px-4 py-2.5 bg-gray-50 text-gray-700"
                            />
                        </div>

                        <div>
                            <label className="block text-sm font-medium text-gray-700 mb-2">
                                Account Role
                            </label>

                            <input
                                type="text"
                                value="Super Admin"
                                readOnly
                                className="w-full border border-gray-200 rounded-lg px-4 py-2.5 bg-gray-50 text-gray-700"
                            />
                        </div>

                        <div>
                            <label className="block text-sm font-medium text-gray-700 mb-2">
                                Account Status
                            </label>

                            <div className="flex items-center h-[42px]">
                                <span className="px-3 py-1 rounded-full text-sm font-medium bg-green-100 text-green-700">
                                    Active
                                </span>
                            </div>
                        </div>

                    </div>

                </div>

                {/* System Preferences */}
                <div className="bg-white rounded-xl shadow-sm border border-gray-100">

                    <div className="p-6 border-b border-gray-100">
                        <h2 className="text-lg font-semibold text-gray-900">
                            System Preferences
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Configure notifications and platform behaviour.
                        </p>
                    </div>

                    <div className="divide-y divide-gray-100">

                        {/* Notifications */}
                        <div className="p-6 flex items-center justify-between gap-6">

                            <div>
                                <h3 className="font-medium text-gray-900">
                                    System Notifications
                                </h3>

                                <p className="text-sm text-gray-500 mt-1">
                                    Receive notifications about important system events.
                                </p>
                            </div>

                            <button
                                type="button"
                                onClick={() =>
                                    setNotifications(!notifications)
                                }
                                className={`relative w-12 h-6 rounded-full transition ${
                                    notifications
                                        ? "bg-teal-600"
                                        : "bg-gray-300"
                                }`}
                            >
                                <span
                                    className={`absolute top-1 w-4 h-4 bg-white rounded-full transition ${
                                        notifications
                                            ? "left-7"
                                            : "left-1"
                                    }`}
                                />
                            </button>

                        </div>

                        {/* Email Alerts */}
                        <div className="p-6 flex items-center justify-between gap-6">

                            <div>
                                <h3 className="font-medium text-gray-900">
                                    Email Alerts
                                </h3>

                                <p className="text-sm text-gray-500 mt-1">
                                    Receive important platform alerts by email.
                                </p>
                            </div>

                            <button
                                type="button"
                                onClick={() =>
                                    setEmailAlerts(!emailAlerts)
                                }
                                className={`relative w-12 h-6 rounded-full transition ${
                                    emailAlerts
                                        ? "bg-teal-600"
                                        : "bg-gray-300"
                                }`}
                            >
                                <span
                                    className={`absolute top-1 w-4 h-4 bg-white rounded-full transition ${
                                        emailAlerts
                                            ? "left-7"
                                            : "left-1"
                                    }`}
                                />
                            </button>

                        </div>

                        {/* Maintenance */}
                        <div className="p-6 flex items-center justify-between gap-6">

                            <div>
                                <h3 className="font-medium text-gray-900">
                                    Maintenance Mode
                                </h3>

                                <p className="text-sm text-gray-500 mt-1">
                                    Temporarily restrict access while system maintenance is performed.
                                </p>
                            </div>

                            <button
                                type="button"
                                onClick={() =>
                                    setMaintenanceMode(!maintenanceMode)
                                }
                                className={`relative w-12 h-6 rounded-full transition ${
                                    maintenanceMode
                                        ? "bg-red-500"
                                        : "bg-gray-300"
                                }`}
                            >
                                <span
                                    className={`absolute top-1 w-4 h-4 bg-white rounded-full transition ${
                                        maintenanceMode
                                            ? "left-7"
                                            : "left-1"
                                    }`}
                                />
                            </button>

                        </div>

                    </div>

                </div>

                {/* Security */}
                <div className="bg-white rounded-xl shadow-sm border border-gray-100">

                    <div className="p-6 border-b border-gray-100">
                        <h2 className="text-lg font-semibold text-gray-900">
                            Security
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Manage administrator account security.
                        </p>
                    </div>

                    <div className="p-6 space-y-4">

                        <div className="flex flex-col md:flex-row md:items-center justify-between gap-4">

                            <div>
                                <h3 className="font-medium text-gray-900">
                                    Password
                                </h3>

                                <p className="text-sm text-gray-500">
                                    Last changed recently.
                                </p>
                            </div>

                            <button
                                onClick={() =>
                                    alert(
                                        "Password change can be connected to the backend later."
                                    )
                                }
                                className="border border-gray-200 px-4 py-2 rounded-lg text-sm font-medium hover:bg-gray-50"
                            >
                                Change Password
                            </button>

                        </div>

                        <div className="flex flex-col md:flex-row md:items-center justify-between gap-4 pt-4 border-t">

                            <div>
                                <h3 className="font-medium text-gray-900">
                                    Two-Factor Authentication
                                </h3>

                                <p className="text-sm text-gray-500">
                                    Add an additional layer of security.
                                </p>
                            </div>

                            <button
                                onClick={() =>
                                    alert(
                                        "Two-factor authentication can be connected later."
                                    )
                                }
                                className="border border-teal-600 text-teal-600 px-4 py-2 rounded-lg text-sm font-medium hover:bg-teal-50"
                            >
                                Configure 2FA
                            </button>

                        </div>

                    </div>

                </div>

                {/* Save */}
                <div className="flex justify-end">

                    <button
                        onClick={handleSave}
                        className="bg-teal-600 hover:bg-teal-700 text-white px-6 py-2.5 rounded-lg font-medium"
                    >
                        Save Changes
                    </button>

                </div>

            </div>
        </DashboardLayout>
    );
}