import "../../styles/proposalActivity.css";

function ProposalActivity({ proposals }) {

  // 🔒 Safety guard (prevents "not iterable" crash)
  const safeProposals = Array.isArray(proposals) ? proposals : [];

  const latestProposals = [...safeProposals]
    .sort((a, b) => (b.id || 0) - (a.id || 0))
    .slice(0, 5);

  return (
    <div className="proposal-activity">

      <h2>Recent Proposal Activity</h2>

      {latestProposals.length === 0 ? (
        <p className="empty-activity">
          No proposal activity available.
        </p>
      ) : (
        <div className="activity-list">

          {latestProposals.map((proposal) => (
            <div
              key={proposal.id}
              className="activity-item"
            >

              <div className="activity-dot"></div>

              <div className="activity-content">

                <h4>{proposal.title}</h4>

                <p>
                  Status:
                  <span
                    className={`status ${(proposal.status || "").toLowerCase()}`}
                  >
                    {" "}
                    {proposal.status}
                  </span>
                </p>

                <small>
                  Proposal ID #{proposal.id}
                </small>

              </div>

            </div>
          ))}

        </div>
      )}

    </div>
  );
}

export default ProposalActivity;