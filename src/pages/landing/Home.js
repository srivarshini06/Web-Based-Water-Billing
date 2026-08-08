// // import React from "react";
// // import Navbar from "../../components/landing/Navbar";
// // import Hero from "../../components/landing/Hero";
// // import Features from "../../components/landing/Features";
// // import CTA from "../../components/landing/CTA";
// //
// // const Home = () => {
// //     return (
// //         <>
// //             <Navbar />
// //             <Hero />
// //             <Features />
// //             <CTA />
// //         </>
// //     );
// // };
// //
// // export default Home;
// import React, { useEffect } from "react";
// import { motion } from "framer-motion";
// import Navbar from "../../components/landing/Navbar";
// import Hero from "../../components/landing/Hero";
// import Features from "../../components/landing/Features";
// import CTA from "../../components/landing/CTA";
// import Footer from "../../components/landing/Footer";
//
// const Home = () => {
//     useEffect(() => {
//         window.scrollTo(0, 0);
//     }, []);
//
//     return (
//         <motion.div
//             initial={{ opacity: 0 }}
//             animate={{ opacity: 1 }}
//             transition={{ duration: 0.5 }}
//             className="min-h-screen bg-white"
//         >
//             {/* Navigation */}
//             <Navbar />
//
//             {/* Main Content */}
//             <main>
//                 {/* Hero Section */}
//                 <Hero />
//
//                 {/* Features */}
//                 <Features />
//
//                 {/* Call To Action */}
//                 <CTA />
//             </main>
//
//             {/* Footer */}
//             <Footer />
//         </motion.div>
//     );
// };
//
// export default Home;
import React, { useEffect } from "react";
import { motion } from "framer-motion";
import Navbar from "../../components/landing/Navbar";
import Hero from "../../components/landing/Hero";
import Features from "../../components/landing/Features";
import CTA from "../../components/landing/CTA";
import Footer from "../../components/landing/Footer";

const Home = () => {
    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);

    return (
        <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            transition={{ duration: 0.5 }}
            className="min-h-screen bg-white"
        >
            {/* Navigation */}
            <Navbar />

            {/* Main Content */}
            <main>
                {/* Hero Section */}
                <Hero />

                {/* Features */}
                <Features />

                {/* Call To Action */}
                <CTA />
            </main>

            {/* Footer */}
            <Footer />
        </motion.div>
    );
};

export default Home;