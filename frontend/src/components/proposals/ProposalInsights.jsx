import "../../styles/proposalInsights.css";
import { calculateProposalScore } from "../../utils/proposalScore";
function ProposalInsights({ proposals = [] }) {

  const enriched = proposals?.map(p => ({
    ...p,
    score: calculateProposalScore(p)
  }));

  const avgScore =
    enriched.reduce((acc, p) => acc + p.score, 0) / (enriched.length || 1);

 return (
  <div className="insights-container">

    <h2 className="insights-title">📊 Proposal Insights</h2>

    <div className="insights-grid">

      <div className="insight-card highlight">
        <p>Average Proposal Score</p>
        <h1>{avgScore.toFixed(0)}%</h1>
      </div>

      <div className="insight-card">
        <p>Total Proposals</p>
        <h2>{proposals.length}</h2>
      </div>

      <div className="insight-card">
        <p>Low Quality (&lt;60%)</p>
        <h2>
          {enriched.filter(p => p.score < 60).length}
        </h2>
      </div>

    </div>

    <div className="insights-list">

      <h3>Proposal Scores</h3>

      {enriched.map(p => (
        <div key={p.id} className="insight-row">
          <span>{p.title}</span>
          <span className={`score ${p.score >= 70 ? "good" : "bad"}`}>
            {p.score}%
          </span>
        </div>
      ))}

    </div>

  </div>
);
}

export default ProposalInsights;