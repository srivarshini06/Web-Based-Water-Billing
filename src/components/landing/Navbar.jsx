// // import React from "react";
// //
// // const Navbar = () => {
// //     return (
// //         <nav className="px-8 py-4 flex justify-between items-center bg-white/70 backdrop-blur-md sticky top-0 z-50 border-b">
// //
// //             <h1 className="font-bold text-lg text-blue-600">
// //                 AquaFlow Pro
// //             </h1>
// //
// //             <div className="hidden md:flex gap-8 text-sm text-gray-600">
// //                 <span className="hover:text-blue-600 cursor-pointer">Features</span>
// //                 <span className="hover:text-blue-600 cursor-pointer">Pricing</span>
// //                 <span className="hover:text-blue-600 cursor-pointer">Solutions</span>
// //                 <span className="hover:text-blue-600 cursor-pointer">Resources</span>
// //             </div>
// //
// //             <button   onClick={() => navigate("/login")} className="bg-blue-600 text-white px-4 py-2 rounded-lg text-sm">
// //                 Get Started
// //             </button>
// //
// //         </nav>
// //     );
// // };
// //
// // export default Navbar;
// import React, { useState } from "react";
// import { useNavigate } from "react-router-dom";
// import { Menu, X, Droplet } from "lucide-react";
// import { motion } from "framer-motion";
//
// const Navbar = () => {
//     const navigate = useNavigate();
//     const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
//
//     return (
//         <motion.nav
//             initial={{ y: -80, opacity: 0 }}
//             animate={{ y: 0, opacity: 1 }}
//             transition={{ duration: 0.5 }}
//             className="sticky top-0 z-50 bg-white/80 backdrop-blur-lg border-b border-gray-200/50"
//         >
//             <div className="max-w-7xl mx-auto px-6 py-4 flex justify-between items-center">
//                 {/* Logo */}
//                 <div className="flex items-center gap-2.5 group cursor-pointer hover:opacity-80 transition-opacity">
//                     <div className="w-9 h-9 bg-gradient-to-br from-teal-600 to-cyan-600 rounded-lg flex items-center justify-center shadow-sm">
//                         <Droplet className="w-5 h-5 text-white" />
//                     </div>
//                     <span className="font-bold text-lg text-gray-900">AquaFlow</span>
//                 </div>
//
//                 {/* Desktop Navigation */}
//                 <div className="hidden md:flex items-center gap-8">
//                     {["Features", "Pricing", "Solutions", "Resources"].map((item) => (
//                         <button
//                             key={item}
//                             className="text-sm text-gray-600 font-medium hover:text-gray-900 transition-colors duration-200"
//                         >
//                             {item}
//                         </button>
//                     ))}
//                 </div>
//
//                 {/* Desktop CTA Button */}
//                 <div className="hidden md:flex items-center gap-3">
//                     <button
//                         onClick={() => navigate("/login")}
//                         className="px-5 py-2.5 text-sm font-medium text-gray-900 hover:bg-gray-100 rounded-lg transition-colors duration-200"
//                     >
//                         Sign in
//                     </button>
//                     <motion.button
//                         whileHover={{ scale: 1.02 }}
//                         whileTap={{ scale: 0.98 }}
//                         onClick={() => navigate("/register")}
//                         className="px-6 py-2.5 bg-gradient-to-r from-teal-600 to-cyan-600 text-white text-sm font-medium rounded-lg shadow-md hover:shadow-lg transition-all duration-200"
//                     >
//                         Get Started
//                     </motion.button>
//                 </div>
//
//                 {/* Mobile Menu Button */}
//                 <button
//                     onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
//                     className="md:hidden p-2 hover:bg-gray-100 rounded-lg transition-colors"
//                 >
//                     {mobileMenuOpen ? (
//                         <X className="w-5 h-5 text-gray-900" />
//                     ) : (
//                         <Menu className="w-5 h-5 text-gray-900" />
//                     )}
//                 </button>
//             </div>
//
//             {/* Mobile Menu */}
//             {mobileMenuOpen && (
//                 <motion.div
//                     initial={{ opacity: 0, y: -10 }}
//                     animate={{ opacity: 1, y: 0 }}
//                     exit={{ opacity: 0, y: -10 }}
//                     className="md:hidden border-t border-gray-200/50 bg-white/50 backdrop-blur-sm"
//                 >
//                     <div className="max-w-7xl mx-auto px-6 py-4 space-y-3">
//                         {["Features", "Pricing", "Solutions", "Resources"].map((item) => (
//                             <button
//                                 key={item}
//                                 className="block w-full text-left px-4 py-2.5 text-sm text-gray-700 hover:bg-gray-100 rounded-lg transition-colors"
//                             >
//                                 {item}
//                             </button>
//                         ))}
//                         <div className="border-t border-gray-200 pt-3 mt-3 space-y-2">
//                             <button
//                                 onClick={() => navigate("/login")}
//                                 className="block w-full px-4 py-2.5 text-sm font-medium text-gray-900 hover:bg-gray-100 rounded-lg transition-colors"
//                             >
//                                 Sign in
//                             </button>
//                             <button
//                                 onClick={() => navigate("/register")}
//                                 className="block w-full px-4 py-2.5 bg-gradient-to-r from-teal-600 to-cyan-600 text-white text-sm font-medium rounded-lg transition-all"
//                             >
//                                 Get Started
//                             </button>
//                         </div>
//                     </div>
//                 </motion.div>
//             )}
//         </motion.nav>
//     );
// };
//
// export default Navbar;
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Menu, X, Droplet } from "lucide-react";
import { motion } from "framer-motion";

const Navbar = () => {
    const navigate = useNavigate();
    const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

    return (
        <motion.nav
            initial={{ y: -80, opacity: 0 }}
            animate={{ y: 0, opacity: 1 }}
            transition={{ duration: 0.5 }}
            className="sticky top-0 z-50 bg-white/80 backdrop-blur-lg border-b border-gray-200/50"
        >
            <div className="max-w-7xl mx-auto px-6 py-4 flex justify-between items-center">
                {/* Logo */}
                <div className="flex items-center gap-2.5 group cursor-pointer hover:opacity-80 transition-opacity">
                    <div className="w-9 h-9 bg-gradient-to-br from-teal-600 to-cyan-600 rounded-lg flex items-center justify-center shadow-sm">
                        <Droplet className="w-5 h-5 text-white" />
                    </div>
                    <span className="font-bold text-lg text-gray-900">AquaFlow</span>
                </div>

                {/* Desktop Navigation */}
                <div className="hidden md:flex items-center gap-8">
                    {["Features", "Pricing", "Solutions", "Resources"].map((item) => (
                        <button
                            key={item}
                            className="text-sm text-gray-600 font-medium hover:text-gray-900 transition-colors duration-200"
                        >
                            {item}
                        </button>
                    ))}
                </div>

                {/* Desktop CTA Button */}
                <div className="hidden md:flex items-center gap-3">
                    <button
                        onClick={() => navigate("/login")}
                        className="px-5 py-2.5 text-sm font-medium text-gray-900 hover:bg-gray-100 rounded-lg transition-colors duration-200"
                    >
                        Sign in
                    </button>
                    <motion.button
                        whileHover={{ scale: 1.02 }}
                        whileTap={{ scale: 0.98 }}
                        onClick={() => navigate("/register")}
                        className="px-6 py-2.5 bg-gradient-to-r from-teal-600 to-cyan-600 text-white text-sm font-medium rounded-lg shadow-md hover:shadow-lg transition-all duration-200"
                    >
                        Get Started
                    </motion.button>
                </div>

                {/* Mobile Menu Button */}
                <button
                    onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
                    className="md:hidden p-2 hover:bg-gray-100 rounded-lg transition-colors"
                >
                    {mobileMenuOpen ? (
                        <X className="w-5 h-5 text-gray-900" />
                    ) : (
                        <Menu className="w-5 h-5 text-gray-900" />
                    )}
                </button>
            </div>

            {/* Mobile Menu */}
            {mobileMenuOpen && (
                <motion.div
                    initial={{ opacity: 0, y: -10 }}
                    animate={{ opacity: 1, y: 0 }}
                    exit={{ opacity: 0, y: -10 }}
                    className="md:hidden border-t border-gray-200/50 bg-white/50 backdrop-blur-sm"
                >
                    <div className="max-w-7xl mx-auto px-6 py-4 space-y-3">
                        {["Features", "Pricing", "Solutions", "Resources"].map((item) => (
                            <button
                                key={item}
                                className="block w-full text-left px-4 py-2.5 text-sm text-gray-700 hover:bg-gray-100 rounded-lg transition-colors"
                            >
                                {item}
                            </button>
                        ))}
                        <div className="border-t border-gray-200 pt-3 mt-3 space-y-2">
                            <button
                                onClick={() => navigate("/login")}
                                className="block w-full px-4 py-2.5 text-sm font-medium text-gray-900 hover:bg-gray-100 rounded-lg transition-colors"
                            >
                                Sign in
                            </button>
                            <button
                                onClick={() => navigate("/register")}
                                className="block w-full px-4 py-2.5 bg-gradient-to-r from-teal-600 to-cyan-600 text-white text-sm font-medium rounded-lg transition-all"
                            >
                                Get Started
                            </button>
                        </div>
                    </div>
                </motion.div>
            )}
        </motion.nav>
    );
};

export default Navbar;