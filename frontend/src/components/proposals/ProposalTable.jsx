function ProposalTable({
  proposals = [],
  search = "",
  handleEdit,
  handleDelete,
  handleGenerateInvoice,
  handleCreateProject
}) {

  const filteredProposals = (proposals || []).filter((proposal) => {
    return (
      (proposal.title ?? "").toLowerCase().includes(search.toLowerCase()) ||
      (proposal.description ?? "").toLowerCase().includes(search.toLowerCase()) ||
      (proposal.status ?? "").toLowerCase().includes(search.toLowerCase()) ||
      (proposal.clientId ?? "").toString().includes(search)
    );
  });

  return (
    <table className="client-table">

      <thead>
        <tr>
          <th>ID</th>
          <th>Client ID</th>
          <th>Title</th>
          <th>Description</th>
          <th>Amount</th>
          <th>Status</th>
          <th>Invoice</th>
          <th>Project</th>
          <th>Progress</th>
          <th>Actions</th>
        </tr>
      </thead>

      <tbody>
        {filteredProposals.map((proposal) => (
          <tr key={proposal.id}>

            <td>{proposal.id}</td>
            <td>{proposal.clientId}</td>
            <td>{proposal.title}</td>
            <td>{proposal.description}</td>
            <td>₹{Number(proposal.amount).toLocaleString("en-IN")}</td>
            <td>{proposal.status}</td>

            {/* Invoice Status */}
            <td>
              {proposal.invoiceGenerated ? (
                <span className="generated-text">✓ Generated</span>
              ) : (
                <span className="pending-text">Not Generated</span>
              )}
            </td>

            {/* Project Status */}
            <td>
              {proposal.projectCreated ? (
                <span className="generated-text">✓ Created</span>
              ) : (
                <span className="pending-text">Not Created</span>
              )}
            </td>

            {/* Progress / Info Column */}
            <td>
              {proposal.projectCreated ? (
                <span className="generated-text">In Project</span>
              ) : proposal.invoiceGenerated && proposal.status === "ACCEPTED" ? (
                <span className="pending-text">Ready to Create</span>
              ) : (
                <span className="pending-text">-</span>
              )}
            </td>

            {/* ACTIONS */}
            <td>

              {/* Generate Invoice */}
              {proposal.status === "ACCEPTED" && !proposal.invoiceGenerated && (
                <button
                  className="generate-btn"
                  onClick={() => handleGenerateInvoice(proposal)}
                >
                  Generate Invoice
                </button>
              )}

              {proposal.invoiceGenerated && (
                <button className="generated-btn" disabled>
                  ✓ Invoice Generated
                </button>
              )}

              <br /><br />

              {/* Create Project */}
              {proposal.status === "ACCEPTED" && (
                proposal.projectCreated ? (
                  <button className="generated-btn" disabled>
                    ✓ Project Created
                  </button>
                ) : (
                  <button
                    className="generate-btn"
                    onClick={() => handleCreateProject(proposal)}
                  >
                    Create Project
                  </button>
                )
              )}

              <br /><br />

              {/* Edit */}
              <button
                className="edit-btn"
                onClick={() => handleEdit(proposal)}
              >
                Edit
              </button>

              {/* Delete */}
              <button
                className="delete-btn"
                onClick={() => handleDelete(proposal.id)}
              >
                Delete
              </button>

            </td>
          </tr>
        ))}
      </tbody>

    </table>
  );
}

export default ProposalTable;