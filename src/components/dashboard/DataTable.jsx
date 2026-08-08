import React from "react";

const DataTable = ({ title, columns = [], data = [] }) => {
    return (
        <div className="bg-white rounded-xl shadow p-6">
            <h2 className="text-lg font-semibold mb-4">{title}</h2>

            <div className="overflow-x-auto">
                <table className="w-full text-sm">
                    <thead>
                    <tr className="border-b bg-gray-50">
                        {columns.map((col) => (
                            <th
                                key={col}
                                className="text-left px-4 py-3 font-semibold text-gray-600"
                            >
                                {col}
                            </th>
                        ))}
                    </tr>
                    </thead>

                    <tbody>
                    {data.length > 0 ? (
                        data.map((row, index) => (
                            <tr
                                key={index}
                                className="border-b hover:bg-gray-50"
                            >
                                {columns.map((col) => (
                                    <td key={col} className="px-4 py-3">
                                        {row[col]}
                                    </td>
                                ))}
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td
                                colSpan={columns.length}
                                className="text-center py-6 text-gray-400"
                            >
                                No data available
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default DataTable;