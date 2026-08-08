// // import React from "react";
// //
// // const CTA = () => {
// //     return (
// //         <section className="px-8 py-24 bg-gradient-to-b from-white to-blue-50 flex justify-center">
// //
// //             <div className="bg-white/70 backdrop-blur-xl p-10 rounded-2xl shadow-xl text-center max-w-3xl w-full border">
// //
// //                 <h2 className="text-2xl font-bold text-gray-900">
// //                     Ready to Optimize Your Infrastructure?
// //                 </h2>
// //
// //                 <p className="mt-3 text-gray-500">
// //                     Join hundreds of communities improving water efficiency.
// //                 </p>
// //
// //                 <div className="mt-8 flex justify-center gap-4">
// //                     <button className="bg-blue-600 text-white px-6 py-3 rounded-lg">
// //                         Request Demo
// //                     </button>
// //
// //                     <button className="border px-6 py-3 rounded-lg">
// //                         Learn More
// //                     </button>
// //                 </div>
// //
// //             </div>
// //
// //         </section>
// //     );
// // };
// //
// // export default CTA;
//
// import React from "react";
// import { useNavigate } from "react-router-dom";
// import { ArrowRight, Check } from "lucide-react";
// import { motion } from "framer-motion";
//
// const CTA = () => {
//     const navigate = useNavigate();
//
//     const benefits = [
//         "Start free, no credit card required",
//         "Full access to all features",
//         "Dedicated support team",
//         "30-day money back guarantee",
//     ];
//
//     const containerVariants = {
//         hidden: { opacity: 0, y: 32 },
//         visible: { opacity: 1, y: 0, transition: { duration: 0.8 } },
//     };
//
//     return (
//         <section className="relative px-6 py-20 md:py-28 overflow-hidden">
//             {/* Background */}
//             <div className="absolute inset-0 bg-gradient-to-r from-teal-900 via-teal-800 to-cyan-900" />
//             <motion.div
//                 animate={{ opacity: [0.3, 0.5, 0.3] }}
//                 transition={{ duration: 6, repeat: Infinity }}
//                 className="absolute inset-0 bg-gradient-to-b from-transparent via-teal-500/20 to-transparent"
//             />
//
//             {/* Animated Elements */}
//             <div className="absolute inset-0 overflow-hidden pointer-events-none">
//                 <motion.div
//                     animate={{ x: [0, 80, 0], y: [0, -40, 0] }}
//                     transition={{ duration: 20, repeat: Infinity, ease: "linear" }}
//                     className="absolute top-1/4 -right-1/4 w-80 h-80 bg-white/5 rounded-full blur-3xl"
//                 />
//                 <motion.div
//                     animate={{ x: [0, -80, 0], y: [0, 40, 0] }}
//                     transition={{ duration: 24, repeat: Infinity, ease: "linear" }}
//                     className="absolute -bottom-1/4 -left-1/4 w-80 h-80 bg-cyan-400/5 rounded-full blur-3xl"
//                 />
//             </div>
//
//             {/* Content */}
//             <div className="relative max-w-6xl mx-auto">
//                 <motion.div
//                     variants={containerVariants}
//                     initial="hidden"
//                     whileInView="visible"
//                     viewport={{ once: true, margin: "-100px" }}
//                     className="grid md:grid-cols-2 gap-12 items-center"
//                 >
//                     {/* Left */}
//                     <div className="space-y-6">
//                         <div className="space-y-3">
//                             <h2 className="text-3xl md:text-4xl font-bold text-white leading-tight">
//                                 Ready to Optimize Your Infrastructure?
//                             </h2>
//                             <p className="text-base text-teal-100">
//                                 Join hundreds of communities improving water efficiency and reducing costs with AquaFlow Pro.
//                             </p>
//                         </div>
//
//                         {/* Benefits */}
//                         <div className="space-y-2.5">
//                             {benefits.map((benefit, idx) => (
//                                 <motion.div
//                                     key={idx}
//                                     initial={{ opacity: 0, x: -16 }}
//                                     whileInView={{ opacity: 1, x: 0 }}
//                                     transition={{ delay: idx * 0.08 }}
//                                     className="flex items-center gap-3"
//                                 >
//                                     <div className="flex-shrink-0 w-5 h-5 bg-teal-400 rounded-full flex items-center justify-center">
//                                         <Check className="w-3 h-3 text-teal-900" />
//                                     </div>
//                                     <span className="text-white text-sm">{benefit}</span>
//                                 </motion.div>
//                             ))}
//                         </div>
//
//                         {/* CTA Buttons */}
//                         <div className="flex flex-col sm:flex-row gap-3 pt-4">
//                             <motion.button
//                                 whileHover={{ scale: 1.02, y: -2 }}
//                                 whileTap={{ scale: 0.98 }}
//                                 onClick={() => navigate("/register")}
//                                 className="inline-flex items-center justify-center gap-2 px-6 py-3 bg-white text-teal-900 font-semibold rounded-lg shadow-md hover:shadow-lg transition-all duration-200 group"
//                             >
//                                 Get Started Now
//                                 <ArrowRight className="w-4 h-4 group-hover:translate-x-0.5 transition-transform" />
//                             </motion.button>
//                             <motion.button
//                                 whileHover={{ scale: 1.02, y: -2 }}
//                                 whileTap={{ scale: 0.98 }}
//                                 className="inline-flex items-center justify-center px-6 py-3 border-2 border-white text-white font-semibold rounded-lg hover:bg-white/10 transition-all duration-200"
//                             >
//                                 Schedule Demo
//                             </motion.button>
//                         </div>
//                     </div>
//
//                     {/* Right - Glassmorphism Card */}
//                     <motion.div
//                         animate={{ y: [0, -16, 0] }}
//                         transition={{ duration: 4, repeat: Infinity, ease: "easeInOut" }}
//                         className="relative"
//                     >
//                         <div className="absolute inset-0 bg-gradient-to-br from-white/10 to-white/5 rounded-2xl blur-lg" />
//                         <div className="relative bg-white/[0.08] backdrop-blur-xl border border-white/20 rounded-2xl p-8 space-y-6">
//                             <div>
//                                 <h3 className="text-xl font-bold text-white">Free Trial Includes</h3>
//                                 <p className="text-sm text-teal-100 mt-1">
//                                     Everything you need to get started
//                                 </p>
//                             </div>
//
//                             {/* Feature List */}
//                             <div className="space-y-3">
//                                 {[
//                                     { title: "Unlimited Users", desc: "Invite your entire team" },
//                                     { title: "Real-time Analytics", desc: "Full access to all reports" },
//                                     { title: "24/7 Support", desc: "Dedicated support team" },
//                                     { title: "Zero Setup Fee", desc: "Start immediately" },
//                                 ].map((item, idx) => (
//                                     <div
//                                         key={idx}
//                                         className="flex items-start gap-3 pb-3 border-b border-white/10 last:border-b-0"
//                                     >
//                                         <div className="flex-shrink-0 w-4 h-4 bg-teal-400 rounded-full flex items-center justify-center mt-0.5">
//                                             <Check className="w-2.5 h-2.5 text-teal-900" />
//                                         </div>
//                                         <div>
//                                             <p className="text-white text-sm font-semibold">{item.title}</p>
//                                             <p className="text-teal-100 text-xs mt-0.5">{item.desc}</p>
//                                         </div>
//                                     </div>
//                                 ))}
//                             </div>
//
//                             {/* Button */}
//                             <motion.button
//                                 whileHover={{ scale: 1.02 }}
//                                 onClick={() => navigate("/register")}
//                                 className="w-full py-2.5 bg-gradient-to-r from-teal-400 to-cyan-400 text-teal-900 font-semibold rounded-lg hover:shadow-lg transition-all duration-200 text-sm"
//                             >
//                                 Start Your Free Trial
//                             </motion.button>
//                         </div>
//                     </motion.div>
//                 </motion.div>
//
//                 {/* Stats */}
//                 <motion.div
//                     variants={containerVariants}
//                     className="grid grid-cols-2 md:grid-cols-4 gap-6 md:gap-8 mt-20 pt-16 border-t border-white/10"
//                 >
//                     {[
//                         { value: "2,500+", label: "Organizations" },
//                         { value: "50M+", label: "Liters Tracked" },
//                         { value: "₹2Cr+", label: "Savings" },
//                         { value: "98%", label: "Satisfaction" },
//                     ].map((stat, idx) => (
//                         <div key={idx} className="text-center">
//                             <p className="text-3xl md:text-4xl font-bold text-white">{stat.value}</p>
//                             <p className="text-sm text-teal-100 mt-2">{stat.label}</p>
//                         </div>
//                     ))}
//                 </motion.div>
//             </div>
//         </section>
//     );
// };
//
// export default CTA;
import React from "react";
import { useNavigate } from "react-router-dom";
import { ArrowRight, Check } from "lucide-react";
import { motion } from "framer-motion";

const CTA = () => {
    const navigate = useNavigate();

    const benefits = [
        "Start free, no credit card required",
        "Full access to all features",
        "Dedicated support team",
        "30-day money back guarantee",
    ];

    const containerVariants = {
        hidden: { opacity: 0, y: 32 },
        visible: { opacity: 1, y: 0, transition: { duration: 0.8 } },
    };

    return (
        <section className="relative px-6 py-20 md:py-28 overflow-hidden">
            {/* Background */}
            <div className="absolute inset-0 bg-gradient-to-r from-teal-900 via-teal-800 to-cyan-900" />
            <motion.div
                animate={{ opacity: [0.3, 0.5, 0.3] }}
                transition={{ duration: 6, repeat: Infinity }}
                className="absolute inset-0 bg-gradient-to-b from-transparent via-teal-500/20 to-transparent"
            />

            {/* Animated Elements */}
            <div className="absolute inset-0 overflow-hidden pointer-events-none">
                <motion.div
                    animate={{ x: [0, 80, 0], y: [0, -40, 0] }}
                    transition={{ duration: 20, repeat: Infinity, ease: "linear" }}
                    className="absolute top-1/4 -right-1/4 w-80 h-80 bg-white/5 rounded-full blur-3xl"
                />
                <motion.div
                    animate={{ x: [0, -80, 0], y: [0, 40, 0] }}
                    transition={{ duration: 24, repeat: Infinity, ease: "linear" }}
                    className="absolute -bottom-1/4 -left-1/4 w-80 h-80 bg-cyan-400/5 rounded-full blur-3xl"
                />
            </div>

            {/* Content */}
            <div className="relative max-w-6xl mx-auto">
                <motion.div
                    variants={containerVariants}
                    initial="hidden"
                    whileInView="visible"
                    viewport={{ once: true, margin: "-100px" }}
                    className="grid md:grid-cols-2 gap-12 items-center"
                >
                    {/* Left */}
                    <div className="space-y-6">
                        <div className="space-y-3">
                            <h2 className="text-3xl md:text-4xl font-bold text-white leading-tight">
                                Ready to Optimize Your Infrastructure?
                            </h2>
                            <p className="text-base text-teal-100">
                                Join hundreds of communities improving water efficiency and reducing costs with AquaFlow Pro.
                            </p>
                        </div>

                        {/* Benefits */}
                        <div className="space-y-2.5">
                            {benefits.map((benefit, idx) => (
                                <motion.div
                                    key={idx}
                                    initial={{ opacity: 0, x: -16 }}
                                    whileInView={{ opacity: 1, x: 0 }}
                                    transition={{ delay: idx * 0.08 }}
                                    className="flex items-center gap-3"
                                >
                                    <div className="flex-shrink-0 w-5 h-5 bg-teal-400 rounded-full flex items-center justify-center">
                                        <Check className="w-3 h-3 text-teal-900" />
                                    </div>
                                    <span className="text-white text-sm">{benefit}</span>
                                </motion.div>
                            ))}
                        </div>

                        {/* CTA Buttons */}
                        <div className="flex flex-col sm:flex-row gap-3 pt-4">
                            <motion.button
                                whileHover={{ scale: 1.02, y: -2 }}
                                whileTap={{ scale: 0.98 }}
                                onClick={() => navigate("/register")}
                                className="inline-flex items-center justify-center gap-2 px-6 py-3 bg-white text-teal-900 font-semibold rounded-lg shadow-md hover:shadow-lg transition-all duration-200 group"
                            >
                                Get Started Now
                                <ArrowRight className="w-4 h-4 group-hover:translate-x-0.5 transition-transform" />
                            </motion.button>
                            <motion.button
                                whileHover={{ scale: 1.02, y: -2 }}
                                whileTap={{ scale: 0.98 }}
                                className="inline-flex items-center justify-center px-6 py-3 border-2 border-white text-white font-semibold rounded-lg hover:bg-white/10 transition-all duration-200"
                            >
                                Schedule Demo
                            </motion.button>
                        </div>
                    </div>

                    {/* Right - Glassmorphism Card */}
                    <motion.div
                        animate={{ y: [0, -16, 0] }}
                        transition={{ duration: 4, repeat: Infinity, ease: "easeInOut" }}
                        className="relative"
                    >
                        <div className="absolute inset-0 bg-gradient-to-br from-white/10 to-white/5 rounded-2xl blur-lg" />
                        <div className="relative bg-white/[0.08] backdrop-blur-xl border border-white/20 rounded-2xl p-8 space-y-6">
                            <div>
                                <h3 className="text-xl font-bold text-white">Free Trial Includes</h3>
                                <p className="text-sm text-teal-100 mt-1">
                                    Everything you need to get started
                                </p>
                            </div>

                            {/* Feature List */}
                            <div className="space-y-3">
                                {[
                                    { title: "Unlimited Users", desc: "Invite your entire team" },
                                    { title: "Real-time Analytics", desc: "Full access to all reports" },
                                    { title: "24/7 Support", desc: "Dedicated support team" },
                                    { title: "Zero Setup Fee", desc: "Start immediately" },
                                ].map((item, idx) => (
                                    <div
                                        key={idx}
                                        className="flex items-start gap-3 pb-3 border-b border-white/10 last:border-b-0"
                                    >
                                        <div className="flex-shrink-0 w-4 h-4 bg-teal-400 rounded-full flex items-center justify-center mt-0.5">
                                            <Check className="w-2.5 h-2.5 text-teal-900" />
                                        </div>
                                        <div>
                                            <p className="text-white text-sm font-semibold">{item.title}</p>
                                            <p className="text-teal-100 text-xs mt-0.5">{item.desc}</p>
                                        </div>
                                    </div>
                                ))}
                            </div>

                            {/* Button */}
                            <motion.button
                                whileHover={{ scale: 1.02 }}
                                onClick={() => navigate("/register")}
                                className="w-full py-2.5 bg-gradient-to-r from-teal-400 to-cyan-400 text-teal-900 font-semibold rounded-lg hover:shadow-lg transition-all duration-200 text-sm"
                            >
                                Start Your Free Trial
                            </motion.button>
                        </div>
                    </motion.div>
                </motion.div>

                {/* Stats */}
                <motion.div
                    variants={containerVariants}
                    className="grid grid-cols-2 md:grid-cols-4 gap-6 md:gap-8 mt-20 pt-16 border-t border-white/10"
                >
                    {[
                        { value: "2,500+", label: "Organizations" },
                        { value: "50M+", label: "Liters Tracked" },
                        { value: "₹2Cr+", label: "Savings" },
                        { value: "98%", label: "Satisfaction" },
                    ].map((stat, idx) => (
                        <div key={idx} className="text-center">
                            <p className="text-3xl md:text-4xl font-bold text-white">{stat.value}</p>
                            <p className="text-sm text-teal-100 mt-2">{stat.label}</p>
                        </div>
                    ))}
                </motion.div>
            </div>
        </section>
    );
};

export default CTA;