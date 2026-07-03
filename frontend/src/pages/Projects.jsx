import { useEffect, useState } from "react";

import {
  getAllProjects,
  addProject,
  updateProject,
  deleteProject
} from "../services/projectService";

import ProjectTable from "../components/projects/ProjectTable";

import "../styles/projects.css";

function Projects() {

  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(false);

  const [search, setSearch] = useState("");

  const [editingId, setEditingId] = useState(null);

  const [formData, setFormData] = useState({
    proposalId: "",
    projectName: "",
    description: "",
    deadline: ""
  });

  // ================= LOAD =================

  const loadProjects = () => {

    setLoading(true);

    getAllProjects()
      .then((response) => {

        const data =
          response?.data?.data ||
          response?.data ||
          [];

        setProjects(Array.isArray(data) ? data : []);

      })
      .catch((error) => {

        console.error(error);
        setProjects([]);

      })
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    loadProjects();
  }, []);

  // ================= INPUT =================

  const handleChange = (e) => {

    setFormData({

      ...formData,

      [e.target.name]: e.target.value

    });

  };

  // ================= RESET =================

  const resetForm = () => {

    setEditingId(null);

    setFormData({

      proposalId: "",
      projectName: "",
      description: "",
      deadline: ""

    });

  };

  // ================= SUBMIT =================

  const handleSubmit = (e) => {

    e.preventDefault();

    const payload = {

      proposalId: Number(formData.proposalId),

      projectName: formData.projectName,

      description: formData.description,

      deadline: formData.deadline

    };

    if (editingId) {

      updateProject(editingId, payload)
        .then(() => {

          loadProjects();

          resetForm();

        });

    }

    else {

      addProject(payload)
        .then(() => {

          loadProjects();

          resetForm();

        });

    }

  };

  // ================= DELETE =================

  const handleDelete = (id) => {

    if (!window.confirm("Delete this project?"))
      return;

    deleteProject(id)
      .then(loadProjects);

  };

  // ================= EDIT =================

  const handleEdit = (project) => {

    setEditingId(project.id);

    setFormData({

      proposalId: project.proposalId,

      projectName: project.projectName,

      description: project.description,

      deadline: project.deadline

    });

  };

  return (

    <div className="clients-container">

      <h1>Projects</h1>

      <input
        className="search-input"
        placeholder="Search Projects..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      <form
        className="client-form"
        onSubmit={handleSubmit}
      >

        <input
          type="number"
          name="proposalId"
          placeholder="Proposal ID"
          value={formData.proposalId}
          onChange={handleChange}
        />

        <input
          type="text"
          name="projectName"
          placeholder="Project Name"
          value={formData.projectName}
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
          type="date"
          name="deadline"
          value={formData.deadline}
          onChange={handleChange}
        />

        <button type="submit">

          {editingId ? "Update Project" : "Create Project"}

        </button>

      </form>

      {

        loading

        ?

        <p>Loading...</p>

        :

        <ProjectTable

          projects={projects}

          search={search}

          handleDelete={handleDelete}

          handleEdit={handleEdit}

        />

      }

    </div>

  );

}

export default Projects;