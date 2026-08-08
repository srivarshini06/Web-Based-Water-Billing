import React from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const Settings = () => {
    return (
        <DashboardLayout
            role="superAdmin"
            user={{
                name: "Admin",
                email: "admin@aquaflow.com",
            }}
        >
            <div className="space-y-6">

                <div>
                    <h1 className="text-2xl font-bold">
                        System Settings
                    </h1>
                    <p className="text-gray-500">
                        Configure global AquaFlow settings.
                    </p>
                </div>

                <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-6">

                    <h2 className="text-lg font-semibold mb-6">
                        Administrator Profile
                    </h2>

                    <div className="grid md:grid-cols-2 gap-5">

                        <input
                            defaultValue="Super Administrator"
                            className="border rounded-lg px-4 py-3"
                        />

                        <input
                            defaultValue="admin@aquaflow.com"
                            className="border rounded-lg px-4 py-3"
                        />

                        <input
                            defaultValue="+91 9876543210"
                            className="border rounded-lg px-4 py-3"
                        />

                        <input
                            defaultValue="India"
                            className="border rounded-lg px-4 py-3"
                        />

                    </div>

                    <button className="mt-6 bg-teal-600 text-white px-6 py-3 rounded-lg">
                        Save Changes
                    </button>

                </div>

            </div>

        </DashboardLayout>
    );
};

export default Settings;