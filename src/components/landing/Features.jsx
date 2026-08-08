// import React from "react";
// import { motion } from "framer-motion";
// import {
//     Zap,
//     CreditCard,
//     Users,
//     BarChart3,
//     AlertCircle,
//     MessageSquare,
// } from "lucide-react";
//
// const Features = () => {
//     const features = [
//         {
//             icon: Zap,
//             title: "Usage Tracking",
//             description: "Real-time telemetry and IoT integration for granular monitoring.",
//             highlights: ["Real-time insights", "Leak detection"],
//         },
//         {
//             icon: CreditCard,
//             title: "Billing System",
//             description: "Automate utility invoicing with tiered pricing and secure payments.",
//             highlights: ["Automated invoicing", "Multiple payment methods"],
//         },
//         {
//             icon: Users,
//             title: "Community Management",
//             description: "Empower residents with dashboards and engagement tools.",
//             highlights: ["User dashboards", "Engagement tools"],
//         },
//         {
//             icon: BarChart3,
//             title: "Analytics & Reports",
//             description: "Comprehensive analytics with detailed consumption trends.",
//             highlights: ["Trend analysis", "Forecasting"],
//         },
//         {
//             icon: AlertCircle,
//             title: "Alert System",
//             description: "Intelligent alerts for anomalies and unusual patterns.",
//             highlights: ["Smart alerts", "Notifications"],
//         },
//         {
//             icon: MessageSquare,
//             title: "Support & Communication",
//             description: "Integrated support with real-time communication channels.",
//             highlights: ["Chat support", "Ticketing system"],
//         },
//     ];
//
//     const containerVariants = {
//         hidden: { opacity: 0 },
//         visible: {
//             opacity: 1,
//             transition: { staggerChildren: 0.08, delayChildren: 0.1 },
//         },
//     };
//
//     const itemVariants = {
//         hidden: { opacity: 0, y: 16 },
//         visible: { opacity: 1, y: 0, transition: { duration: 0.5 } },
//     };
//
//     return (
//         <section className="px-6 py-20 md:py-28 bg-gray-50">
//             <div className="max-w-6xl mx-auto">
//                 <motion.div
//                     variants={containerVariants}
//                     initial="hidden"
//                     whileInView="visible"
//                     viewport={{ once: true, margin: "-100px" }}
//                     className="space-y-16"
//                 >
//                     {/* Header */}
//                     <motion.div variants={itemVariants} className="text-center space-y-3">
//                         <h2 className="text-3xl md:text-4xl font-bold text-gray-900">
//                             Designed for Modern Infrastructure
//                         </h2>
//                         <p className="text-lg text-gray-600 max-w-2xl mx-auto">
//                             Built for efficiency, scalability, and automation
//                         </p>
//                     </motion.div>
//
//                     {/* Features Grid */}
//                     <motion.div
//                         variants={containerVariants}
//                         className="grid md:grid-cols-2 lg:grid-cols-3 gap-6"
//                     >
//                         {features.map((feature, idx) => {
//                             const Icon = feature.icon;
//                             return (
//                                 <motion.div
//                                     key={idx}
//                                     variants={itemVariants}
//                                     whileHover={{
//                                         y: -4,
//                                         boxShadow: "0 16px 32px -8px rgba(13, 110, 110, 0.1)",
//                                     }}
//                                     className="group p-6 bg-white rounded-xl border border-gray-200 hover:border-teal-200 transition-all duration-200"
//                                 >
//                                     {/* Icon */}
//                                     <motion.div
//                                         whileHover={{ scale: 1.08, rotate: 6 }}
//                                         className="w-11 h-11 bg-gradient-to-br from-teal-100 to-cyan-100 rounded-lg flex items-center justify-center mb-4 group-hover:shadow-md transition-shadow"
//                                     >
//                                         <Icon className="w-6 h-6 text-teal-600" />
//                                     </motion.div>
//
//                                     {/* Content */}
//                                     <h3 className="text-lg font-bold text-gray-900 mb-2">
//                                         {feature.title}
//                                     </h3>
//                                     <p className="text-sm text-gray-600 mb-4 leading-relaxed">
//                                         {feature.description}
//                                     </p>
//
//                                     {/* Highlights */}
//                                     <div className="flex flex-wrap gap-2 pt-4 border-t border-gray-100">
//                                         {feature.highlights.map((highlight, i) => (
//                                             <div
//                                                 key={i}
//                                                 className="flex items-center gap-1.5 text-xs text-gray-700 bg-teal-50 px-2.5 py-1.5 rounded-full"
//                                             >
//                                                 <div className="w-1 h-1 bg-teal-600 rounded-full" />
//                                                 {highlight}
//                                             </div>
//                                         ))}
//                                     </div>
//                                 </motion.div>
//                             );
//                         })}
//                     </motion.div>
//                 </motion.div>
//             </div>
//         </section>
//     );
// };
//
// export default Features;
import React from "react";
import { motion } from "framer-motion";
import {
    Zap,
    CreditCard,
    Users,
    BarChart3,
    AlertCircle,
    MessageSquare,
} from "lucide-react";

const Features = () => {
    const features = [
        {
            icon: Zap,
            title: "Usage Tracking",
            description: "Real-time telemetry and IoT integration for granular monitoring.",
            highlights: ["Real-time insights", "Leak detection"],
        },
        {
            icon: CreditCard,
            title: "Billing System",
            description: "Automate utility invoicing with tiered pricing and secure payments.",
            highlights: ["Automated invoicing", "Multiple payment methods"],
        },
        {
            icon: Users,
            title: "Community Management",
            description: "Empower residents with dashboards and engagement tools.",
            highlights: ["User dashboards", "Engagement tools"],
        },
        {
            icon: BarChart3,
            title: "Analytics & Reports",
            description: "Comprehensive analytics with detailed consumption trends.",
            highlights: ["Trend analysis", "Forecasting"],
        },
        {
            icon: AlertCircle,
            title: "Alert System",
            description: "Intelligent alerts for anomalies and unusual patterns.",
            highlights: ["Smart alerts", "Notifications"],
        },
        {
            icon: MessageSquare,
            title: "Support & Communication",
            description: "Integrated support with real-time communication channels.",
            highlights: ["Chat support", "Ticketing system"],
        },
    ];

    const containerVariants = {
        hidden: { opacity: 0 },
        visible: {
            opacity: 1,
            transition: { staggerChildren: 0.08, delayChildren: 0.1 },
        },
    };

    const itemVariants = {
        hidden: { opacity: 0, y: 16 },
        visible: { opacity: 1, y: 0, transition: { duration: 0.5 } },
    };

    return (
        <section className="px-6 py-20 md:py-28 bg-gray-50">
            <div className="max-w-6xl mx-auto">
                <motion.div
                    variants={containerVariants}
                    initial="hidden"
                    whileInView="visible"
                    viewport={{ once: true, margin: "-100px" }}
                    className="space-y-16"
                >
                    {/* Header */}
                    <motion.div variants={itemVariants} className="text-center space-y-3">
                        <h2 className="text-3xl md:text-4xl font-bold text-gray-900">
                            Designed for Modern Infrastructure
                        </h2>
                        <p className="text-lg text-gray-600 max-w-2xl mx-auto">
                            Built for efficiency, scalability, and automation
                        </p>
                    </motion.div>

                    {/* Features Grid */}
                    <motion.div
                        variants={containerVariants}
                        className="grid md:grid-cols-2 lg:grid-cols-3 gap-6"
                    >
                        {features.map((feature, idx) => {
                            const Icon = feature.icon;
                            return (
                                <motion.div
                                    key={idx}
                                    variants={itemVariants}
                                    whileHover={{
                                        y: -4,
                                        boxShadow: "0 16px 32px -8px rgba(13, 110, 110, 0.1)",
                                    }}
                                    className="group p-6 bg-white rounded-xl border border-gray-200 hover:border-teal-200 transition-all duration-200"
                                >
                                    {/* Icon */}
                                    <motion.div
                                        whileHover={{ scale: 1.08, rotate: 6 }}
                                        className="w-11 h-11 bg-gradient-to-br from-teal-100 to-cyan-100 rounded-lg flex items-center justify-center mb-4 group-hover:shadow-md transition-shadow"
                                    >
                                        <Icon className="w-6 h-6 text-teal-600" />
                                    </motion.div>

                                    {/* Content */}
                                    <h3 className="text-lg font-bold text-gray-900 mb-2">
                                        {feature.title}
                                    </h3>
                                    <p className="text-sm text-gray-600 mb-4 leading-relaxed">
                                        {feature.description}
                                    </p>

                                    {/* Highlights */}
                                    <div className="flex flex-wrap gap-2 pt-4 border-t border-gray-100">
                                        {feature.highlights.map((highlight, i) => (
                                            <div
                                                key={i}
                                                className="flex items-center gap-1.5 text-xs text-gray-700 bg-teal-50 px-2.5 py-1.5 rounded-full"
                                            >
                                                <div className="w-1 h-1 bg-teal-600 rounded-full" />
                                                {highlight}
                                            </div>
                                        ))}
                                    </div>
                                </motion.div>
                            );
                        })}
                    </motion.div>
                </motion.div>
            </div>
        </section>
    );
};

export default Features;