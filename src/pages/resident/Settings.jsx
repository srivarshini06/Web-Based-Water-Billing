import React from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const Settings = () => {
    return (
        <DashboardLayout
            role="resident"
            user={{
                name: "Priya Nair",
                email: "priya@aquaflow.com",
            }}
        >
            <div className="space-y-8">

                {/* Header */}
                <div>
                    <h1 className="text-4xl font-bold text-gray-900">
                        Settings
                    </h1>

                    <p className="text-gray-500 mt-2">
                        Manage your account and notification preferences.
                    </p>
                </div>

                {/* Account Settings */}
                <div className="bg-white rounded-xl shadow">

                    <div className="p-6 border-b">
                        <h2 className="text-xl font-semibold text-gray-900">
                            Account Settings
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Update your account information.
                        </p>
                    </div>

                    <div className="p-6 space-y-6">

                        <div>
                            <label className="block text-sm font-medium text-gray-700 mb-2">
                                Full Name
                            </label>

                            <input
                                type="text"
                                value="Priya Nair"
                                readOnly
                                className="w-full border border-gray-200 rounded-lg px-4 py-3 bg-gray-50 outline-none"
                            />
                        </div>

                        <div>
                            <label className="block text-sm font-medium text-gray-700 mb-2">
                                Email Address
                            </label>

                            <input
                                type="email"
                                value="priya@aquaflow.com"
                                readOnly
                                className="w-full border border-gray-200 rounded-lg px-4 py-3 bg-gray-50 outline-none"
                            />
                        </div>

                        <div>
                            <label className="block text-sm font-medium text-gray-700 mb-2">
                                Phone Number
                            </label>

                            <input
                                type="text"
                                value="+91 98765 43210"
                                readOnly
                                className="w-full border border-gray-200 rounded-lg px-4 py-3 bg-gray-50 outline-none"
                            />
                        </div>

                        <button
                            className="bg-teal-600 hover:bg-teal-700 text-white px-6 py-3 rounded-lg font-medium"
                        >
                            Save Changes
                        </button>

                    </div>

                </div>

                {/* Notification Preferences */}
                <div className="bg-white rounded-xl shadow">

                    <div className="p-6 border-b">
                        <h2 className="text-xl font-semibold text-gray-900">
                            Notification Preferences
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Choose which notifications you want to receive.
                        </p>
                    </div>

                    <div className="p-6 space-y-6">

                        <div className="flex items-center justify-between gap-4">

                            <div>
                                <p className="font-medium text-gray-900">
                                    Bill Notifications
                                </p>

                                <p className="text-sm text-gray-500">
                                    Receive notifications when a new bill is generated.
                                </p>
                            </div>

                            <input
                                type="checkbox"
                                defaultChecked
                                className="w-5 h-5 accent-teal-600"
                            />

                        </div>

                        <div className="border-t pt-6 flex items-center justify-between gap-4">

                            <div>
                                <p className="font-medium text-gray-900">
                                    Payment Notifications
                                </p>

                                <p className="text-sm text-gray-500">
                                    Get notified when a payment is completed.
                                </p>
                            </div>

                            <input
                                type="checkbox"
                                defaultChecked
                                className="w-5 h-5 accent-teal-600"
                            />

                        </div>

                        <div className="border-t pt-6 flex items-center justify-between gap-4">

                            <div>
                                <p className="font-medium text-gray-900">
                                    Usage Alerts
                                </p>

                                <p className="text-sm text-gray-500">
                                    Receive alerts when your water usage is unusually high.
                                </p>
                            </div>

                            <input
                                type="checkbox"
                                defaultChecked
                                className="w-5 h-5 accent-teal-600"
                            />

                        </div>

                        <div className="border-t pt-6 flex items-center justify-between gap-4">

                            <div>
                                <p className="font-medium text-gray-900">
                                    Community Updates
                                </p>

                                <p className="text-sm text-gray-500">
                                    Receive important updates from your community.
                                </p>
                            </div>

                            <input
                                type="checkbox"
                                defaultChecked
                                className="w-5 h-5 accent-teal-600"
                            />

                        </div>

                    </div>

                </div>

                {/* Security */}
                <div className="bg-white rounded-xl shadow">

                    <div className="p-6 border-b">
                        <h2 className="text-xl font-semibold text-gray-900">
                            Security
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Manage your account security.
                        </p>
                    </div>

                    <div className="p-6 space-y-5">

                        <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">

                            <div>
                                <p className="font-medium text-gray-900">
                                    Password
                                </p>

                                <p className="text-sm text-gray-500">
                                    Last changed recently.
                                </p>
                            </div>

                            <button
                                className="border border-gray-300 hover:bg-gray-50 px-5 py-2.5 rounded-lg font-medium"
                            >
                                Change Password
                            </button>

                        </div>

                        <div className="border-t pt-5 flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">

                            <div>
                                <p className="font-medium text-gray-900">
                                    Two-Factor Authentication
                                </p>

                                <p className="text-sm text-gray-500">
                                    Add an extra layer of security to your account.
                                </p>
                            </div>

                            <span className="px-3 py-1 rounded-full bg-yellow-100 text-yellow-700 text-sm font-medium w-fit">
                                Not Enabled
                            </span>

                        </div>

                    </div>

                </div>

                {/* Danger Zone */}
                <div className="bg-white rounded-xl shadow border border-red-100">

                    <div className="p-6">

                        <h2 className="text-xl font-semibold text-red-600">
                            Danger Zone
                        </h2>

                        <p className="text-sm text-gray-500 mt-2">
                            These actions can affect your AquaFlow account.
                        </p>

                        <button
                            className="mt-5 border border-red-500 text-red-600 hover:bg-red-50 px-5 py-2.5 rounded-lg font-medium"
                        >
                            Deactivate Account
                        </button>

                    </div>

                </div>

            </div>
        </DashboardLayout>
    );
};

export default Settings;