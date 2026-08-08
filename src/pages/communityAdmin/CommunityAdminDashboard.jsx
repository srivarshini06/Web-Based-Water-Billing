import DashboardLayout from "../../layouts/DashboardLayout";

export default function CommunityAdminDashboard() {
    return (
        <DashboardLayout
            role="communityAdmin"
            user={{ name: "Rahul", email: "rahul@aquaflow.com" }}
        >
            <h1 className="text-3xl font-bold mb-6">
                Community Admin Dashboard
            </h1>

            <div className="grid grid-cols-3 gap-6">
                <div className="bg-white p-6 rounded-xl shadow">
                    <h2>Total Residents</h2>
                    <p className="text-4xl font-bold">1284</p>
                </div>

                <div className="bg-white p-6 rounded-xl shadow">
                    <h2>Total Usage</h2>
                    <p className="text-4xl font-bold">24,512 L</p>
                </div>

                <div className="bg-white p-6 rounded-xl shadow">
                    <h2>Pending Bills</h2>
                    <p className="text-4xl font-bold">124</p>
                </div>
            </div>
        </DashboardLayout>
    );
}