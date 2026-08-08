import React from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const Profile = () => {
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
                        My Profile
                    </h1>

                    <p className="text-gray-500 mt-2">
                        View and manage your personal information.
                    </p>
                </div>

                {/* Profile Overview */}
                <div className="bg-white rounded-xl shadow p-6">

                    <div className="flex flex-col md:flex-row md:items-center gap-6">

                        {/* Avatar */}
                        <div className="w-24 h-24 rounded-full bg-teal-100 text-teal-700 flex items-center justify-center text-3xl font-bold">
                            P
                        </div>

                        {/* Basic Info */}
                        <div className="flex-1">
                            <h2 className="text-2xl font-bold text-gray-900">
                                Priya Nair
                            </h2>

                            <p className="text-gray-500 mt-1">
                                Resident
                            </p>

                            <p className="text-gray-500 text-sm mt-2">
                                Member since January 2025
                            </p>
                        </div>

                        <button
                            className="border border-teal-600 text-teal-600 hover:bg-teal-50 px-5 py-2.5 rounded-lg font-medium"
                        >
                            Edit Profile
                        </button>

                    </div>

                </div>

                {/* Personal Information */}
                <div className="bg-white rounded-xl shadow">

                    <div className="p-6 border-b">
                        <h2 className="text-xl font-semibold text-gray-900">
                            Personal Information
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Your registered personal details.
                        </p>
                    </div>

                    <div className="p-6 grid grid-cols-1 md:grid-cols-2 gap-6">

                        <div>
                            <p className="text-sm text-gray-500">
                                Full Name
                            </p>

                            <p className="font-medium text-gray-900 mt-1">
                                Priya Nair
                            </p>
                        </div>

                        <div>
                            <p className="text-sm text-gray-500">
                                Email Address
                            </p>

                            <p className="font-medium text-gray-900 mt-1">
                                priya@aquaflow.com
                            </p>
                        </div>

                        <div>
                            <p className="text-sm text-gray-500">
                                Phone Number
                            </p>

                            <p className="font-medium text-gray-900 mt-1">
                                +91 98765 43210
                            </p>
                        </div>

                        <div>
                            <p className="text-sm text-gray-500">
                                Date of Birth
                            </p>

                            <p className="font-medium text-gray-900 mt-1">
                                12 March 1999
                            </p>
                        </div>

                    </div>

                </div>

                {/* Property Information */}
                <div className="bg-white rounded-xl shadow">

                    <div className="p-6 border-b">
                        <h2 className="text-xl font-semibold text-gray-900">
                            Property Information
                        </h2>

                        <p className="text-sm text-gray-500 mt-1">
                            Information about your registered property.
                        </p>
                    </div>

                    <div className="p-6 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">

                        <div>
                            <p className="text-sm text-gray-500">
                                Community
                            </p>

                            <p className="font-medium text-gray-900 mt-1">
                                Palm Residency
                            </p>
                        </div>

                        <div>
                            <p className="text-sm text-gray-500">
                                Apartment / Unit
                            </p>

                            <p className="font-medium text-gray-900 mt-1">
                                A-204
                            </p>
                        </div>

                        <div>
                            <p className="text-sm text-gray-500">
                                Meter ID
                            </p>

                            <p className="font-medium text-gray-900 mt-1">
                                MTR-88213
                            </p>
                        </div>

                        <div>
                            <p className="text-sm text-gray-500">
                                Property Type
                            </p>

                            <p className="font-medium text-gray-900 mt-1">
                                Apartment
                            </p>
                        </div>

                        <div>
                            <p className="text-sm text-gray-500">
                                Occupancy
                            </p>

                            <p className="font-medium text-gray-900 mt-1">
                                Owner Occupied
                            </p>
                        </div>

                        <div>
                            <p className="text-sm text-gray-500">
                                Meter Status
                            </p>

                            <p className="font-medium text-green-600 mt-1">
                                Active
                            </p>
                        </div>

                    </div>

                </div>

                {/* Account Information */}
                <div className="bg-white rounded-xl shadow">

                    <div className="p-6 border-b">
                        <h2 className="text-xl font-semibold text-gray-900">
                            Account Information
                        </h2>
                    </div>

                    <div className="p-6 space-y-5">

                        <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-3">
                            <div>
                                <p className="font-medium text-gray-900">
                                    Account Status
                                </p>

                                <p className="text-sm text-gray-500">
                                    Your AquaFlow account is currently active.
                                </p>
                            </div>

                            <span className="w-fit px-3 py-1 rounded-full bg-green-100 text-green-700 text-sm font-medium">
                                Active
                            </span>
                        </div>

                        <div className="border-t pt-5 flex flex-col sm:flex-row sm:items-center sm:justify-between gap-3">
                            <div>
                                <p className="font-medium text-gray-900">
                                    Email Verification
                                </p>

                                <p className="text-sm text-gray-500">
                                    Your email address has been verified.
                                </p>
                            </div>

                            <span className="w-fit px-3 py-1 rounded-full bg-green-100 text-green-700 text-sm font-medium">
                                Verified
                            </span>
                        </div>

                    </div>

                </div>

            </div>
        </DashboardLayout>
    );
};

export default Profile;