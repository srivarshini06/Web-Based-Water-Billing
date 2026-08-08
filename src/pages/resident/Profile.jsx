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

                <div>
                    <h1 className="text-3xl font-bold text-gray-800">
                        My Profile
                    </h1>

                    <p className="text-gray-500 mt-2">
                        View your personal and property information.
                    </p>
                </div>

                {/* Profile Header */}

                <div className="bg-white rounded-xl shadow-md p-8 flex flex-col md:flex-row items-center gap-8">

                    <div className="w-28 h-28 rounded-full bg-teal-600 text-white flex items-center justify-center text-5xl font-bold">
                        P
                    </div>

                    <div className="flex-1">

                        <h2 className="text-3xl font-bold">
                            Priya Nair
                        </h2>

                        <p className="text-gray-500 mt-2">
                            Resident
                        </p>

                        <p className="mt-2">
                            priya@aquaflow.com
                        </p>

                    </div>

                    <button className="bg-teal-600 text-white px-6 py-3 rounded-lg hover:bg-teal-700">
                        Edit Profile
                    </button>

                </div>

                {/* Personal Information */}

                <div className="grid lg:grid-cols-2 gap-6">

                    <div className="bg-white rounded-xl shadow-md p-6">

                        <h2 className="text-xl font-semibold mb-6">
                            Personal Details
                        </h2>

                        <div className="space-y-5">

                            <div>
                                <p className="text-gray-500 text-sm">
                                    Full Name
                                </p>

                                <h3 className="font-semibold">
                                    Priya Nair
                                </h3>
                            </div>

                            <div>
                                <p className="text-gray-500 text-sm">
                                    Email Address
                                </p>

                                <h3 className="font-semibold">
                                    priya@aquaflow.com
                                </h3>
                            </div>

                            <div>
                                <p className="text-gray-500 text-sm">
                                    Phone Number
                                </p>

                                <h3 className="font-semibold">
                                    +91 98765 43210
                                </h3>
                            </div>

                            <div>
                                <p className="text-gray-500 text-sm">
                                    Resident Since
                                </p>

                                <h3 className="font-semibold">
                                    January 2024
                                </h3>
                            </div>

                        </div>

                    </div>

                    {/* Property Details */}

                    <div className="bg-white rounded-xl shadow-md p-6">

                        <h2 className="text-xl font-semibold mb-6">
                            Property Details
                        </h2>

                        <div className="space-y-5">

                            <div>
                                <p className="text-gray-500 text-sm">
                                    Community
                                </p>

                                <h3 className="font-semibold">
                                    Palm Residency
                                </h3>
                            </div>

                            <div>
                                <p className="text-gray-500 text-sm">
                                    Apartment
                                </p>

                                <h3 className="font-semibold">
                                    Block A - 204
                                </h3>
                            </div>

                            <div>
                                <p className="text-gray-500 text-sm">
                                    Meter ID
                                </p>

                                <h3 className="font-semibold">
                                    MTR-88213
                                </h3>
                            </div>

                            <div>
                                <p className="text-gray-500 text-sm">
                                    Occupancy
                                </p>

                                <h3 className="font-semibold">
                                    4 Members
                                </h3>
                            </div>

                        </div>

                    </div>

                </div>

                {/* Emergency Contact */}

                <div className="bg-white rounded-xl shadow-md p-6">

                    <h2 className="text-xl font-semibold mb-6">
                        Emergency Contact
                    </h2>

                    <div className="grid md:grid-cols-3 gap-6">

                        <div>

                            <p className="text-gray-500 text-sm">
                                Contact Person
                            </p>

                            <h3 className="font-semibold">
                                Rajesh Nair
                            </h3>

                        </div>

                        <div>

                            <p className="text-gray-500 text-sm">
                                Relationship
                            </p>

                            <h3 className="font-semibold">
                                Spouse
                            </h3>

                        </div>

                        <div>

                            <p className="text-gray-500 text-sm">
                                Mobile Number
                            </p>

                            <h3 className="font-semibold">
                                +91 99887 66554
                            </h3>

                        </div>

                    </div>

                </div>

            </div>

        </DashboardLayout>
    );
};

export default Profile;