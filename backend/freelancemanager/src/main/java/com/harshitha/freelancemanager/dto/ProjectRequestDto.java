package com.harshitha.freelancemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ProjectRequestDto {

   // @NotNull(message = "Client ID is required")
    private Long clientId;

    @NotNull(message = "Proposal ID is required")
    private Long proposalId;

    @NotBlank(message = "Project name is required")
    private String projectName;

    @NotBlank(message = "Description is required")
    private String description;

    // backend sets this → remove validation only
    private LocalDate startDate;

    @NotNull(message = "Deadline is required")
    private LocalDate deadline;

    // backend sets this → remove validation only
    private Integer progress;

    // backend sets this → remove validation only
    private String status;

    // ================= GETTERS =================

    public Long getClientId() {
        return clientId;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Integer getProgress() {
        return progress;
    }

    public String getStatus() {
        return status;
    }

    // ================= SETTERS =================

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}