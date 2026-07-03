import { useEffect, useState } from "react";
import {
  getAllProposals,
  addProposal,
  deleteProposal,
  updateProposal,
  generateInvoice
} from "../services/proposalService";

import ProposalTable from "../components/proposals/ProposalTable";
import ProposalPipeline from "../components/proposals/ProposalPipeline";
import ProposalInsights from "../components/proposals/ProposalInsights";
import ProposalActivity from "../components/proposals/ProposalActivity";
import "../styles/proposals.css";
import { createProjectFromProposal } from "../services/projectService"; 
import { toast } from "react-toastify"; 

function Proposals() {
  const [proposals, setProposals] = useState([]);
  const [loading, setLoading] = useState(false);
  const [editingId, setEditingId] = useState(null);
  const [search, setSearch] = useState("");

  const [formData, setFormData] = useState({
    title: "",
    description: "",
    amount: "",
    status: "",
    clientId: ""
  });

  // ================= LOAD =================
  const loadProposals = () => {
    setLoading(true);

    getAllProposals()
      .then((response) => {
        const data =
          response?.data?.data ||
          response?.data ||
          response ||
          [];

        setProposals(Array.isArray(data) ? data : []);
      })
      .catch((error) => {
        console.error("Error fetching proposals:", error);
        setProposals([]);
      })
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    loadProposals();
  }, []);

  //-----------------handlers-----------------
      const handleCreateProject = async (proposal) => {

  console.log("========== Proposal Object ==========");
  console.log(proposal);
  console.log("proposal.id =", proposal?.id);
  console.log("proposal.proposalId =", proposal?.proposalId);
  console.log("=====================================");

  try {

    const payload = {
      proposalId: proposal?.id,
      projectName: proposal?.title,
      description: proposal?.description,
      deadline: new Date().toISOString().split("T")[0]
    };

    console.log("Payload being sent:");
    console.log(payload);

    await createProjectFromProposal(payload);

    toast.success("Project created successfully!");

    loadProposals();

  } catch (error) {

    console.error("Create Project Error:", error);

    if (error.response) {
      console.error("Response:", error.response.data);
      console.error("Status:", error.response.status);
    }

    toast.error(
      error.response?.data?.message ||
      error.response?.data ||
      "Unable to create project"
    );

  }

};
  // ================= CHANGE =================
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  // ================= SUBMIT =================
  const handleSubmit = (e) => {
    e.preventDefault();

    // 🔥 CLEAN PAYLOAD (IMPORTANT FIX)
    const payload = {
      title: formData.title,
      description: formData.description,
      amount: Number(formData.amount),
      status: formData.status,
      clientId: Number(formData.clientId)
    };

    if (editingId) {
      // UPDATE
      updateProposal(editingId, payload)
        .then(() => {
          loadProposals();
          setEditingId(null);
          resetForm();
        })
        .catch((error) => {
          console.error("Error updating proposal:", error);
        });

    } else {
      // ADD
      addProposal(payload)
        .then(() => {
          loadProposals();
          resetForm();
        })
        .catch((error) => {
          console.error("Error adding proposal:", error);
        });
    }
  };

  // ================= RESET FORM =================
  const resetForm = () => {
    setFormData({
      title: "",
      description: "",
      amount: "",
      status: "",
      clientId: ""
    });
  };

  // ================= DELETE =================
  const handleDelete = (id) => {
    if (!window.confirm("Delete this proposal?")) return;

    deleteProposal(id)
      .then(() => loadProposals())
      .catch((error) => {
        console.error("Error deleting proposal:", error);
      });
  };
  const handleGenerateInvoice = async (proposal) => {

  const dueDate = window.prompt(
    "Enter Due Date (YYYY-MM-DD)"
  );

  if (!dueDate) return;

  try {

    await generateInvoice(
      proposal.id,
      dueDate
    );

    alert("Invoice generated successfully.");

    loadProposals();

  } catch (error) {

    alert(
      error?.response?.data?.message ||
      "Unable to generate invoice."
    );

  }

};
  // ================= EDIT =================
  const handleEdit = (proposal) => {
    setEditingId(proposal.id);

    setFormData({
      title: proposal.title,
      description: proposal.description,
      amount: proposal.amount,
      status: proposal.status,
      clientId: proposal.clientId || ""
    });
  };

  return (
    <div className="clients-container">

      <h1>Proposals</h1>

      <input
        className="search-input"
        type="text"
        placeholder="🔍 Search by title, description, status"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      {/* ================= FORM ================= */}
      <form className="client-form" onSubmit={handleSubmit}>

        <input
          type="text"
          name="title"
          placeholder="Title"
          value={formData.title}
          onChange={handleChange}
        />

        <input
          type="text"
          name="description"
          placeholder="Description"
          value={formData.description}
          onChange={handleChange}
        />

        <input
          type="number"
          name="clientId"
          placeholder="Client ID"
          value={formData.clientId}
          onChange={handleChange}
        />

        <input
          type="number"
          name="amount"
          placeholder="Amount"
          value={formData.amount}
          onChange={handleChange}
        />

        <select
          name="status"
          value={formData.status}
          onChange={handleChange}
        >
          <option value="">Select Status</option>
          <option value="PENDING">PENDING</option>
          <option value="ACCEPTED">ACCEPTED</option>
          <option value="REJECTED">REJECTED</option>
        </select>

        <button type="submit">
          {editingId ? "Update Proposal" : "Add Proposal"}
        </button>

      </form>

      {/* ================= TABLE + UI ================= */}
      {loading ? (
        <p>Loading proposals...</p>
      ) : (
        <div className="proposals-page">

          <ProposalTable
            proposals={proposals}
            search={search}
            handleEdit={handleEdit}
            handleDelete={handleDelete}
            handleGenerateInvoice={handleGenerateInvoice}
            handleCreateProject={handleCreateProject}
          />

          <div className="row">
            <ProposalPipeline proposals={proposals} />
            <ProposalInsights proposals={proposals} />
          </div>

          <ProposalActivity proposals={proposals} />

        </div>
      )}

    </div>
  );
}

export default Proposals;