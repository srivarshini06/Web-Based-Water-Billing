// import React from "react";
//
// const AuthLayout = ({ children }) => {
//     return (
//         <div className="min-h-screen grid md:grid-cols-2">
//
//             {/* LEFT SIDE */}
//             <div className="relative hidden md:block">
//                 <img
//                     src="https://images.unsplash.com/photo-1506744038136-46273834b3fb"
//                     alt="water"
//                     className="w-full h-full object-cover"
//                 />
//
//                 <div className="absolute inset-0 bg-green-900/60"></div>
//
//                 <div className="absolute bottom-10 left-10 text-white max-w-md">
//                     <h2 className="text-2xl font-semibold">
//                         Empowering smarter water management.
//                     </h2>
//
//                     <p className="mt-2 text-sm text-gray-200">
//                         Manage usage, automate billing, and reduce waste.
//                     </p>
//                 </div>
//             </div>
//
//             {/* RIGHT SIDE */}
//             <div className="flex items-center justify-center bg-gray-50 px-6">
//                 {children}
//             </div>
//
//         </div>
//     );
// };
//
// export default AuthLayout;
import React from "react";
import { Link } from "react-router-dom";
import { Droplet } from "lucide-react";
import { motion } from "framer-motion";

const AuthLayout = ({ children, isRegister }) => {
    return (
        <div className="min-h-screen grid md:grid-cols-2 bg-white">
            {/* LEFT SIDE - Image & Text */}
            <motion.div
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ duration: 0.8 }}
                className="relative hidden md:flex flex-col justify-between p-12 bg-gradient-to-br from-teal-900 via-teal-800 to-cyan-900 overflow-hidden"
            >
                {/* Animated Background Elements */}
                <motion.div
                    animate={{
                        y: [0, -20, 0],
                        x: [0, 10, 0],
                    }}
                    transition={{
                        duration: 8,
                        repeat: Infinity,
                        ease: "easeInOut",
                    }}
                    className="absolute top-1/4 right-1/4 w-96 h-96 bg-white/10 rounded-full blur-3xl"
                />
                <motion.div
                    animate={{
                        y: [0, 20, 0],
                        x: [0, -10, 0],
                    }}
                    transition={{
                        duration: 10,
                        repeat: Infinity,
                        ease: "easeInOut",
                    }}
                    className="absolute -bottom-32 -left-32 w-96 h-96 bg-cyan-400/10 rounded-full blur-3xl"
                />

                <div className="relative space-y-8">
                    {/* Logo */}
                    <Link to="/" className="flex items-center gap-3 group">
                        <div className="w-11 h-11 bg-white rounded-lg flex items-center justify-center group-hover:scale-110 transition-transform">
                            <Droplet className="w-6 h-6 text-teal-600" />
                        </div>
                        <div>
                            <p className="font-bold text-lg text-white">AquaFlow</p>
                            <p className="text-xs text-teal-200">Pro</p>
                        </div>
                    </Link>

                    {/* Content */}
                    <div className="space-y-4">
                        <h2 className="text-3xl font-bold text-white leading-tight">
                            Empowering Smarter Water Management
                        </h2>
                        <p className="text-lg text-teal-100">
                            Join thousands of communities optimizing water usage, reducing costs, and managing infrastructure efficiently.
                        </p>
                    </div>

                    {/* Features */}
                    <div className="space-y-3">
                        {[
                            "Real-time water monitoring",
                            "Automated billing system",
                            "Community engagement tools",
                            "Advanced analytics & insights",
                        ].map((feature, idx) => (
                            <motion.div
                                key={idx}
                                initial={{ opacity: 0, x: -16 }}
                                animate={{ opacity: 1, x: 0 }}
                                transition={{ delay: idx * 0.1 }}
                                className="flex items-center gap-3"
                            >
                                <div className="flex-shrink-0 w-5 h-5 bg-teal-400 rounded-full flex items-center justify-center">
                                    <svg
                                        className="w-3 h-3 text-teal-900"
                                        fill="currentColor"
                                        viewBox="0 0 20 20"
                                    >
                                        <path
                                            fillRule="evenodd"
                                            d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z"
                                            clipRule="evenodd"
                                        />
                                    </svg>
                                </div>
                                <span className="text-teal-100">{feature}</span>
                            </motion.div>
                        ))}
                    </div>
                </div>

                {/* Stats */}
                <motion.div
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ delay: 0.4 }}
                    className="relative grid grid-cols-3 gap-6 pt-8 border-t border-white/10"
                >
                    {[
                        { value: "10K+", label: "Active Users" },
                        { value: "2,500+", label: "Communities" },
                        { value: "99.9%", label: "Uptime" },
                    ].map((stat, idx) => (
                        <div key={idx} className="text-center">
                            <p className="text-2xl font-bold text-teal-300">{stat.value}</p>
                            <p className="text-xs text-teal-200 mt-1">{stat.label}</p>
                        </div>
                    ))}
                </motion.div>
            </motion.div>

            {/* RIGHT SIDE - Form */}
            <div className="flex items-center justify-center px-6 py-12 md:py-0 bg-gray-50">
                <motion.div
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.6, delay: 0.2 }}
                    className="w-full"
                >
                    {children}
                </motion.div>
            </div>
        </div>
    );
};

export default AuthLayout;