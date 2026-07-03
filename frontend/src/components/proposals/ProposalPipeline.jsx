import "../../styles/proposalPipeline.css";
function ProposalPipeline({ proposals = [] }) {

  const total = proposals.length;

    const pending = proposals.filter(
    (proposal) => proposal.status?.toUpperCase() === "PENDING"
    ).length;

    const accepted = proposals.filter(
    (proposal) => proposal.status?.toUpperCase() === "ACCEPTED"
    ).length;

    const rejected = proposals.filter(
    (proposal) => proposal.status?.toUpperCase() === "REJECTED"
    ).length;
    const successRate =
  total === 0 ? 0 : Math.round((accepted / total) * 100);
  return (
    <div className="proposal-pipeline">

        <h2>Proposal Overview</h2>

        <div className="pipeline-grid">

        <div className="pipeline-card">
            <h3>📄 Total Proposals</h3>
            <p>{total}</p>
        </div>

        <div className="pipeline-card">
            <h3>⏳ Pending</h3>
            <p>{pending}</p>
        </div>

        <div className="pipeline-card">
            <h3>✅ Accepted</h3>
            <p>{accepted}</p>
        </div>

        <div className="pipeline-card">
            <h3>❌ Rejected</h3>
            <p>{rejected}</p>
        </div>

        </div>
        <div className="success-rate">

        <h3>📊 Proposal Success Rate</h3>

        <div className="progress-bar">

            <div
            className="progress-fill"
            style={{ width: `${successRate}%` }}
            ></div>

        </div>

        <p>{successRate}% of proposals accepted</p>

        </div>
    </div>
    );
}

export default ProposalPipeline;