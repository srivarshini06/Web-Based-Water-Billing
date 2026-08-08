// import React, { useState } from "react";
// import { useNavigate, Link } from "react-router-dom";
// import { Mail, Lock, Eye, EyeOff, ArrowRight } from "lucide-react";
// import { motion } from "framer-motion";
// import toast from "react-hot-toast";
//
// const Login = () => {
//     const navigate = useNavigate();
//     const [email, setEmail] = useState("");
//     const [password, setPassword] = useState("");
//     const [showPassword, setShowPassword] = useState(false);
//     const [rememberMe, setRememberMe] = useState(false);
//     const [errors, setErrors] = useState({});
//     const [isLoading, setIsLoading] = useState(false);
//
//     const validateForm = () => {
//         const newErrors = {};
//
//         if (!email) {
//             newErrors.email = "Email is required";
//         } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
//             newErrors.email = "Please enter a valid email";
//         }
//
//         if (!password) {
//             newErrors.password = "Password is required";
//         } else if (password.length < 6) {
//             newErrors.password = "Password must be at least 6 characters";
//         }
//
//         setErrors(newErrors);
//         return Object.keys(newErrors).length === 0;
//     };
//
//     const handleSubmit = async (e) => {
//         e.preventDefault();
//
//         if (!validateForm()) {
//             return;
//         }
//
//         setIsLoading(true);
//
//         setTimeout(() => {
//             if (email === "admin@aquaflow.com" && password === "password") {
//                 localStorage.setItem("userRole", "superAdmin");
//                 localStorage.setItem("userEmail", email);
//                 toast.success("Welcome back, Admin!");
//                 navigate("/admin/dashboard");
//             } else if (email === "community@aquaflow.com" && password === "password") {
//                 localStorage.setItem("userRole", "communityAdmin");
//                 localStorage.setItem("userEmail", email);
//                 toast.success("Welcome back, Community Manager!");
//                 navigate("/community/dashboard");
//             } else if (email === "resident@aquaflow.com" && password === "password") {
//                 localStorage.setItem("userRole", "resident");
//                 localStorage.setItem("userEmail", email);
//                 toast.success("Welcome back!");
//                 navigate("/resident/dashboard");
//             } else {
//                 toast.error("Invalid email or password");
//             }
//             setIsLoading(false);
//         }, 1000);
//     };
//
//     const containerVariants = {
//         hidden: { opacity: 0, y: 20 },
//         visible: {
//             opacity: 1,
//             y: 0,
//             transition: { duration: 0.6, staggerChildren: 0.1 },
//         },
//     };
//
//     const itemVariants = {
//         hidden: { opacity: 0, y: 10 },
//         visible: { opacity: 1, y: 0, transition: { duration: 0.4 } },
//     };
//
//     return (
//         <div className="min-h-screen grid md:grid-cols-2 bg-white">
//             {/* LEFT SIDE */}
//             <motion.div
//                 initial={{ opacity: 0 }}
//                 animate={{ opacity: 1 }}
//                 transition={{ duration: 0.8 }}
//                 className="relative hidden md:flex flex-col justify-between p-12 bg-gradient-to-br from-teal-900 via-teal-800 to-cyan-900 overflow-hidden"
//             >
//                 {/* Animated Background */}
//                 <motion.div
//                     animate={{ y: [0, -20, 0], x: [0, 10, 0] }}
//                     transition={{ duration: 8, repeat: Infinity, ease: "easeInOut" }}
//                     className="absolute top-1/4 right-1/4 w-96 h-96 bg-white/10 rounded-full blur-3xl"
//                 />
//                 <motion.div
//                     animate={{ y: [0, 20, 0], x: [0, -10, 0] }}
//                     transition={{ duration: 10, repeat: Infinity, ease: "easeInOut" }}
//                     className="absolute -bottom-32 -left-32 w-96 h-96 bg-cyan-400/10 rounded-full blur-3xl"
//                 />
//
//                 <div className="relative space-y-8">
//                     <Link to="/" className="flex items-center gap-3">
//                         <div className="w-11 h-11 bg-white rounded-lg flex items-center justify-center">
//                             <svg className="w-6 h-6 text-teal-600" fill="currentColor" viewBox="0 0 20 20">
//                                 <path d="M10 2a8 8 0 100 16 8 8 0 000-16z" />
//                             </svg>
//                         </div>
//                         <div>
//                             <p className="font-bold text-lg text-white">AquaFlow</p>
//                             <p className="text-xs text-teal-200">Pro</p>
//                         </div>
//                     </Link>
//
//                     <div className="space-y-4">
//                         <h2 className="text-3xl font-bold text-white leading-tight">
//                             Empowering Smarter Water Management
//                         </h2>
//                         <p className="text-lg text-teal-100">
//                             Monitor water usage, automate billing, and optimize your community efficiently.
//                         </p>
//                     </div>
//
//                     <div className="space-y-3">
//                         {["Real-time water monitoring", "Automated billing system", "Community engagement tools", "Advanced analytics"].map((feature, idx) => (
//                             <motion.div
//                                 key={idx}
//                                 initial={{ opacity: 0, x: -16 }}
//                                 animate={{ opacity: 1, x: 0 }}
//                                 transition={{ delay: idx * 0.1 }}
//                                 className="flex items-center gap-3"
//                             >
//                                 <div className="flex-shrink-0 w-5 h-5 bg-teal-400 rounded-full flex items-center justify-center">
//                                     <svg className="w-3 h-3 text-teal-900" fill="currentColor" viewBox="0 0 20 20">
//                                         <path fillRule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clipRule="evenodd" />
//                                     </svg>
//                                 </div>
//                                 <span className="text-teal-100">{feature}</span>
//                             </motion.div>
//                         ))}
//                     </div>
//                 </div>
//
//                 <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} transition={{ delay: 0.4 }} className="relative grid grid-cols-3 gap-6 pt-8 border-t border-white/10">
//                     {[
//                         { value: "10K+", label: "Active Users" },
//                         { value: "2,500+", label: "Communities" },
//                         { value: "99.9%", label: "Uptime" },
//                     ].map((stat, idx) => (
//                         <div key={idx} className="text-center">
//                             <p className="text-2xl font-bold text-teal-300">{stat.value}</p>
//                             <p className="text-xs text-teal-200 mt-1">{stat.label}</p>
//                         </div>
//                     ))}
//                 </motion.div>
//             </motion.div>
//
//             {/* RIGHT SIDE */}
//             <div className="flex items-center justify-center px-6 py-12 md:py-0 bg-gray-50">
//                 <motion.div
//                     variants={containerVariants}
//                     initial="hidden"
//                     animate="visible"
//                     className="w-full max-w-md space-y-6"
//                 >
//                     {/* Header */}
//                     <motion.div variants={itemVariants} className="space-y-2">
//                         <h1 className="text-2xl font-bold text-gray-900">Welcome Back</h1>
//                         <p className="text-sm text-gray-600">Sign in to your account to continue</p>
//                     </motion.div>
//
//                     {/* Login Card */}
//                     <motion.div variants={itemVariants} className="relative group">
//                         <div className="absolute inset-0 bg-gradient-to-r from-teal-600 to-cyan-600 rounded-xl blur opacity-0 group-hover:opacity-10 transition-all duration-300" />
//                         <div className="relative bg-white border border-gray-200 rounded-xl p-6 space-y-5">
//                             <form onSubmit={handleSubmit} className="space-y-4">
//                                 {/* Email */}
//                                 <motion.div variants={itemVariants} className="space-y-2">
//                                     <label className="block text-sm font-semibold text-gray-900">Email Address</label>
//                                     <div className="relative">
//                                         <Mail className="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
//                                         <input
//                                             type="email"
//                                             value={email}
//                                             onChange={(e) => setEmail(e.target.value)}
//                                             placeholder="name@company.com"
//                                             className="w-full pl-10 pr-4 py-2.5 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-500 focus:bg-white transition-all text-sm"
//                                         />
//                                     </div>
//                                     {errors.email && <p className="text-xs text-red-500 font-medium">{errors.email}</p>}
//                                 </motion.div>
//
//                                 {/* Password */}
//                                 <motion.div variants={itemVariants} className="space-y-2">
//                                     <div className="flex items-center justify-between">
//                                         <label className="block text-sm font-semibold text-gray-900">Password</label>
//                                         <button type="button" className="text-xs text-teal-600 hover:text-teal-700 font-medium">
//                                             Forgot password?
//                                         </button>
//                                     </div>
//                                     <div className="relative">
//                                         <Lock className="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
//                                         <input
//                                             type={showPassword ? "text" : "password"}
//                                             value={password}
//                                             onChange={(e) => setPassword(e.target.value)}
//                                             placeholder="••••••••"
//                                             className="w-full pl-10 pr-10 py-2.5 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-500 focus:bg-white transition-all text-sm"
//                                         />
//                                         <button
//                                             type="button"
//                                             onClick={() => setShowPassword(!showPassword)}
//                                             className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400"
//                                         >
//                                             {showPassword ? <EyeOff className="w-5 h-5" /> : <Eye className="w-5 h-5" />}
//                                         </button>
//                                     </div>
//                                     {errors.password && <p className="text-xs text-red-500 font-medium">{errors.password}</p>}
//                                 </motion.div>
//
//                                 {/* Remember Me */}
//                                 <motion.div variants={itemVariants} className="flex items-center gap-2">
//                                     <input
//                                         type="checkbox"
//                                         id="remember"
//                                         checked={rememberMe}
//                                         onChange={(e) => setRememberMe(e.target.checked)}
//                                         className="w-4 h-4 rounded border-gray-300 text-teal-600"
//                                     />
//                                     <label htmlFor="remember" className="text-sm text-gray-700">
//                                         Remember me for 30 days
//                                     </label>
//                                 </motion.div>
//
//                                 {/* Submit Button */}
//                                 <motion.button
//                                     variants={itemVariants}
//                                     whileHover={{ scale: 1.01 }}
//                                     whileTap={{ scale: 0.99 }}
//                                     type="submit"
//                                     disabled={isLoading}
//                                     className="w-full py-2.5 bg-gradient-to-r from-teal-600 to-teal-700 text-white font-semibold rounded-lg hover:shadow-lg transition-all flex items-center justify-center gap-2 disabled:opacity-50 text-sm"
//                                 >
//                                     {isLoading ? (
//                                         <>
//                                             <div className="w-4 h-4 border-2 border-white/30 border-t-white rounded-full animate-spin" />
//                                             Signing in...
//                                         </>
//                                     ) : (
//                                         <>
//                                             Sign In
//                                             <ArrowRight className="w-4 h-4" />
//                                         </>
//                                     )}
//                                 </motion.button>
//                             </form>
//
//                             {/* Divider */}
//                             <div className="relative py-2">
//                                 <div className="absolute inset-0 flex items-center">
//                                     <div className="w-full border-t border-gray-200" />
//                                 </div>
//                                 <div className="relative flex justify-center text-xs">
//                                     <span className="px-2 bg-white text-gray-600">New to AquaFlow?</span>
//                                 </div>
//                             </div>
//
//                             {/* Sign Up Link */}
//                             <Link to="/register" className="w-full py-2.5 border-2 border-gray-200 text-gray-900 font-semibold rounded-lg hover:border-teal-600 hover:text-teal-600 transition-all text-center text-sm block">
//                                 Create Account
//                             </Link>
//                         </div>
//                     </motion.div>
//
//                     {/* Footer */}
//                     <motion.p variants={itemVariants} className="text-center text-xs text-gray-600">
//                         By signing in, you agree to our{" "}
//                         <button className="text-teal-600 hover:text-teal-700 font-medium">Terms of Service</button> and{" "}
//                         <button className="text-teal-600 hover:text-teal-700 font-medium">Privacy Policy</button>
//                     </motion.p>
//
//                     {/* Demo Credentials */}
//                     <motion.div variants={itemVariants} className="mt-6 p-3 bg-teal-50 border border-teal-200 rounded-lg">
//                         <p className="text-xs font-semibold text-teal-900 mb-2">Demo Credentials:</p>
//                         <div className="space-y-1 text-xs text-teal-800">
//                             <p>👤 Admin: admin@aquaflow.com / password</p>
//                             <p>🏢 Community: community@aquaflow.com / password</p>
//                             <p>👥 Resident: resident@aquaflow.com / password</p>
//                         </div>
//                     </motion.div>
//                 </motion.div>
//             </div>
//         </div>
//     );
// };
//
// export default Login;
import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { Mail, Lock, Eye, EyeOff, ArrowRight } from "lucide-react";
import { motion } from "framer-motion";
import toast from "react-hot-toast";

const Login = () => {
    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [rememberMe, setRememberMe] = useState(false);
    const [errors, setErrors] = useState({});
    const [isLoading, setIsLoading] = useState(false);

    const validateForm = () => {
        const newErrors = {};

        if (!email) {
            newErrors.email = "Email is required";
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
            newErrors.email = "Please enter a valid email";
        }

        if (!password) {
            newErrors.password = "Password is required";
        } else if (password.length < 6) {
            newErrors.password = "Password must be at least 6 characters";
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (!validateForm()) return;

        setIsLoading(true);

        setTimeout(() => {
            if (email === "admin@aquaflow.com" && password === "password") {
                localStorage.setItem("userRole", "superAdmin");
                localStorage.setItem("userEmail", email);
                toast.success("Welcome back, Admin!");
                navigate("/super-admin");
            }

            else if (email === "community@aquaflow.com" && password === "password") {
                localStorage.setItem("userRole", "communityAdmin");
                localStorage.setItem("userEmail", email);
                toast.success("Welcome back, Community Manager!");
                navigate("/community-admin");
            }

            else if (email === "resident@aquaflow.com" && password === "password") {
                localStorage.setItem("userRole", "resident");
                localStorage.setItem("userEmail", email);
                toast.success("Welcome back!");
                navigate("/resident");
            }

            else {
                toast.error("Invalid email or password");
            }

            setIsLoading(false);
        }, 1000);
    };

    const containerVariants = {
        hidden: { opacity: 0, y: 20 },
        visible: {
            opacity: 1,
            y: 0,
            transition: {
                duration: 0.6,
                staggerChildren: 0.1,
            },
        },
    };

    const itemVariants = {
        hidden: { opacity: 0, y: 10 },
        visible: {
            opacity: 1,
            y: 0,
            transition: { duration: 0.4 },
        },
    };

    return (
        <div className="min-h-screen grid md:grid-cols-2 bg-white">

            {/* LEFT PANEL */}
            <motion.div
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ duration: 0.8 }}
                className="relative hidden md:flex flex-col justify-between p-12 bg-gradient-to-br from-teal-900 via-teal-800 to-cyan-900 overflow-hidden"
            >
                <motion.div
                    animate={{ y: [0, -20, 0], x: [0, 10, 0] }}
                    transition={{ duration: 8, repeat: Infinity }}
                    className="absolute top-1/4 right-1/4 w-96 h-96 bg-white/10 rounded-full blur-3xl"
                />

                <motion.div
                    animate={{ y: [0, 20, 0], x: [0, -10, 0] }}
                    transition={{ duration: 10, repeat: Infinity }}
                    className="absolute -bottom-32 -left-32 w-96 h-96 bg-cyan-400/10 rounded-full blur-3xl"
                />

                <div className="relative space-y-8">

                    <Link to="/" className="flex items-center gap-3">
                        <div className="w-11 h-11 rounded-lg bg-white flex items-center justify-center">
                            💧
                        </div>

                        <div>
                            <h2 className="font-bold text-white text-lg">
                                AquaFlow
                            </h2>
                            <p className="text-teal-200 text-xs">Pro</p>
                        </div>
                    </Link>

                    <div>
                        <h1 className="text-4xl font-bold text-white leading-tight">
                            Empowering Smarter Water Management
                        </h1>

                        <p className="mt-4 text-teal-100">
                            Monitor water usage, automate billing, and optimize
                            your community efficiently.
                        </p>
                    </div>

                    <div className="space-y-3">
                        {[
                            "Real-time water monitoring",
                            "Automated billing system",
                            "Community engagement tools",
                            "Advanced analytics",
                        ].map((feature) => (
                            <div key={feature} className="flex items-center gap-3">
                                <div className="w-5 h-5 rounded-full bg-teal-400 flex items-center justify-center">
                                    ✓
                                </div>

                                <span className="text-teal-100">{feature}</span>
                            </div>
                        ))}
                    </div>
                </div>

                <div className="grid grid-cols-3 gap-6 pt-8 border-t border-white/10">
                    <div className="text-center">
                        <p className="text-2xl font-bold text-teal-300">10K+</p>
                        <p className="text-xs text-teal-200">Users</p>
                    </div>

                    <div className="text-center">
                        <p className="text-2xl font-bold text-teal-300">2500+</p>
                        <p className="text-xs text-teal-200">Communities</p>
                    </div>

                    <div className="text-center">
                        <p className="text-2xl font-bold text-teal-300">99.9%</p>
                        <p className="text-xs text-teal-200">Uptime</p>
                    </div>
                </div>
            </motion.div>

            {/* RIGHT PANEL */}

            <div className="flex justify-center items-center bg-gray-50 px-6">

                <motion.div
                    variants={containerVariants}
                    initial="hidden"
                    animate="visible"
                    className="w-full max-w-md space-y-6"
                >

                    <div>
                        <h1 className="text-3xl font-bold text-gray-900">
                            Welcome Back
                        </h1>

                        <p className="text-gray-500 mt-2">
                            Sign in to continue
                        </p>
                    </div>

                    <div className="bg-white rounded-xl shadow-sm border border-gray-200 p-6">

                        <form onSubmit={handleSubmit} className="space-y-5">

                            <div>
                                <label className="text-sm font-semibold">
                                    Email
                                </label>

                                <div className="relative mt-2">
                                    <Mail className="absolute left-3 top-3 w-5 h-5 text-gray-400"/>

                                    <input
                                        type="email"
                                        value={email}
                                        onChange={(e)=>setEmail(e.target.value)}
                                        className="w-full pl-10 pr-4 py-3 border rounded-lg"
                                        placeholder="name@example.com"
                                    />
                                </div>

                                {errors.email &&
                                    <p className="text-red-500 text-xs mt-1">
                                        {errors.email}
                                    </p>}
                            </div>

                            <div>

                                <label className="text-sm font-semibold">
                                    Password
                                </label>

                                <div className="relative mt-2">

                                    <Lock className="absolute left-3 top-3 w-5 h-5 text-gray-400"/>

                                    <input
                                        type={showPassword ? "text":"password"}
                                        value={password}
                                        onChange={(e)=>setPassword(e.target.value)}
                                        className="w-full pl-10 pr-10 py-3 border rounded-lg"
                                        placeholder="********"
                                    />

                                    <button
                                        type="button"
                                        onClick={()=>setShowPassword(!showPassword)}
                                        className="absolute right-3 top-3"
                                    >
                                        {showPassword
                                            ? <EyeOff size={18}/>
                                            : <Eye size={18}/>}
                                    </button>
                                </div>

                                {errors.password &&
                                    <p className="text-red-500 text-xs mt-1">
                                        {errors.password}
                                    </p>}
                            </div>

                            <div className="flex items-center gap-2">
                                <input
                                    type="checkbox"
                                    checked={rememberMe}
                                    onChange={(e)=>setRememberMe(e.target.checked)}
                                />
                                <span className="text-sm">
                                    Remember me
                                </span>
                            </div>

                            <button
                                disabled={isLoading}
                                className="w-full bg-teal-600 hover:bg-teal-700 text-white py-3 rounded-lg font-semibold flex justify-center items-center gap-2"
                            >
                                {isLoading ? "Signing In..." : <>
                                    Sign In
                                    <ArrowRight size={18}/>
                                </>}
                            </button>

                        </form>

                        <div className="mt-6 border-t pt-6">

                            <Link
                                to="/register"
                                className="block text-center border rounded-lg py-3 hover:border-teal-600"
                            >
                                Create Account
                            </Link>

                        </div>
                    </div>

                    <div className="bg-teal-50 border border-teal-200 rounded-lg p-4 text-sm">
                        <p className="font-semibold mb-2">Demo Accounts</p>

                        <p>👤 admin@aquaflow.com / password</p>
                        <p>🏢 community@aquaflow.com / password</p>
                        <p>👥 resident@aquaflow.com / password</p>
                    </div>

                </motion.div>

            </div>

        </div>
    );
};

export default Login;