import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { Mail, Lock, User, Phone, Eye, EyeOff, ArrowRight, Building2, Users } from "lucide-react";
import { motion } from "framer-motion";
import toast from "react-hot-toast";

const Register = () => {
    const navigate = useNavigate();
    const [role, setRole] = useState("resident");
    const [fullName, setFullName] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);
    const [agreeTerms, setAgreeTerms] = useState(false);
    const [errors, setErrors] = useState({});
    const [isLoading, setIsLoading] = useState(false);
    const [passwordStrength, setPasswordStrength] = useState(0);

    const calculatePasswordStrength = (pwd) => {
        let strength = 0;
        if (pwd.length >= 8) strength++;
        if (/[a-z]/.test(pwd) && /[A-Z]/.test(pwd)) strength++;
        if (/[0-9]/.test(pwd)) strength++;
        if (/[^a-zA-Z0-9]/.test(pwd)) strength++;
        setPasswordStrength(strength);
    };

    const validateForm = () => {
        const newErrors = {};

        if (!fullName.trim()) newErrors.fullName = "Full name is required";
        if (!email) newErrors.email = "Email is required";
        else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) newErrors.email = "Please enter a valid email";
        if (!phone.trim()) newErrors.phone = "Phone number is required";
        else if (!/^\d{10}$/.test(phone.replace(/\D/g, ""))) newErrors.phone = "Please enter a valid 10-digit phone number";
        if (!password) newErrors.password = "Password is required";
        else if (password.length < 8) newErrors.password = "Password must be at least 8 characters";
        if (password !== confirmPassword) newErrors.confirmPassword = "Passwords do not match";
        if (!agreeTerms) newErrors.agreeTerms = "You must agree to the terms";

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!validateForm()) return;

        setIsLoading(true);
        setTimeout(() => {
            localStorage.setItem("userRole", role);
            localStorage.setItem("userEmail", email);
            localStorage.setItem("userName", fullName);

            if (role === "communityAdmin") {
                toast.success("Account created! Welcome, Community Manager");
                navigate("/community/dashboard");
            } else {
                toast.success("Account created! Welcome to AquaFlow");
                navigate("/resident/dashboard");
            }
            setIsLoading(false);
        }, 1000);
    };

    const containerVariants = {
        hidden: { opacity: 0 },
        visible: { opacity: 1, transition: { duration: 0.6, staggerChildren: 0.08 } },
    };

    const itemVariants = {
        hidden: { opacity: 0, y: 12 },
        visible: { opacity: 1, y: 0, transition: { duration: 0.4 } },
    };

    const getPasswordStrengthColor = () => {
        if (passwordStrength === 0) return "bg-gray-300";
        if (passwordStrength === 1) return "bg-red-500";
        if (passwordStrength === 2) return "bg-yellow-500";
        if (passwordStrength === 3) return "bg-blue-500";
        return "bg-green-500";
    };

    const getPasswordStrengthText = () => {
        if (passwordStrength === 0) return "";
        if (passwordStrength === 1) return "Weak";
        if (passwordStrength === 2) return "Fair";
        if (passwordStrength === 3) return "Good";
        return "Strong";
    };

    return (
        <div className="min-h-screen grid md:grid-cols-2 bg-white">
            {/* LEFT SIDE */}
            <motion.div
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ duration: 0.8 }}
                className="relative hidden md:flex flex-col justify-between p-12 bg-gradient-to-br from-teal-900 via-teal-800 to-cyan-900 overflow-hidden"
            >
                <motion.div
                    animate={{ y: [0, -20, 0], x: [0, 10, 0] }}
                    transition={{ duration: 8, repeat: Infinity, ease: "easeInOut" }}
                    className="absolute top-1/4 right-1/4 w-96 h-96 bg-white/10 rounded-full blur-3xl"
                />
                <motion.div
                    animate={{ y: [0, 20, 0], x: [0, -10, 0] }}
                    transition={{ duration: 10, repeat: Infinity, ease: "easeInOut" }}
                    className="absolute -bottom-32 -left-32 w-96 h-96 bg-cyan-400/10 rounded-full blur-3xl"
                />

                <div className="relative space-y-8">
                    <Link to="/" className="flex items-center gap-3">
                        <div className="w-11 h-11 bg-white rounded-lg flex items-center justify-center">
                            <svg className="w-6 h-6 text-teal-600" fill="currentColor" viewBox="0 0 20 20">
                                <path d="M10 2a8 8 0 100 16 8 8 0 000-16z" />
                            </svg>
                        </div>
                        <div>
                            <p className="font-bold text-lg text-white">AquaFlow</p>
                            <p className="text-xs text-teal-200">Pro</p>
                        </div>
                    </Link>

                    <div className="space-y-4">
                        <h2 className="text-3xl font-bold text-white leading-tight">Join AquaFlow Today</h2>
                        <p className="text-lg text-teal-100">
                            Create your account and start monitoring water usage, managing bills, and optimizing your community.
                        </p>
                    </div>

                    <div className="space-y-3">
                        {["Real-time water monitoring", "Automated billing system", "Community engagement tools"].map((feature, idx) => (
                            <motion.div
                                key={idx}
                                initial={{ opacity: 0, x: -16 }}
                                animate={{ opacity: 1, x: 0 }}
                                transition={{ delay: idx * 0.1 }}
                                className="flex items-center gap-3"
                            >
                                <div className="flex-shrink-0 w-5 h-5 bg-teal-400 rounded-full flex items-center justify-center">
                                    <svg className="w-3 h-3 text-teal-900" fill="currentColor" viewBox="0 0 20 20">
                                        <path fillRule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clipRule="evenodd" />
                                    </svg>
                                </div>
                                <span className="text-teal-100">{feature}</span>
                            </motion.div>
                        ))}
                    </div>
                </div>

                <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} transition={{ delay: 0.4 }} className="relative grid grid-cols-3 gap-6 pt-8 border-t border-white/10">
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

            {/* RIGHT SIDE */}
            <div className="flex items-center justify-center px-6 py-12 md:py-0 bg-gray-50">
                <motion.div
                    variants={containerVariants}
                    initial="hidden"
                    animate="visible"
                    className="w-full max-w-md space-y-6"
                >
                    <motion.div variants={itemVariants} className="space-y-2">
                        <h1 className="text-2xl font-bold text-gray-900">Create your account</h1>
                        <p className="text-sm text-gray-600">Join AquaFlow and start managing water efficiently</p>
                    </motion.div>

                    {/* Role Selection */}
                    <motion.div variants={itemVariants} className="space-y-3">
                        <label className="block text-sm font-semibold text-gray-900">Select your role</label>
                        <div className="grid grid-cols-2 gap-3">
                            {[
                                { value: "resident", label: "Resident", icon: Users, desc: "View usage & bills" },
                                { value: "communityAdmin", label: "Community Manager", icon: Building2, desc: "Manage community" },
                            ].map((option) => {
                                const Icon = option.icon;
                                return (
                                    <motion.button
                                        key={option.value}
                                        whileHover={{ scale: 1.02 }}
                                        onClick={() => setRole(option.value)}
                                        className={`p-3 rounded-lg border-2 transition-all ${
                                            role === option.value
                                                ? "border-teal-600 bg-teal-50"
                                                : "border-gray-200 bg-white hover:border-gray-300"
                                        }`}
                                    >
                                        <Icon className={`w-5 h-5 mb-2 ${role === option.value ? "text-teal-600" : "text-gray-600"}`} />
                                        <p className="font-semibold text-xs text-gray-900">{option.label}</p>
                                        <p className="text-xs text-gray-600 mt-0.5">{option.desc}</p>
                                    </motion.button>
                                );
                            })}
                        </div>
                    </motion.div>

                    {/* Form Card */}
                    <motion.div variants={itemVariants} className="relative group">
                        <div className="absolute inset-0 bg-gradient-to-r from-teal-600 to-cyan-600 rounded-xl blur opacity-0 group-hover:opacity-10 transition-all" />
                        <div className="relative bg-white border border-gray-200 rounded-xl p-5 space-y-4">
                            <form onSubmit={handleSubmit} className="space-y-3.5">
                                {/* Full Name */}
                                <motion.div variants={itemVariants} className="space-y-2">
                                    <label className="block text-sm font-semibold text-gray-900">Full Name</label>
                                    <div className="relative">
                                        <User className="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
                                        <input
                                            type="text"
                                            value={fullName}
                                            onChange={(e) => setFullName(e.target.value)}
                                            placeholder="John Doe"
                                            className="w-full pl-10 pr-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-500 focus:bg-white transition-all text-sm"
                                        />
                                    </div>
                                    {errors.fullName && <p className="text-xs text-red-500 font-medium">{errors.fullName}</p>}
                                </motion.div>

                                {/* Email */}
                                <motion.div variants={itemVariants} className="space-y-2">
                                    <label className="block text-sm font-semibold text-gray-900">Email Address</label>
                                    <div className="relative">
                                        <Mail className="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
                                        <input
                                            type="email"
                                            value={email}
                                            onChange={(e) => setEmail(e.target.value)}
                                            placeholder="john@example.com"
                                            className="w-full pl-10 pr-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-500 focus:bg-white transition-all text-sm"
                                        />
                                    </div>
                                    {errors.email && <p className="text-xs text-red-500 font-medium">{errors.email}</p>}
                                </motion.div>

                                {/* Phone */}
                                <motion.div variants={itemVariants} className="space-y-2">
                                    <label className="block text-sm font-semibold text-gray-900">Phone Number</label>
                                    <div className="relative">
                                        <Phone className="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
                                        <input
                                            type="tel"
                                            value={phone}
                                            onChange={(e) => setPhone(e.target.value.replace(/\D/g, "").slice(0, 10))}
                                            placeholder="9876543210"
                                            className="w-full pl-10 pr-4 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-500 focus:bg-white transition-all text-sm"
                                        />
                                    </div>
                                    {errors.phone && <p className="text-xs text-red-500 font-medium">{errors.phone}</p>}
                                </motion.div>

                                {/* Password */}
                                <motion.div variants={itemVariants} className="space-y-2">
                                    <label className="block text-sm font-semibold text-gray-900">Password</label>
                                    <div className="relative">
                                        <Lock className="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
                                        <input
                                            type={showPassword ? "text" : "password"}
                                            value={password}
                                            onChange={(e) => {
                                                setPassword(e.target.value);
                                                calculatePasswordStrength(e.target.value);
                                            }}
                                            placeholder="••••••••"
                                            className="w-full pl-10 pr-10 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-500 focus:bg-white transition-all text-sm"
                                        />
                                        <button
                                            type="button"
                                            onClick={() => setShowPassword(!showPassword)}
                                            className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400"
                                        >
                                            {showPassword ? <EyeOff className="w-5 h-5" /> : <Eye className="w-5 h-5" />}
                                        </button>
                                    </div>
                                    {password && (
                                        <div className="flex items-center gap-2 mt-1.5">
                                            <div className="flex-1 h-1 bg-gray-200 rounded-full overflow-hidden">
                                                <div className={`h-full ${getPasswordStrengthColor()} transition-all`} style={{ width: `${(passwordStrength / 4) * 100}%` }} />
                                            </div>
                                            <span className="text-xs text-gray-600">{getPasswordStrengthText()}</span>
                                        </div>
                                    )}
                                    {errors.password && <p className="text-xs text-red-500 font-medium">{errors.password}</p>}
                                </motion.div>

                                {/* Confirm Password */}
                                <motion.div variants={itemVariants} className="space-y-2">
                                    <label className="block text-sm font-semibold text-gray-900">Confirm Password</label>
                                    <div className="relative">
                                        <Lock className="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
                                        <input
                                            type={showConfirmPassword ? "text" : "password"}
                                            value={confirmPassword}
                                            onChange={(e) => setConfirmPassword(e.target.value)}
                                            placeholder="••••••••"
                                            className="w-full pl-10 pr-10 py-2 bg-gray-50 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-500 focus:bg-white transition-all text-sm"
                                        />
                                        <button
                                            type="button"
                                            onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                                            className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400"
                                        >
                                            {showConfirmPassword ? <EyeOff className="w-5 h-5" /> : <Eye className="w-5 h-5" />}
                                        </button>
                                    </div>
                                    {errors.confirmPassword && <p className="text-xs text-red-500 font-medium">{errors.confirmPassword}</p>}
                                </motion.div>

                                {/* Agree Terms */}
                                <motion.div variants={itemVariants} className="flex items-start gap-2">
                                    <input
                                        type="checkbox"
                                        id="terms"
                                        checked={agreeTerms}
                                        onChange={(e) => setAgreeTerms(e.target.checked)}
                                        className="w-4 h-4 mt-0.5 rounded border-gray-300 text-teal-600"
                                    />
                                    <label htmlFor="terms" className="text-xs text-gray-700">
                                        I agree to the{" "}
                                        <button className="text-teal-600 hover:underline font-medium">Terms of Service</button> and{" "}
                                        <button className="text-teal-600 hover:underline font-medium">Privacy Policy</button>
                                    </label>
                                </motion.div>
                                {errors.agreeTerms && <p className="text-xs text-red-500 font-medium">{errors.agreeTerms}</p>}

                                {/* Submit Button */}
                                <motion.button
                                    variants={itemVariants}
                                    whileHover={{ scale: 1.01 }}
                                    whileTap={{ scale: 0.99 }}
                                    type="submit"
                                    disabled={isLoading}
                                    className="w-full py-2.5 bg-gradient-to-r from-teal-600 to-teal-700 text-white font-semibold rounded-lg hover:shadow-lg transition-all flex items-center justify-center gap-2 disabled:opacity-50 text-sm mt-4"
                                >
                                    {isLoading ? (
                                        <>
                                            <div className="w-4 h-4 border-2 border-white/30 border-t-white rounded-full animate-spin" />
                                            Creating account...
                                        </>
                                    ) : (
                                        <>
                                            Create Account
                                            <ArrowRight className="w-4 h-4" />
                                        </>
                                    )}
                                </motion.button>
                            </form>
                        </div>
                    </motion.div>

                    <motion.p variants={itemVariants} className="text-center text-xs text-gray-600">
                        Already have an account?{" "}
                        <Link to="/login" className="text-teal-600 hover:text-teal-700 font-semibold">
                            Sign in here
                        </Link>
                    </motion.p>
                </motion.div>
            </div>
        </div>
    );
};

export default Register;