import Navbar from "../components/landing/Navbar";
import Hero from "../components/landing/Hero";
import "../styles/landing.css";
import Features from "../components/landing/Features";
import About from "../components/landing/About";
import HowItWorks from "../components/landing/HowItWorks";
import Footer from "../components/landing/Footer";
import Stats from "../components/landing/Stats";
import Testimonials from "../components/landing/Testimonials";

function Home() {
  return (
    <>
      <Navbar />
      <Hero />
      <Features />
      <About />
      <HowItWorks />
      <Stats />
      <Testimonials />
      <Footer />
    </>
  );
}

export default Home;