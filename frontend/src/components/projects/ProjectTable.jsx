import {
  updateProjectProgress,
  updateProjectStatus
} from "../../services/projectService";

function ProjectTable({
  projects = [],
  search = "",
  handleEdit,
  handleDelete
}) {

  const filteredProjects = projects.filter((project) => {

    return (

      (project.projectName ?? "")
        .toLowerCase()
        .includes(search.toLowerCase())

      ||

      (project.description ?? "")
        .toLowerCase()
        .includes(search.toLowerCase())

      ||

      (project.status ?? "")
        .toLowerCase()
        .includes(search.toLowerCase())

    );

  });

  const handleProgress = (id, progress) => {

    updateProjectProgress(id, progress)
      .then(() => window.location.reload())
      .catch(console.error);

  };

  const handleStatus = (id, status) => {

    updateProjectStatus(id, status)
      .then(() => window.location.reload())
      .catch(console.error);

  };

  return (

    <table className="client-table">

      <thead>

        <tr>

          <th>ID</th>

          <th>Project</th>

          <th>Proposal</th>

          <th>Client</th>

          <th>Deadline</th>

          <th>Status</th>

          <th>Progress</th>

          <th>Actions</th>

        </tr>

      </thead>

      <tbody>

        {

          filteredProjects.map((project) => (

            <tr key={project.id}>

              <td>{project.id}</td>

              <td>{project.projectName}</td>

              <td>{project.proposalId}</td>

              <td>{project.clientId}</td>

              <td>{project.deadline}</td>

              <td>

                <span
                  className={`status-badge ${project.status.toLowerCase()}`}
                >
                  {project.status}
                </span>

                <br /><br />

                <select

                  value={project.status}

                  onChange={(e) =>
                    handleStatus(project.id, e.target.value)
                  }

                >

                  <option value="NOT_STARTED">
                    NOT_STARTED
                  </option>

                  <option value="IN_PROGRESS">
                    IN_PROGRESS
                  </option>

                  <option value="COMPLETED">
                    COMPLETED
                  </option>

                </select>

              </td>

              <td>

                <input

                  type="range"

                  min="0"

                  max="100"

                  value={project.progress}

                  onChange={(e) =>
                    handleProgress(
                      project.id,
                      Number(e.target.value)
                    )
                  }

                />

                <br />

                {project.progress}%

              </td>

              <td>

                <button

                  className="edit-btn"

                  onClick={() => handleEdit(project)}

                >

                  Edit

                </button>

                <button

                  className="delete-btn"

                  onClick={() => handleDelete(project.id)}

                >

                  Delete

                </button>

              </td>

            </tr>

          ))

        }

        {

          filteredProjects.length === 0 && (

            <tr>

              <td colSpan="8">

                No Projects Found

              </td>

            </tr>

          )

        }

      </tbody>

    </table>

  );

}

export default ProjectTable;