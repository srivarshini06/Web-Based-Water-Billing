// import React from "react";
// import { useNavigate } from "react-router-dom";
// import { ArrowRight, Play } from "lucide-react";
// import { motion } from "framer-motion";
//
// const Hero = () => {
//     const navigate = useNavigate();
//
//     const containerVariants = {
//         hidden: { opacity: 0 },
//         visible: {
//             opacity: 1,
//             transition: {
//                 staggerChildren: 0.2,
//                 delayChildren: 0.2,
//             },
//         },
//     };
//
//     const itemVariants = {
//         hidden: { opacity: 0, y: 20 },
//         visible: {
//             opacity: 1,
//             y: 0,
//             transition: { duration: 0.8, ease: "easeOut" },
//         },
//     };
//
//     return (
//         <section className="relative px-6 py-20 md:py-28 bg-white overflow-hidden">
//             {/* Subtle Background Elements */}
//             <div className="absolute top-0 right-0 w-96 h-96 bg-teal-50 rounded-full blur-3xl opacity-40 pointer-events-none" />
//             <div className="absolute -bottom-32 left-1/3 w-96 h-96 bg-cyan-50 rounded-full blur-3xl opacity-30 pointer-events-none" />
//
//             <div className="relative max-w-6xl mx-auto">
//                 <motion.div
//                     variants={containerVariants}
//                     initial="hidden"
//                     animate="visible"
//                     className="grid md:grid-cols-2 gap-12 md:gap-16 items-center"
//                 >
//                     {/* Left Content */}
//                     <motion.div variants={itemVariants} className="space-y-6">
//                         {/* Badge */}
//                         <motion.div
//                             whileHover={{ scale: 1.02 }}
//                             className="inline-flex items-center gap-2 px-3.5 py-1.5 bg-teal-50 border border-teal-200 rounded-full w-fit"
//                         >
//                             <span className="w-2 h-2 bg-teal-600 rounded-full animate-pulse" />
//                             <span className="text-xs font-semibold text-teal-700">
//                                 TRUSTED BY 500+ COMMUNITIES
//                             </span>
//                         </motion.div>
//
//                         {/* Heading */}
//                         <div className="space-y-3">
//                             <h1 className="text-4xl md:text-5xl lg:text-6xl font-bold text-gray-900 leading-tight">
//                                 Next-Gen{" "}
//                                 <span className="bg-gradient-to-r from-teal-600 to-cyan-600 bg-clip-text text-transparent">
//                                     Smart Water
//                                 </span>{" "}
//                                 Management System
//                             </h1>
//                             <p className="text-lg text-gray-600 leading-relaxed">
//                                 Optimize water usage, automate billing, and monitor infrastructure with a powerful modern platform.
//                             </p>
//                         </div>
//
//                         {/* CTA Buttons */}
//                         <div className="flex flex-col sm:flex-row gap-3 pt-2">
//                             <motion.button
//                                 whileHover={{ scale: 1.02, y: -2 }}
//                                 whileTap={{ scale: 0.98 }}
//                                 onClick={() => navigate("/register")}
//                                 className="inline-flex items-center justify-center gap-2 px-6 py-3 bg-gradient-to-r from-teal-600 to-cyan-600 text-white font-semibold rounded-lg shadow-md hover:shadow-lg transition-all duration-200"
//                             >
//                                 Get Started
//                                 <ArrowRight className="w-4 h-4" />
//                             </motion.button>
//                             <motion.button
//                                 whileHover={{ scale: 1.02, y: -2 }}
//                                 whileTap={{ scale: 0.98 }}
//                                 className="inline-flex items-center justify-center gap-2 px-6 py-3 border-2 border-gray-200 text-gray-900 font-semibold rounded-lg hover:bg-gray-50 transition-all duration-200"
//                             >
//                                 <Play className="w-4 h-4" />
//                                 Watch Demo
//                             </motion.button>
//                         </div>
//
//                         {/* Stats */}
//                         <motion.div
//                             variants={itemVariants}
//                             className="grid grid-cols-3 gap-6 pt-8 border-t border-gray-200"
//                         >
//                             {[
//                                 { value: "10K+", label: "Active Users" },
//                                 { value: "50M", label: "L Tracked" },
//                                 { value: "2Cr+", label: "Saved" },
//                             ].map((stat, idx) => (
//                                 <div key={idx}>
//                                     <p className="text-2xl font-bold text-teal-600">{stat.value}</p>
//                                     <p className="text-xs text-gray-500 mt-1">{stat.label}</p>
//                                 </div>
//                             ))}
//                         </motion.div>
//                     </motion.div>
//
//                     {/* Right Content - Illustration */}
//                     <motion.div
//                         variants={itemVariants}
//                         className="relative hidden md:block"
//                     >
//                         <motion.div
//                             animate={{ y: [0, -12, 0] }}
//                             transition={{ duration: 4, repeat: Infinity, ease: "easeInOut" }}
//                             className="relative"
//                         >
//                             <div className="bg-gradient-to-br from-teal-100 via-cyan-100 to-teal-50 rounded-2xl p-8 shadow-xl">
//                                 <div className="aspect-square bg-gradient-to-br from-teal-400 to-cyan-400 rounded-xl flex items-center justify-center">
//                                     <svg
//                                         className="w-24 h-24 text-white opacity-80"
//                                         fill="currentColor"
//                                         viewBox="0 0 20 20"
//                                     >
//                                         <path d="M2 11a1 1 0 011-1h2a1 1 0 011 1v5a1 1 0 01-1 1H3a1 1 0 01-1-1v-5zM8 7a1 1 0 011-1h2a1 1 0 011 1v9a1 1 0 01-1 1H9a1 1 0 01-1-1V7zM14 4a1 1 0 011-1h2a1 1 0 011 1v12a1 1 0 01-1 1h-2a1 1 0 01-1-1V4z" />
//                                     </svg>
//                                 </div>
//                             </div>
//
//                             {/* Floating Card 1 */}
//                             <motion.div
//                                 animate={{ x: [0, 8, 0] }}
//                                 transition={{ duration: 3, repeat: Infinity }}
//                                 className="absolute -bottom-6 -left-8 bg-white p-4 rounded-lg shadow-lg border border-gray-200"
//                             >
//                                 <p className="text-xs text-gray-500 font-medium">Daily Usage</p>
//                                 <p className="text-xl font-bold text-gray-900 mt-1">245L</p>
//                             </motion.div>
//
//                             {/* Floating Card 2 */}
//                             <motion.div
//                                 animate={{ x: [0, -8, 0] }}
//                                 transition={{ duration: 4, repeat: Infinity }}
//                                 className="absolute top-12 -right-8 bg-white p-4 rounded-lg shadow-lg border border-gray-200"
//                             >
//                                 <p className="text-xs text-gray-500 font-medium">Monthly Bill</p>
//                                 <p className="text-xl font-bold text-teal-600 mt-1">₹1,245</p>
//                             </motion.div>
//                         </motion.div>
//                     </motion.div>
//                 </motion.div>
//             </div>
//         </section>
//     );
// };
//
// export default Hero;
import React from "react";
import { useNavigate } from "react-router-dom";
import { ArrowRight, Play } from "lucide-react";
import { motion } from "framer-motion";

const Hero = () => {
    const navigate = useNavigate();

    const containerVariants = {
        hidden: { opacity: 0 },
        visible: {
            opacity: 1,
            transition: {
                staggerChildren: 0.2,
                delayChildren: 0.2,
            },
        },
    };

    const itemVariants = {
        hidden: { opacity: 0, y: 20 },
        visible: {
            opacity: 1,
            y: 0,
            transition: { duration: 0.8, ease: "easeOut" },
        },
    };

    return (
        <section className="relative px-6 py-20 md:py-28 bg-white overflow-hidden">
            {/* Background blur */}
            <div className="absolute top-0 right-0 w-96 h-96 bg-teal-50 rounded-full blur-3xl opacity-40" />
            <div className="absolute -bottom-32 left-1/3 w-96 h-96 bg-cyan-50 rounded-full blur-3xl opacity-30" />

            <div className="relative max-w-6xl mx-auto">
                <motion.div
                    variants={containerVariants}
                    initial="hidden"
                    animate="visible"
                    className="grid md:grid-cols-2 gap-12 md:gap-16 items-center"
                >
                    {/* LEFT */}
                    <motion.div variants={itemVariants} className="space-y-6">
                        <div className="inline-flex items-center gap-2 px-3.5 py-1.5 bg-teal-50 border border-teal-200 rounded-full">
                            <span className="w-2 h-2 bg-teal-600 rounded-full animate-pulse" />
                            <span className="text-xs font-semibold text-teal-700">
                                TRUSTED BY 500+ COMMUNITIES
                            </span>
                        </div>

                        <div>
                            <h1 className="text-4xl md:text-5xl lg:text-6xl font-bold text-gray-900 leading-tight">
                                Next-Gen{" "}
                                <span className="bg-gradient-to-r from-teal-600 to-cyan-600 bg-clip-text text-transparent">
                                    Smart Water
                                </span>{" "}
                                Management System
                            </h1>

                            <p className="mt-4 text-lg text-gray-600">
                                Optimize water usage, automate billing, and monitor infrastructure with a powerful modern platform.
                            </p>
                        </div>

                        {/* Buttons */}
                        <div className="flex flex-col sm:flex-row gap-3">
                            <motion.button
                                whileHover={{ scale: 1.02 }}
                                whileTap={{ scale: 0.98 }}
                                onClick={() => navigate("/register")}
                                className="flex items-center justify-center gap-2 px-6 py-3 bg-gradient-to-r from-teal-600 to-cyan-600 text-white font-semibold rounded-lg shadow-md hover:shadow-lg"
                            >
                                Get Started
                                <ArrowRight className="w-4 h-4" />
                            </motion.button>

                            <motion.button
                                whileHover={{ scale: 1.02 }}
                                whileTap={{ scale: 0.98 }}
                                className="flex items-center justify-center gap-2 px-6 py-3 border border-gray-300 rounded-lg"
                            >
                                <Play className="w-4 h-4" />
                                Watch Demo
                            </motion.button>
                        </div>

                        {/* Stats */}
                        <div className="grid grid-cols-3 gap-6 pt-6 border-t">
                            <div>
                                <p className="text-2xl font-bold text-teal-600">10K+</p>
                                <p className="text-xs text-gray-500">Users</p>
                            </div>
                            <div>
                                <p className="text-2xl font-bold text-teal-600">50M</p>
                                <p className="text-xs text-gray-500">Liters</p>
                            </div>
                            <div>
                                <p className="text-2xl font-bold text-teal-600">2Cr+</p>
                                <p className="text-xs text-gray-500">Saved</p>
                            </div>
                        </div>
                    </motion.div>

                    {/* RIGHT (UPDATED IMAGE CARD) */}
                    <motion.div
                        variants={itemVariants}
                        className="relative hidden md:block"
                    >
                        <motion.div
                            animate={{ y: [0, -12, 0] }}
                            transition={{ duration: 4, repeat: Infinity }}
                            className="relative"
                        >
                            {/* CARD */}
                            <div className="bg-white p-4 rounded-2xl shadow-2xl border">
                                <img
                                    src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRT284pSLbyjc-tN_OwG7F5WiYrnw2_L09h0Ev5tvPRZw&s=10"
                                    alt="dashboard"
                                    className="rounded-xl w-full object-cover"
                                />
                            </div>

                            {/* Floating Card 1 */}
                            <motion.div
                                animate={{ x: [0, 8, 0] }}
                                transition={{ duration: 3, repeat: Infinity }}
                                className="absolute -bottom-6 -left-8 bg-white p-4 rounded-lg shadow-lg border"
                            >
                                <p className="text-xs text-gray-500">Daily Usage</p>
                                <p className="text-lg font-bold">245L</p>
                            </motion.div>

                            {/* Floating Card 2 */}
                            <motion.div
                                animate={{ x: [0, -8, 0] }}
                                transition={{ duration: 4, repeat: Infinity }}
                                className="absolute top-12 -right-8 bg-white p-4 rounded-lg shadow-lg border"
                            >
                                <p className="text-xs text-gray-500">Monthly Bill</p>
                                <p className="text-lg font-bold text-teal-600">₹1,245</p>
                            </motion.div>
                        </motion.div>
                    </motion.div>

                </motion.div>
            </div>
        </section>
    );
};

export default Hero;