// import React from "react";
// import DashboardLayout from "../../layouts/DashboardLayout";
// import MetricCard from "../../components/dashboard/MetricCard";
// import ChartCard from "../../components/dashboard/ChartCard";
//
// const usageData = [
//     { name: "Mon", value: 1180 },
//     { name: "Tue", value: 1320 },
//     { name: "Wed", value: 1090 },
//     { name: "Thu", value: 1450 },
//     { name: "Fri", value: 1210 },
//     { name: "Sat", value: 980 },
//     { name: "Sun", value: 1140 },
// ];
//
// const monthlyUsage = [
//     { name: "Feb", value: 12500 },
//     { name: "Mar", value: 13200 },
//     { name: "Apr", value: 11800 },
//     { name: "May", value: 14100 },
//     { name: "Jun", value: 13500 },
//     { name: "Jul", value: 12800 },
// ];
//
// const Usage = () => {
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
//                         Water Usage
//                     </h1>
//                     <p className="text-gray-500 mt-1">
//                         Monitor your daily and monthly water consumption.
//                     </p>
//                 </div>
//
//                 <div className="grid md:grid-cols-3 gap-5">
//                     <MetricCard
//                         label="Today's Usage"
//                         value="1,140 L"
//                         change={2.4}
//                     />
//
//                     <MetricCard
//                         label="This Month"
//                         value="12,800 L"
//                         change={-6.3}
//                     />
//
//                     <MetricCard
//                         label="Average Daily"
//                         value="1,220 L"
//                         change={1.8}
//                     />
//                 </div>
//
//                 <div className="grid lg:grid-cols-2 gap-6">
//                     <ChartCard
//                         title="Daily Consumption"
//                         subtitle="Last 7 Days"
//                         type="bar"
//                         data={usageData}
//                         color="#14B8A6"
//                     />
//
//                     <ChartCard
//                         title="Monthly Consumption"
//                         subtitle="Last 6 Months"
//                         type="line"
//                         data={monthlyUsage}
//                         color="#0F766E"
//                     />
//                 </div>
//
//                 <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-6">
//                     <h2 className="text-lg font-semibold text-gray-900 mb-4">
//                         Usage Summary
//                     </h2>
//
//                     <div className="grid sm:grid-cols-2 lg:grid-cols-4 gap-5">
//                         <div>
//                             <p className="text-gray-500 text-sm">Highest Day</p>
//                             <p className="text-xl font-semibold text-gray-900">
//                                 1,450 L
//                             </p>
//                         </div>
//
//                         <div>
//                             <p className="text-gray-500 text-sm">Lowest Day</p>
//                             <p className="text-xl font-semibold text-gray-900">
//                                 980 L
//                             </p>
//                         </div>
//
//                         <div>
//                             <p className="text-gray-500 text-sm">Average</p>
//                             <p className="text-xl font-semibold text-gray-900">
//                                 1,220 L
//                             </p>
//                         </div>
//
//                         <div>
//                             <p className="text-gray-500 text-sm">Efficiency</p>
//                             <p className="text-xl font-semibold text-emerald-600">
//                                 Excellent
//                             </p>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         </DashboardLayout>
//     );
// };
//
// export default Usage;
import React from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const weeklyUsage = [
    { day: "Mon", value: 210 },
    { day: "Tue", value: 260 },
    { day: "Wed", value: 180 },
    { day: "Thu", value: 310 },
    { day: "Fri", value: 240 },
    { day: "Sat", value: 280 },
    { day: "Sun", value: 190 },
];

const monthlyUsage = [
    { month: "Feb", usage: "11,500 L" },
    { month: "Mar", usage: "12,300 L" },
    { month: "Apr", usage: "13,000 L" },
    { month: "May", usage: "14,500 L" },
    { month: "Jun", usage: "14,000 L" },
    { month: "Jul", usage: "14,200 L" },
];

const Usage = () => {
    return (
        <DashboardLayout
            role="resident"
            user={{
                name: "Priya Nair",
                email: "priya@aquaflow.com",
            }}
        >
            <h1 className="text-3xl font-bold mb-8">
                Water Usage
            </h1>

            <div className="grid lg:grid-cols-3 gap-6">

                <div className="lg:col-span-2 bg-white rounded-xl shadow p-6">

                    <h2 className="text-xl font-semibold mb-6">
                        Weekly Consumption
                    </h2>

                    <div className="flex items-end justify-between h-72">

                        {weeklyUsage.map((item) => (

                            <div
                                key={item.day}
                                className="flex flex-col items-center"
                            >
                                <div
                                    className="bg-teal-500 w-10 rounded-t"
                                    style={{
                                        height: `${item.value}px`,
                                    }}
                                />

                                <span className="mt-3 text-sm">
                                    {item.day}
                                </span>

                            </div>

                        ))}

                    </div>

                </div>

                <div className="bg-white rounded-xl shadow p-6">

                    <h2 className="text-xl font-semibold mb-6">
                        Summary
                    </h2>

                    <div className="space-y-5">

                        <div>
                            <p className="text-gray-500">
                                Total Usage
                            </p>

                            <h3 className="text-3xl font-bold text-teal-600">
                                14,200 L
                            </h3>
                        </div>

                        <div>
                            <p className="text-gray-500">
                                Average Daily
                            </p>

                            <h3 className="text-2xl font-bold">
                                470 L
                            </h3>
                        </div>

                        <div>
                            <p className="text-gray-500">
                                Efficiency
                            </p>

                            <h3 className="text-2xl font-bold text-green-600">
                                Excellent
                            </h3>
                        </div>

                    </div>

                </div>

            </div>

            <div className="bg-white rounded-xl shadow mt-8">

                <div className="p-6 border-b">
                    <h2 className="text-xl font-semibold">
                        Monthly Usage
                    </h2>
                </div>

                <table className="w-full">

                    <thead className="bg-gray-50">

                    <tr>
                        <th className="text-left p-4">Month</th>
                        <th className="text-left">Consumption</th>
                    </tr>

                    </thead>

                    <tbody>

                    {monthlyUsage.map((item) => (

                        <tr
                            key={item.month}
                            className="border-t hover:bg-gray-50"
                        >
                            <td className="p-4">{item.month}</td>
                            <td>{item.usage}</td>
                        </tr>

                    ))}

                    </tbody>

                </table>

            </div>

        </DashboardLayout>
    );
};

export default Usage;