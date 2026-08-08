import DashboardLayout from "../../layouts/DashboardLayout";

export default function SuperAdminDashboard() {
    return (
        <DashboardLayout
            role="superAdmin"
            user={{ name: "System Admin", email: "admin@aquaflow.com" }}
        >
            <h1 className="text-3xl font-bold mb-6">
                Super Admin Dashboard
            </h1>

            <div className="grid grid-cols-3 gap-6">
                <div className="bg-white p-6 rounded-xl shadow">
                    <h2>Total Users</h2>
                    <p className="text-4xl font-bold">12,942</p>
                </div>

                <div className="bg-white p-6 rounded-xl shadow">
                    <h2>Communities</h2>
                    <p className="text-4xl font-bold">142</p>
                </div>

                <div className="bg-white p-6 rounded-xl shadow">
                    <h2>Active Users</h2>
                    <p className="text-4xl font-bold">11,982</p>
                </div>
            </div>
        </DashboardLayout>
    );
}