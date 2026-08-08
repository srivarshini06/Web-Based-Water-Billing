import React from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const Settings = () => {
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
                        Community Settings
                    </h1>
                    <p className="text-gray-500 mt-1">
                        Configure your community information and billing preferences.
                    </p>
                </div>

                {/* Community Information */}
                <div className="bg-white rounded-2xl border border-gray-100 shadow-sm p-6">
                    <h2 className="text-lg font-semibold mb-6">
                        Community Information
                    </h2>

                    <div className="grid md:grid-cols-2 gap-5">
                        <div>
                            <label className="block text-sm text-gray-500 mb-2">
                                Community Name
                            </label>
                            <input
                                defaultValue="Palm Residency"
                                className="w-full border border-gray-300 rounded-lg px-4 py-3 focus:ring-2 focus:ring-teal-500 outline-none"
                            />
                        </div>

                        <div>
                            <label className="block text-sm text-gray-500 mb-2">
                                Total Units
                            </label>
                            <input
                                defaultValue="1284"
                                className="w-full border border-gray-300 rounded-lg px-4 py-3 focus:ring-2 focus:ring-teal-500 outline-none"
                            />
                        </div>

                        <div>
                            <label className="block text-sm text-gray-500 mb-2">
                                Contact Email
                            </label>
                            <input
                                defaultValue="rahul@aquaflow.com"
                                className="w-full border border-gray-300 rounded-lg px-4 py-3 focus:ring-2 focus:ring-teal-500 outline-none"
                            />
                        </div>

                        <div>
                            <label className="block text-sm text-gray-500 mb-2">
                                Contact Number
                            </label>
                            <input
                                defaultValue="+91 9876543210"
                                className="w-full border border-gray-300 rounded-lg px-4 py-3 focus:ring-2 focus:ring-teal-500 outline-none"
                            />
                        </div>
                    </div>

                    <button className="mt-6 bg-teal-600 hover:bg-teal-700 text-white px-6 py-3 rounded-lg font-medium">
                        Save Changes
                    </button>
                </div>

                {/* Billing Settings */}
                <div className="bg-white rounded-2xl border border-gray-100 shadow-sm p-6">
                    <h2 className="text-lg font-semibold mb-5">
                        Billing Settings
                    </h2>

                    <div className="space-y-4">
                        <label className="flex justify-between items-center">
                            <span>Email Bill Notifications</span>
                            <input type="checkbox" defaultChecked />
                        </label>

                        <label className="flex justify-between items-center">
                            <span>Automatic Bill Generation</span>
                            <input type="checkbox" defaultChecked />
                        </label>

                        <label className="flex justify-between items-center">
                            <span>Late Payment Reminder</span>
                            <input type="checkbox" defaultChecked />
                        </label>
                    </div>
                </div>
            </div>
        </DashboardLayout>
    );
};

export default Settings;