import React from "react";
import { Link } from "react-router-dom";
import { Droplet, Mail, Phone, MapPin } from "lucide-react";
import { FaGithub, FaTwitter, FaLinkedin } from "react-icons/fa";
import { motion } from "framer-motion";

const Footer = () => {
    const currentYear = new Date().getFullYear();

    const footerLinks = [
        {
            title: "Product",
            links: [
                { label: "Features", href: "#features" },
                { label: "Pricing", href: "#pricing" },
                { label: "Security", href: "#" },
                { label: "Roadmap", href: "#" },
            ],
        },
        {
            title: "Company",
            links: [
                { label: "About Us", href: "#" },
                { label: "Blog", href: "#" },
                { label: "Careers", href: "#" },
                { label: "Press", href: "#" },
            ],
        },
        {
            title: "Resources",
            links: [
                { label: "Documentation", href: "#" },
                { label: "Help Center", href: "#" },
                { label: "API Docs", href: "#" },
                { label: "Community", href: "#" },
            ],
        },
    ];

    const socialLinks = [
        { icon: FaGithub, href: "#", label: "GitHub" },
        { icon: FaTwitter, href: "#", label: "Twitter" },
        { icon: FaLinkedin, href: "#", label: "LinkedIn" },
    ];

    const containerVariants = {
        hidden: { opacity: 0 },
        visible: {
            opacity: 1,
            transition: { staggerChildren: 0.08, delayChildren: 0.1 },
        },
    };

    const itemVariants = {
        hidden: { opacity: 0, y: 8 },
        visible: { opacity: 1, y: 0, transition: { duration: 0.4 } },
    };

    return (
        <footer className="bg-gray-900 text-gray-400">
            {/* Main Footer */}
            <div className="border-t border-gray-800">
                <div className="max-w-7xl mx-auto px-6 py-16 md:py-20">
                    <motion.div
                        variants={containerVariants}
                        initial="hidden"
                        whileInView="visible"
                        viewport={{ once: true }}
                        className="grid grid-cols-2 md:grid-cols-4 gap-8 md:gap-12"
                    >
                        {/* Brand Section */}
                        <motion.div variants={itemVariants} className="col-span-2 md:col-span-1 space-y-4">
                            <Link to="/" className="flex items-center gap-2.5">
                                <div className="w-9 h-9 bg-gradient-to-br from-teal-500 to-cyan-500 rounded-lg flex items-center justify-center">
                                    <Droplet className="w-5 h-5 text-white" />
                                </div>
                                <div>
                                    <p className="font-bold text-white">AquaFlow</p>
                                    <p className="text-xs text-gray-500">Pro</p>
                                </div>
                            </Link>
                            <p className="text-sm text-gray-500 leading-relaxed">
                                Smart water management for modern communities.
                            </p>

                            {/* Social Links */}
                            <div className="flex gap-3 pt-2">
                                {socialLinks.map((social) => {
                                    const Icon = social.icon;
                                    return (
                                        <motion.a
                                            key={social.label}
                                            href={social.href}
                                            whileHover={{ scale: 1.1, y: -2 }}
                                            className="w-9 h-9 bg-gray-800 hover:bg-teal-600 rounded-lg flex items-center justify-center transition-all group"
                                        >
                                            <Icon className="w-4 h-4 group-hover:text-white transition-colors" />
                                        </motion.a>
                                    );
                                })}
                            </div>
                        </motion.div>

                        {/* Links */}
                        {footerLinks.map((section, idx) => (
                            <motion.div
                                key={idx}
                                variants={itemVariants}
                                className="space-y-3"
                            >
                                <h3 className="text-sm font-semibold text-white">
                                    {section.title}
                                </h3>
                                <ul className="space-y-2">
                                    {section.links.map((link) => (
                                        <li key={link.label}>
                                            <a
                                                href={link.href}
                                                className="text-sm text-gray-500 hover:text-teal-400 transition-colors duration-200"
                                            >
                                                {link.label}
                                            </a>
                                        </li>
                                    ))}
                                </ul>
                            </motion.div>
                        ))}
                    </motion.div>
                </div>
            </div>

            {/* Bottom Bar */}
            <div className="border-t border-gray-800">
                <div className="max-w-7xl mx-auto px-6 py-8">
                    <motion.div
                        initial={{ opacity: 0 }}
                        whileInView={{ opacity: 1 }}
                        viewport={{ once: true }}
                        className="flex flex-col md:flex-row items-center justify-between gap-4"
                    >
                        <p className="text-sm text-gray-500">
                            © {currentYear} AquaFlow Pro. All rights reserved.
                        </p>
                        <div className="flex gap-6">
                            <a href="#" className="text-sm text-gray-500 hover:text-teal-400 transition-colors">
                                Privacy Policy
                            </a>
                            <a href="#" className="text-sm text-gray-500 hover:text-teal-400 transition-colors">
                                Terms of Service
                            </a>
                            <a href="#" className="text-sm text-gray-500 hover:text-teal-400 transition-colors">
                                Cookie Settings
                            </a>
                        </div>
                    </motion.div>
                </div>
            </div>
        </footer>
    );
};

export default Footer;