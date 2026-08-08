// import React from "react";
// import DashboardLayout from "../../layouts/DashboardLayout";
// import DataTable from "../../components/dashboard/DataTable";
//
// const bills = [
//     {
//         id: 1,
//         month: "July 2026",
//         amount: "₹842",
//         due: "05 Aug 2026",
//         status: "Paid",
//     },
//     {
//         id: 2,
//         month: "June 2026",
//         amount: "₹910",
//         due: "05 Jul 2026",
//         status: "Paid",
//     },
//     {
//         id: 3,
//         month: "May 2026",
//         amount: "₹975",
//         due: "05 Jun 2026",
//         status: "Overdue",
//     },
//     {
//         id: 4,
//         month: "April 2026",
//         amount: "₹690",
//         due: "05 May 2026",
//         status: "Paid",
//     },
// ];
//
// const StatusBadge = ({ status }) => (
//     <span
//         className={`px-3 py-1 rounded-full text-xs font-medium ${
//             status === "Paid"
//                 ? "bg-green-100 text-green-700"
//                 : "bg-red-100 text-red-700"
//         }`}
//     >
//     {status}
//   </span>
// );
//
// const columns = [
//     { key: "month", label: "Billing Month" },
//     { key: "amount", label: "Amount" },
//     { key: "due", label: "Due Date" },
//     {
//         key: "status",
//         label: "Status",
//         render: (row) => <StatusBadge status={row.status} />,
//     },
// ];
//
// const Bills = () => {
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
//                         Bills
//                     </h1>
//                     <p className="text-gray-500 mt-1">
//                         View and manage your billing history.
//                     </p>
//                 </div>
//
//                 <div className="bg-[#0B1420] rounded-2xl p-6 text-white">
//                     <p className="text-gray-300 text-sm">
//                         Outstanding Balance
//                     </p>
//
//                     <h2 className="text-4xl font-bold mt-2">
//                         ₹842.50
//                     </h2>
//
//                     <p className="text-gray-400 mt-1">
//                         Due in 5 days
//                     </p>
//
//                     <button className="mt-5 bg-teal-500 hover:bg-teal-400 px-6 py-3 rounded-lg font-medium">
//                         Pay Now
//                     </button>
//                 </div>
//
//                 <div>
//                     <h2 className="text-lg font-semibold mb-4">
//                         Billing History
//                     </h2>
//
//                     <DataTable
//                         columns={columns}
//                         rows={bills}
//                         pageSize={5}
//                     />
//                 </div>
//             </div>
//         </DashboardLayout>
//     );
// };
//
// export default Bills;
import React from "react";
import DashboardLayout from "../../layouts/DashboardLayout";

const bills = [
    {
        month: "July 2026",
        amount: "₹842",
        due: "05 Aug 2026",
        status: "Pending",
    },
    {
        month: "June 2026",
        amount: "₹790",
        due: "05 Jul 2026",
        status: "Paid",
    },
    {
        month: "May 2026",
        amount: "₹915",
        due: "05 Jun 2026",
        status: "Paid",
    },
    {
        month: "April 2026",
        amount: "₹870",
        due: "05 May 2026",
        status: "Paid",
    },
    {
        month: "March 2026",
        amount: "₹815",
        due: "05 Apr 2026",
        status: "Paid",
    },
];

const Bills = () => {
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
                        Water Bills
                    </h1>

                    <p className="text-gray-500 mt-2">
                        View and manage your billing history.
                    </p>
                </div>

                {/* Current Bill */}

                <div className="bg-gradient-to-r from-teal-600 to-teal-700 text-white rounded-xl p-8 shadow-lg">

                    <p className="text-teal-100">
                        Current Outstanding Bill
                    </p>

                    <h2 className="text-5xl font-bold mt-3">
                        ₹842
                    </h2>

                    <div className="mt-6 flex flex-wrap gap-8">

                        <div>
                            <p className="text-sm text-teal-100">
                                Billing Month
                            </p>

                            <h3 className="text-xl font-semibold">
                                July 2026
                            </h3>
                        </div>

                        <div>
                            <p className="text-sm text-teal-100">
                                Due Date
                            </p>

                            <h3 className="text-xl font-semibold">
                                05 August 2026
                            </h3>
                        </div>

                    </div>

                    <div className="mt-8 flex gap-4">

                        <button className="bg-white text-teal-700 font-semibold px-6 py-3 rounded-lg hover:bg-gray-100">
                            Pay Now
                        </button>

                        <button className="border border-white px-6 py-3 rounded-lg hover:bg-white/10">
                            Download Invoice
                        </button>

                    </div>

                </div>

                {/* Bill History */}

                <div className="bg-white rounded-xl shadow-md overflow-hidden">

                    <div className="p-6 border-b">
                        <h2 className="text-2xl font-semibold">
                            Payment History
                        </h2>
                    </div>

                    <table className="w-full">

                        <thead className="bg-gray-50">

                        <tr>

                            <th className="text-left p-4">Month</th>
                            <th className="text-left">Amount</th>
                            <th className="text-left">Due Date</th>
                            <th className="text-left">Status</th>
                            <th className="text-left">Action</th>

                        </tr>

                        </thead>

                        <tbody>

                        {bills.map((bill) => (

                            <tr
                                key={bill.month}
                                className="border-t hover:bg-gray-50"
                            >

                                <td className="p-4 font-medium">
                                    {bill.month}
                                </td>

                                <td>{bill.amount}</td>

                                <td>{bill.due}</td>

                                <td>

                                    <span
                                        className={`px-3 py-1 rounded-full text-sm font-medium ${
                                            bill.status === "Paid"
                                                ? "bg-green-100 text-green-700"
                                                : "bg-red-100 text-red-700"
                                        }`}
                                    >
                                        {bill.status}
                                    </span>

                                </td>

                                <td>

                                    <button className="text-teal-600 hover:text-teal-800 font-medium">
                                        View
                                    </button>

                                </td>

                            </tr>

                        ))}

                        </tbody>

                    </table>

                </div>

            </div>

        </DashboardLayout>
    );
};

export default Bills;