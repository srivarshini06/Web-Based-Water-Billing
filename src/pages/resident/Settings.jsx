// import React from "react";
// import DashboardLayout from "../../layouts/DashboardLayout";
//
// const Settings = () => {
//     return (
//         <DashboardLayout
//             role="resident"
//             user={{
//                 name: "Priya Nair",
//                 email: "priya@aquaflow.com",
//             }}
//         >
//             <div className="space-y-6">
//                 <div>
//                     <h1 className="text-2xl font-bold text-gray-900">
//                         Settings
//                     </h1>
//                     <p className="text-gray-500 mt-1">
//                         Manage your profile and account preferences.
//                     </p>
//                 </div>
//
//                 <div className="bg-white rounded-2xl border border-gray-100 shadow-sm p-6">
//                     <h2 className="text-lg font-semibold mb-6">
//                         Profile Information
//                     </h2>
//
//                     <div className="grid md:grid-cols-2 gap-5">
//                         <div>
//                             <label className="block text-sm text-gray-500 mb-2">
//                                 Full Name
//                             </label>
//                             <input
//                                 type="text"
//                                 defaultValue="Priya Nair"
//                                 className="w-full border border-gray-300 rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-teal-500"
//                             />
//                         </div>
//
//                         <div>
//                             <label className="block text-sm text-gray-500 mb-2">
//                                 Email
//                             </label>
//                             <input
//                                 type="email"
//                                 defaultValue="priya@aquaflow.com"
//                                 className="w-full border border-gray-300 rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-teal-500"
//                             />
//                         </div>
//
//                         <div>
//                             <label className="block text-sm text-gray-500 mb-2">
//                                 Phone Number
//                             </label>
//                             <input
//                                 type="text"
//                                 defaultValue="+91 9876543210"
//                                 className="w-full border border-gray-300 rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-teal-500"
//                             />
//                         </div>
//
//                         <div>
//                             <label className="block text-sm text-gray-500 mb-2">
//                                 Apartment
//                             </label>
//                             <input
//                                 type="text"
//                                 defaultValue="A-204"
//                                 className="w-full border border-gray-300 rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-teal-500"
//                             />
//                         </div>
//                     </div>
//
//                     <button className="mt-8 bg-teal-600 hover:bg-teal-700 text-white px-6 py-3 rounded-lg font-medium transition">
//                         Save Changes
//                     </button>
//                 </div>
//
//                 <div className="bg-white rounded-2xl border border-gray-100 shadow-sm p-6">
//                     <h2 className="text-lg font-semibold mb-4">
//                         Notification Preferences
//                     </h2>
//
//                     <div className="space-y-4">
//                         <label className="flex items-center justify-between">
//                             <span>Email Notifications</span>
//                             <input type="checkbox" defaultChecked />
//                         </label>
//
//                         <label className="flex items-center justify-between">
//                             <span>SMS Alerts</span>
//                             <input type="checkbox" defaultChecked />
//                         </label>
//
//                         <label className="flex items-center justify-between">
//                             <span>Bill Reminders</span>
//                             <input type="checkbox" defaultChecked />
//                         </label>
//                     </div>
//                 </div>
//             </div>
//         </DashboardLayout>
//     );
// };
//
// export default Settings;
import React, { useState } from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const Settings = () => {
    const [notifications, setNotifications] = useState(true);
    const [emailAlerts, setEmailAlerts] = useState(true);
    const [darkMode, setDarkMode] = useState(false);

    return (
        <DashboardLayout
            role="resident"
            user={{
                name: "Priya Nair",
                email: "priya@aquaflow.com",
            }}
        >
            <div className="space-y-8">

                <div>
                    <h1 className="text-3xl font-bold text-gray-800">
                        Settings
                    </h1>

                    <p className="text-gray-500 mt-2">
                        Manage your AquaFlow account preferences.
                    </p>
                </div>

                {/* Account */}

                <div className="bg-white rounded-xl shadow-md p-6">

                    <h2 className="text-xl font-semibold mb-6">
                        Account Information
                    </h2>

                    <div className="grid md:grid-cols-2 gap-6">

                        <div>
                            <label className="block text-sm text-gray-600 mb-2">
                                Full Name
                            </label>

                            <input
                                type="text"
                                defaultValue="Priya Nair"
                                className="w-full border rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-teal-500"
                            />
                        </div>

                        <div>
                            <label className="block text-sm text-gray-600 mb-2">
                                Email
                            </label>

                            <input
                                type="email"
                                defaultValue="priya@aquaflow.com"
                                className="w-full border rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-teal-500"
                            />
                        </div>

                        <div>
                            <label className="block text-sm text-gray-600 mb-2">
                                Phone
                            </label>

                            <input
                                type="text"
                                defaultValue="+91 98765 43210"
                                className="w-full border rounded-lg px-4 py-3 focus:outline-none focus:ring-2 focus:ring-teal-500"
                            />
                        </div>

                        <div>
                            <label className="block text-sm text-gray-600 mb-2">
                                Apartment
                            </label>

                            <input
                                type="text"
                                defaultValue="A-204"
                                className="w-full border rounded-lg px-4 py-3 bg-gray-100"
                                disabled
                            />
                        </div>

                    </div>

                    <button className="mt-6 bg-teal-600 hover:bg-teal-700 text-white px-6 py-3 rounded-lg">
                        Save Changes
                    </button>

                </div>

                {/* Preferences */}

                <div className="bg-white rounded-xl shadow-md p-6">

                    <h2 className="text-xl font-semibold mb-6">
                        Preferences
                    </h2>

                    <div className="space-y-5">

                        <div className="flex justify-between items-center">

                            <div>
                                <h3 className="font-semibold">
                                    Push Notifications
                                </h3>

                                <p className="text-sm text-gray-500">
                                    Receive alerts about bills and usage.
                                </p>
                            </div>

                            <input
                                type="checkbox"
                                checked={notifications}
                                onChange={() =>
                                    setNotifications(!notifications)
                                }
                                className="w-5 h-5"
                            />

                        </div>

                        <div className="flex justify-between items-center">

                            <div>
                                <h3 className="font-semibold">
                                    Email Notifications
                                </h3>

                                <p className="text-sm text-gray-500">
                                    Receive monthly reports by email.
                                </p>
                            </div>

                            <input
                                type="checkbox"
                                checked={emailAlerts}
                                onChange={() =>
                                    setEmailAlerts(!emailAlerts)
                                }
                                className="w-5 h-5"
                            />

                        </div>

                        <div className="flex justify-between items-center">

                            <div>
                                <h3 className="font-semibold">
                                    Dark Mode
                                </h3>

                                <p className="text-sm text-gray-500">
                                    Enable dark appearance.
                                </p>
                            </div>

                            <input
                                type="checkbox"
                                checked={darkMode}
                                onChange={() =>
                                    setDarkMode(!darkMode)
                                }
                                className="w-5 h-5"
                            />

                        </div>

                    </div>

                </div>

                {/* Security */}

                <div className="bg-white rounded-xl shadow-md p-6">

                    <h2 className="text-xl font-semibold mb-6">
                        Security
                    </h2>

                    <div className="space-y-4">

                        <button className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-3 rounded-lg">
                            Change Password
                        </button>

                        <button className="bg-red-600 hover:bg-red-700 text-white px-6 py-3 rounded-lg ml-4">
                            Delete Account
                        </button>

                    </div>

                </div>

            </div>
        </DashboardLayout>
    );
};

export default Settings;