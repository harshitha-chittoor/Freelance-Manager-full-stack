import "../../styles/testimonials.css";

function Testimonials() {

  const testimonials = [
    {
      name: "Sarah Johnson",
      role: "UI/UX Designer",
      review:
        "Freelance Manager has completely transformed how I manage my clients and invoices."
    },
    {
      name: "Michael Brown",
      role: "Web Developer",
      review:
        "Everything is organized in one place. Tracking payments has never been easier."
    },
    {
      name: "Emily Davis",
      role: "Content Writer",
      review:
        "A clean interface and powerful features. It saves me hours every week."
    }
  ];

  return (
    <section className="testimonials">

      <h2>What Our Users Say</h2>

      <div className="testimonial-grid">

        {testimonials.map((user, index) => (

          <div className="testimonial-card" key={index}>

            <div className="avatar">
              {user.name.charAt(0)}
            </div>

            <h3>{user.name}</h3>

            <span>{user.role}</span>

            <p>"{user.review}"</p>

          </div>

        ))}

      </div>

    </section>
  );
}

export default Testimonials;