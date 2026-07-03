export function calculateProposalScore(proposal) {
  let score = 0;

  if (proposal.title?.trim()) score += 20;

  if (proposal.description?.length > 50) score += 20;

  if (proposal.amount > 0) score += 20;

  const text = (proposal.title + " " + proposal.description).toLowerCase();

  if (
    text.includes("timeline") ||
    text.includes("delivery") ||
    text.includes("milestone") ||
    text.includes("revision")
  ) {
    score += 20;
  }

  if (proposal.description?.length > 150) score += 20;

  return score;
}